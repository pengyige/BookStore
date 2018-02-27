package cn.pengyi.uitls;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.security.acl.Permission;
import java.util.List;

import cn.pengyi.domain.Privilege;
import cn.pengyi.domain.User;
import cn.pengyi.service.BusinessService;
import cn.pengyi.utils.Permisson;


public class ServiceFactory {
	
	private static final ServiceFactory instance = new ServiceFactory();
	private ServiceFactory(){};
	public static ServiceFactory getIntance(){
		return instance;
	}
	
	
	public<T> T createService(String className,Class<T> clazz,final User user){
		
		try{
			final T t = (T) Class.forName(className).newInstance();
			
			return (T) Proxy.newProxyInstance(ServiceFactory.class.getClassLoader(),t.getClass().getInterfaces(), new InvocationHandler(){

				@Override
				public Object invoke(Object proxy, Method method, Object[] args)
						throws Throwable {
					//1.判断用户调用的是什么方法 此处的method是代理对象的method
					String methodName = method.getName();
					
					//2.通过代理对象method反射出真实对象中method，从而获得真实method上面的注解信息
					Method m = t.getClass().getMethod(methodName, method.getParameterTypes());
					
					//3.查看真实对象是否有注解权限
					Permisson p = (Permisson) m.getAnnotation(Permisson.class);
					
					
					//4.如果没有，代表访问该方法不需要权限，调用真实对象方法即可
					if(p==null){
						return method.invoke(t, args);
					}
					
					//5.如果有，得到该方法上面的注解需要什么什么权限
					String name = p.value();
					Privilege privilege = new Privilege();
					privilege.setName(name);
					
					
					//6.得到用户的权限
					if(user == null){
						throw new PrivilegeException("请先登入");
						}
			
					BusinessService service =(BusinessService)t;
					List<Privilege> list = service.getUserPrivilege(user.getId());
					
					//7.检查用户是否有权限，如果有放行
					if(list.contains(privilege)){
						return method.invoke(t, args);
					}else{
						throw new PrivilegeException("对不起，没有权限");
					}
					
					
					
					
				}
					
			});
		}catch (Exception e){
			throw new RuntimeException(e);
		}
	}
}
