package cn.pengyi.uitls;

import java.util.UUID;

public class WebUtils {
	
	public static String makeID(){
		return UUID.randomUUID().toString();
	}
}
