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
 * @date  : 2017年2月28日
 */
public class ThriftServerServiceImpl implements ThriftServerService.Iface{

	/* (non-Javadoc)
	 * @see com.thrift.TSimpleServerService.Iface#sayHello(java.lang.String)
	 */
	@Override
	public ResultStr sayHello(String username) throws TException{
		// TODO Auto-generated method stub
		
		ResultStr resultStr = new ResultStr();
		resultStr.result = ThriftResult.SUCCESS;
		resultStr.value = "Hello " + username + "!!!";
		return resultStr;
	}

}
