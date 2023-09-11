namespace java com.thrift

include "ThriftDataType.thrift"

service ThriftServerService{
	ThriftDataType.ResultStr sayHello(1:string username)
}