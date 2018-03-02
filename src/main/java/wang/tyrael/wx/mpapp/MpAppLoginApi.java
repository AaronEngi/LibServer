package wang.tyrael.wx.mpapp;


import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import okhttp3.Response;
import wang.tyrael.http.HttpDefault;
import wang.tyrael.log.LogAdapter;

//https://mp.weixin.qq.com/debug/wxadoc/dev/api/api-login.html?t=20161122
public class MpAppLoginApi {
	public static final String UrlSession =
			"https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";
	private static final String TAG = "MpAppLoginApi";

	private final String appId;
	private final String appSecret;
	
	private SessionResponse sessionResposne;

	public MpAppLoginApi(String appId, String appSecret) {
		this.appId = appId;
		this.appSecret = appSecret;
	}
	
	/**
	 * 前端给出code，这就是登录过程.
	 * 同步方法
	 * @param code
	 */
	public SessionResponse requestSession(String code){
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

	private String getUrlSession(String code){
		return String.format(UrlSession, appId, appSecret, code);
	}
}
