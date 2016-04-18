package club.mecn.util;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class BeanValueExchangeUtil {

	
	
	/**
	 * 同步obj2的数据到obj1
	 * @param obj1 目标
	 * @param obj2 数据来源
	 */
	public static <T> void exchangeBeanValue(T obj1,T obj2)
	{
		Class class1 = obj1.getClass();
		Class class2 = obj2.getClass();
		
		//类名不相同不能赋值
		if(!class1.getName().equals(class2.getName()))
		{
			return ;
		}
		
		Method[] methods = class1.getDeclaredMethods();
		Map<String,Method> getters = new HashMap<String, Method>();
		Map<String,Method> setters = new HashMap<String, Method>();
		
		for(Method method : methods)
		{
			int subNum = 0;
			boolean isGetter = false;
			boolean isSetter = false;
			if(method.getName().substring(0, 3).equals("get") && !method.getName().equals("getClass"))
			{
				subNum = 3;
				isGetter = true;
			}
			if(method.getName().substring(0, 2).equals("is"))
			{
				subNum = 2;
				isGetter = true;
			}
			if(method.getName().substring(0, 3).equals("set"))
			{
				isSetter = true;
			}
			
			if(isGetter)
			{
				//存储字段名和方法名
				getters.put(method.getName().substring(subNum), method);
			}
			if(isSetter)
			{
				setters.put(method.getName().substring(3), method);
			}
		}
		

		//进行obj.set(get())
		for(Entry<String, Method> entry : setters.entrySet())
		{
			Method setter = entry.getValue();
			Method getter = getters.get(entry.getKey());
			try {
				setter.invoke(obj1, getter.invoke(obj2));
			}  catch (Exception e) {
				e.printStackTrace();
			}
		}
			
		
	}
	
}
