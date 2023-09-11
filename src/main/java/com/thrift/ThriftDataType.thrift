namespace java com.thrift.dataType

enum ThriftResult
{
  SUCCESS,           /*成功*/
  SERVER_UNWORKING,  /*服务器处于非Working状态*/
  NO_CONTENT,  		 /*请求结果不存在*/
  PARAMETER_ERROR,	 /*参数错误*/
  EXCEPTION,	 	 /*内部出现异常*/
  INDEX_ERROR,		 /*错误的索引或者下标值*/
  UNKNOWN_ERROR, 	 /*未知错误*/
  DATA_NOT_COMPLETE,  /*数据不完全*/
  INNER_ERROR 	    /*内部错误*/
}

/*bool类型返回结果*/
struct ResultBool 
{
  1: ThriftResult result,
  2: bool value
}

/*int类型返回结果*/
struct ResultInt
{
  1: ThriftResult result,
  2: i32 value
}

/*String类型返回结果*/
struct ResultStr
{
  1: ThriftResult result,
  2: string value
}

/*long类型返回结果*/
struct ResultLong
{
  1: ThriftResult result,
  2: i64 value
}

/*double类型返回结果*/
struct ResultDouble
{
  1: ThriftResult result,
  2: double value
}

/*void类型返回结果*/
struct ResultVoid
{
  1: ThriftResult result
}

/*list<string>类型返回结果*/
struct ResultListStr 
{
  1: ThriftResult result,
  2: list<string> value
}

/*Set<string>类型返回结果*/
struct ResultSetStr 
{
  1: ThriftResult result,
  2: set<string> value
}

/*map<string,string>类型返回结果*/
struct ResultMapStrStr 
{
  1: ThriftResult result,
  2: map<string,string> value
}