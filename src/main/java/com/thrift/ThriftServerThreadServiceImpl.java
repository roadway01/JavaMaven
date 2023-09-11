/**
 * 
 */
package com.thrift;

import org.apache.thrift.TException;

import com.thrift.dataType.ResultStr;
import com.thrift.dataType.ThriftResult;

/**
 * @desc  : TODO
 * @author: Zhu
 * @date  : 2017年3月2日
 */
public class ThriftServerThreadServiceImpl implements ThriftServerService.Iface{

	/* (non-Javadoc)
	 * @see com.thrift.ThriftServerService.Iface#sayHello(java.lang.String)
	 */
	@Override
	public ResultStr sayHello(String username) throws TException {
		// TODO Auto-generated method stub
		ResultStr resultStr = new ResultStr();
		resultStr.result = ThriftResult.SUCCESS;
		resultStr.value = "Hello " + username + "!!!";
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultStr;
	}

}
