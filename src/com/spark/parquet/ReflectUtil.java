package com.spark.parquet;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;

public class ReflectUtil {
	@SuppressWarnings("rawtypes")
	public static Object getBean(String className) throws Exception {
		Class cls = Class.forName(className);
		Constructor[] cons = cls.getConstructors();
		Constructor defCon = cons[0];// 得到默认构造器,第0个是默认构造器，无参构造方法
		Object obj = defCon.newInstance();// 实例化，得到一个对象 //Spring - bean -id
		return obj;
	}

	/**
	 * java反射bean的set方法
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static Method getSetMethod(Class objClass, String fieldName) throws Exception {
		Class[] parameterTypes = new Class[1];
		Field field = objClass.getDeclaredField(fieldName.substring(0, 1).toLowerCase()+fieldName.substring(1));
		parameterTypes[0] = field.getType();
		StringBuilder builder = new StringBuilder();
		builder.append("set");
		builder.append(fieldName.substring(0, 1).toUpperCase());
		builder.append(fieldName.substring(1));
		Method method = objClass.getMethod(builder.toString(), parameterTypes);
		return method;
	}

	/**
	 * 执行set方法
	 */
	public static void invokeSet(Object obj, String fieldName, Object value) throws Exception{
		Method method = getSetMethod(obj.getClass(), fieldName);
		
		Field field = obj.getClass().getDeclaredField(fieldName.substring(0, 1).toLowerCase()+fieldName.substring(1));
		if(field.getType().getName().equals("int")&&!value.getClass().getName().equals("int")){
			value = Integer.valueOf(value.toString());
		}else if(field.getType().getName().equals("long")&&!value.getClass().getName().equals("long")){
			value = Long.valueOf(value.toString());
		}else if(field.getType().getName().equals("java.util.Date")){
			if(value.toString().length()==19){
				SimpleDateFormat sdf = new SimpleDateFormat(value.toString().replaceAll("\\d+(\\D*)\\d+(\\D*)\\d+(\\D*)\\d+(\\D*)\\d+(\\D*)\\d+", "yyyy$1MM$2dd$3HH$4mm$5ss"));
				value = sdf.parse(value.toString());
			}else if(value.toString().length()==10){
				SimpleDateFormat sdf = new SimpleDateFormat(value.toString().replaceAll("\\d+(\\D*)\\d+(\\D*)\\d", "yyyy$1MM$2dd"));
				value = sdf.parse(value.toString());
			}else{
				throw new IllegalArgumentException("date型必需为10或19字符日期");
			}
		}else if(field.getType().getName().equals("java.sql.Date")){
			if(value.toString().length()==19){
				SimpleDateFormat sdf = new SimpleDateFormat(value.toString().replaceAll("\\d+(\\D*)\\d+(\\D*)\\d+(\\D*)\\d+(\\D*)\\d+(\\D*)\\d+", "yyyy$1MM$2dd$3HH$4mm$5ss"));
				value = new java.sql.Date(sdf.parse(value.toString()).getTime());
			}else if(value.toString().length()==10){
				SimpleDateFormat sdf = new SimpleDateFormat(value.toString().replaceAll("\\d+(\\D*)\\d+(\\D*)\\d", "yyyy$1MM$2dd"));
				value = new java.sql.Date(sdf.parse(value.toString()).getTime());
			}else{
				throw new IllegalArgumentException("date型必需为10或19字符日期");
			}
		}
		method.invoke(obj, new Object[] { value });
	}

	/**
	 * java反射bean的get方法
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static Method getGetMethod(Class objClass, String fieldName) throws Exception{
		StringBuilder builder = new StringBuilder();
		if ("boolean".equals(objClass.getDeclaredField(fieldName).getType().getName()))
			builder.append("is");
		else
			builder.append("get");
		builder.append(fieldName.substring(0, 1).toUpperCase());
		builder.append(fieldName.substring(1));
		Method method = objClass.getMethod(builder.toString());
		return method;
	}

	/**
	 * 执行get方法
	 */
	public static Object invokeGet(Object obj, String fieldName) throws Exception{
		Method method = getGetMethod(obj.getClass(), fieldName);
		return method.invoke(obj);
	}

	/**
	 * 执行方法
	 */
	public static Object invoke(Object obj, String methodName, @SuppressWarnings("rawtypes") Class[] clazz, Object[] value) throws Exception {
		Method method = null;
		if (clazz.length < 1) {
			method = obj.getClass().getMethod(methodName);
			return method.invoke(obj);
		} else {
			method = obj.getClass().getMethod(methodName, clazz);
			return method.invoke(obj, value);
		}
	}
}
