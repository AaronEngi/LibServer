package wang.tyrael.wx.mpapp;


import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import wang.tyrael.library.http.HttpDefault;
import wang.tyrael.library.log.LogAdapter;
import okhttp3.Response;

//https://mp.weixin.qq.com/debug/wxadoc/dev/api/api-login.html?t=20161122
public class MpAppLoginApi {
	public static final String UrlSession =
			"https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";
	private static final String TAG = "MpAppLoginManager";
			
	public static String getUrlSession(String code){
		return String.format(UrlSession, MpAppConstant.AppId, MpAppConstant.AppSecret, code);
	}
	
	private SessionResponse sessionResposne;
	
	/**
	 * 前端给出code，这就是登录过程.
	 * 同步方法
	 * @param code
	 */
	public SessionResponse toSession(String code){
		String url = getUrlSession(code);
		Response response= HttpDefault.get(url);
		try {
			String body = response.body().string();
			LogAdapter.d(TAG, body);
			sessionResposne = new Gson().fromJson(body, SessionResponse.class);
		} catch (JsonSyntaxException | IOException e) {
			LogAdapter.w(TAG, response.toString());
			e.printStackTrace();
			sessionResposne = null;
		}
		return sessionResposne;
	}
}
