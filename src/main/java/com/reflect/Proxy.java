package com.reflect;

public class Proxy implements Sourceable{
	private Source source;
	
	public Proxy(){
		super();
		this.source = new Source();
	}
	
	private void after(){
		System.out.println("after proxy");
	}
	
	private void before(){
		System.out.println("before proxy");
	}

	public void method() {
		// TODO Auto-generated method stub
		before();
		source.method();
		after();
	}

}
