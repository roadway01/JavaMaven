package com.reflect;

import java.lang.reflect.Proxy;

public class MyProxyFactory {

	public static Object getProxy(Object target){
		MyInvocationHandler myInvocationHandle = new MyInvocationHandler();
		myInvocationHandle.setTarget(target);
		
		Object proxyObject = Proxy.newProxyInstance(target.getClass().getClassLoader(), 
													target.getClass().getInterfaces(), myInvocationHandle);
		return proxyObject;
	}
	
}
