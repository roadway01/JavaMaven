package com.spymemcached;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @Name  :SpyMemcached
 * @Desc  :TODO
 * @author:zhu
 * @date  :2016年9月2日
 */
public class SpyMemcached {

	/**
	 * @Desc  :TODO
	 * @param :
	 * @throws:
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context = new FileSystemXmlApplicationContext("applicationContext_memcached.xml");
		SpyMemcachedManager memcachedManager = (SpyMemcachedManager) context.getBean("memcachedManager");
		memcachedManager.add("key", "value", 1000);
	}

}
