package com.xunlei.sdk.test.utils;

import java.lang.reflect.Field;

public class ReflectUtils {

	/**
	 * 获取私有属性
	 * 
	 * @param object
	 * @param name
	 * @return
	 */
	public static Object getPrivateAttr(Object object, String name) {
		Class<? extends Object> temp = object.getClass();
		Object attribution = null;
		try {
			Field field = temp.getDeclaredField(name);
			field.setAccessible(true);
			attribution = field.get(object);
		} catch (NoSuchFieldException | IllegalAccessException
				| IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return attribution;
	}

}
