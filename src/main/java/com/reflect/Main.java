package com.reflect;

import java.lang.reflect.Method;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Reflect reflect = new MyReflect();
		Reflect proxyReflect = (Reflect)MyProxyFactory.getProxy(reflect);
		proxyReflect.show();
		reflect.show();
		
		System.out.println("\n---------------------------------------\n");
		
		Class<?> clazz = Class.forName("com.reflect.MyReflect");
		Object reflect1 = clazz.newInstance();
		Method[] methods = clazz.getDeclaredMethods();
		for(Method method : methods){
			method.invoke(reflect1,new Object[]{});
		}
		
		System.out.println("\n---------------------------------------\n");
		
		Reflect reflect2 = (Reflect) clazz.newInstance();
		Reflect proxyReflect2 = (Reflect)MyProxyFactory.getProxy(reflect2);
		proxyReflect2.show();
		
		
		
	}

}
