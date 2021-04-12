package com.example.aisearch.util.httpokutil;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.Response;

public class GenericHttpUtils {
    //public static final String baseUrl = "http://10.151.3.61:8082";
    //public static final String baseUrl = "http://192.168.43.15:8082";


//    public static final String baseUrl = "http://47.102.221.169:8082/smarthotel-0.0.1-SNAPSHOT";
    public static final String baseUrl ="";

    public static <T> T fromJson(String json, Class<T> classOfT){
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            @Override
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return new Date(json.getAsJsonPrimitive().getAsLong());
            }
        });
        return builder.create().fromJson(json,classOfT);
        //return new GsonBuilder().setDateFormat("yyyy-MM-dd").create().fromJson(json, classOfT);
        //return new Gson().fromJson(json, classOfT);
    }
    public static String toJson(Object object){
        return new Gson().toJson(object);
    }
    public static <T> T fromJson(String json, Type type){
        /*
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            @Override
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return new Date(json.getAsJsonPrimitive().getAsLong());
            }
        });
        return builder.create().fromJson(json,type);

         */
        //return new GsonBuilder().setDateFormat("yyyy-MM-dd").create().fromJson(json, type);

        System.out.println(json);
        return new Gson().fromJson(json, type);
    }


    public static String getResponse(String url){
        try {
            Response response = OkHttpUtils.get()
                    .url(baseUrl+url)
                    .build()
                    .execute();
            String str = response.body().string();
            System.out.println(str);
            return str;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }


    public static String postStringResponse(String url, String content){
        try {
            Response response = OkHttpUtils.postString()
                    .url(baseUrl+url)
                    .mediaType(MediaType.parse("application/json;charset=utf-8"))
                    .content(content)
                    .build()
                    .execute();
            String str = response.body().string();
            return str;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }


    public static String deleteResponse(String url, String content){
        try {
            Response response = OkHttpUtils.delete().url(baseUrl+url)
                    .requestBody(RequestBody.create(MediaType.parse("application/json"),content))
                    .build()
                    .execute();
            return response.body().string();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

}
