package com.hbh.cl.carfriends.util;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/*
 * Json相关类
 */
public class JsonHelper {

	// 反序列化json
	public static <T> T parseObject(String jsonStr, Type type) {
		GsonBuilder builder = new GsonBuilder();
		// 不转换没有 @Expose 注解的字段
		builder.excludeFieldsWithoutExposeAnnotation();
		DateDeserializer ds = new DateDeserializer();
		builder.registerTypeAdapter(Date.class, ds);
		Gson gson = builder.create();
		return gson.fromJson(jsonStr, type);
	}

	// 序列化Json
	public static String toJson(Object object) {
		GsonBuilder builder = new GsonBuilder();
		// 不转换没有 @Expose 注解的字段
		builder.excludeFieldsWithoutExposeAnnotation();
		builder.registerTypeAdapter(Date.class, new UtilDateSerializer());
		Gson gson = builder.create();
		return gson.toJson(object);
	}
}

class DateDeserializer implements JsonDeserializer<Date> {
	@Override
	public Date deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		String JSONDateToMilliseconds = "\\/(Date\\((.*?)(\\+.*)?\\))\\/";
		Pattern pattern = Pattern.compile(JSONDateToMilliseconds);
		Matcher matcher = pattern.matcher(json.getAsJsonPrimitive()
				.getAsString());
		String result = matcher.replaceAll("$2");
		return new Date(new Long(result));
	}
}

class UtilDateSerializer implements JsonSerializer<Date> {
	@Override
	public JsonElement serialize(Date src, Type typeOfSrc,
			JsonSerializationContext context) {
		return new JsonPrimitive("/Date(" + src.getTime() + "+0800)/");
	}
}
