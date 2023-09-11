package com.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {
	
	private Object target;
	public void setTarget(Object target){
		this.target = target;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		Extend extend = new Extend();
		
		extend.before();
		Object result = method.invoke(target, args);
		extend.after();
		
		return result;
	}

}
