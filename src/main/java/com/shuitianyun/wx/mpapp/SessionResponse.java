package com.shuitianyun.wx.mpapp;

public class SessionResponse {
	public String openid;	//用户唯一标识
	public String session_key;	//会话密钥
	public int expires_in;	//会话有效期, 以秒为单位, 例如2592000代表会话有效期为30天
}
