package club.mecn.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by exste on 2016/02/22.
 */
public class JsonUtil {
    /**
     * 状态 1成功,-1失败
     */
    public final static String RETURN_STATUS = "status";

    /**
     * 消息
     */
    public final static String RETURN_MESSAGE = "message";

    /**
     * 数据
     */
    public final static String RETURN_DATA = "data";

    /**
     * 状态码用来定义状态
     * -113 注册邮箱已存在
     * -112 用户名已存在
     * -111 注册信息不完整
     * 110 成功注册
     * -101 没有用户
     * -102 密码错误
     * -103 邮箱未激活
     * 100 成功登录(会返回数据)
     */
    public final static String RETURN_STATUS_CODE = "statusCode";

    /**
     * 状态定义
     */
    public final static int SUCCESS_STATUS = 1;

    public final static int FAIL_STATUS = -1;


    /**
     *
     * @param status 状态标识
     * @param errorcode 状态码
     * @param message 消息说明
     * @return
     */
    public static Map<String,Object> returnJsonMap(int status,int statusCode,String message)
    {
        Map<String,Object> result = new HashMap<String, Object>();
        result.put(RETURN_STATUS,status);
        result.put(RETURN_STATUS_CODE,statusCode);
        result.put(RETURN_MESSAGE, message);

        return result;
    }
    
    
    /**
     * @param status 状态标识
     * @param statusCode 状态码
     * @param message 消息说明
     * @param data 数据
     * @return
     */
    public static Map<String,Object> returnJsonMap(int status,int statusCode,String message,Object data)
    {
        Map<String,Object> result = new HashMap<String, Object>();
        result.put(RETURN_STATUS,status);
        result.put(RETURN_STATUS_CODE,statusCode);
        result.put(RETURN_MESSAGE, message);
        result.put(RETURN_DATA, data);

        return result;
    }

    public static Map<String,Object> returnJsonMap(int status, String message, Object data)
    {
        Map<String,Object> result = new HashMap<String, Object>();
        result.put(RETURN_STATUS,status);
        result.put(RETURN_MESSAGE,message == null ? "" : message);
        if(data != null)
            result.put(RETURN_DATA,data);

        return result;
    }

    /**
     * 只有状态和数据的
     * @param status
     * @param data
     * @return
     */
    public static Map<String,Object> returnJsonMap(int status, Object data)
    {
        return JsonUtil.returnJsonMap(status,null,data);
    }

    /**
     * 只有状态
     * @param status
     * @return
     */
    public static Map<String,Object> returnJsonMap(int status)
    {
        return JsonUtil.returnJsonMap(status,null,null);
    }

    public static Map<String,Object> returnJsonMap(int status,String message)
    {
        return JsonUtil.returnJsonMap(status,message,null);
    }
//    /**
//     * 返回json数据格式,字符串格式
//     *
//     * @param status
//     * @param message
//     * @return String
//     */
//    public static String returnJsonMapString(int status,String message)
//    {
//        Map<String,Object> returnJsonMap = JsonResultUtil.returnJsonMap(status,message,null);
//        ObjectMapper mapper = new ObjectMapper();
//        String jsonResult = "";
//        try{
//            jsonResult = mapper.writeValueAsString(returnJsonMap);
//        } catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//        return jsonResult;
//    }
}
