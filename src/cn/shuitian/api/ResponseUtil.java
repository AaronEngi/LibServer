package cn.shuitian.api;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cn.tyrael.data.net.response.BaseResponse;

public class ResponseUtil {
	public static void returnSuccess(HttpServletResponse response) throws IOException{
		BaseResponse br = new BaseResponse();
		response.getWriter().append(new Gson().toJson(br));
	}
	
	public static void returnSuccess(HttpServletResponse response, Object data) throws IOException{
		BaseResponse br = new BaseResponse();
		br.data = data;
		response.getWriter().append(createGson().toJson(br));
	}
	
	public static Gson createGson(){
		GsonBuilder gsonBuilder = new GsonBuilder();  
		gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss"); 
		return gsonBuilder.create();
	}
}
