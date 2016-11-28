package cn.shuitian.api;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import cn.tyrael.data.net.response.BaseResponse;

public class ResponseUtil {
	public static void returnSuccess(HttpServletResponse response) throws IOException{
		BaseResponse br = new BaseResponse();
		response.getWriter().append(new Gson().toJson(br));
	}
}
