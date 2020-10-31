# 中道监理平台

## 任务分配（Web端）

杨鸿健：事件管理->评价管理

孙青鑫：资产管理，实施管理，事件附件

李佳琪：信息管理

梁老师：公告推送

## 项目相关资料

项目前端： http://taxuexunshang.gitee.io/managementui 

数据库详细设计： https://www.yuque.com/bdy5qz/project/vpk6i0 

## 接口设计细则

> 建议设计为Restful风格的接口

为方便后期前后端对接，在接口设计完成后，推荐使用Postman测试工具模拟请求，测试通过后，再行提交代码

工具下载地址： https://www.postman.com/ 

## 编码规范

> 建议遵守阿里巴巴Java编程规范

## Controller统一返回与异常处理
在项目中设计了的如下的统一返回值对象
```java
public class ResponseResult<T> {
    public static final Integer OK = 0;
    public static final Integer ERROR = 100;

    private Integer code;     //Error不用填，OK需要使用setter进行注入
    private String message;   //可选择填入前端所需提示信息
    private String url;       //无需填写，发生异常会自动进行注入
    private T data;           //泛型数据 必填
}
```
### 在Controller层中的应用举例
```java
public ResponseResult<List<Event>> getEventsByOptions(@RequestParam Map<String,Object> options) throws Exception{
        List<Event> events = service.getEventsByOptions(options);
        ResponseResult<List<Event>> info = new ResponseResult<>();
        if (events.size() == 0){
            //throw MyException 便可直接触发统一异常处理
            throw new MyException("查询结果为空");
        }else{
            //操作成功记得将OK放入Code layUI数据表格可直接解析返回数据
            info.setCode(ResponseResult.OK);
            info.setData(events);
        }
        return info;
    }
```
## 常量统一采用util包下的Final文件进行设置
- 方便统一维护

## 上传下载相关格式规范
- 文件名格式：
- 文件路径格式规范

