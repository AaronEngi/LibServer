package cn.shuitian.api;

import javax.servlet.http.HttpServletRequest;

import cn.tyrael.library.log.LogAdapter;

public class RequestPage {
	private static final String TAG = "RequestPage";
	public final HttpServletRequest request;

	public RequestPage(HttpServletRequest request) {
		super();
		this.request = request;
	}
	
	public int getPage(){
		String sPage = request.getParameter("page");
		int page = 1;
		try{
			page = Integer.parseInt(sPage);
		}catch(NumberFormatException e){
			LogAdapter.w(TAG, "NumberFormatException");
		}
		return page;
	}
	
	public int getSize(){
		String sSize = request.getParameter("size");
		int size = 30;
		try{
			size = Integer.parseInt(sSize);
		}catch(NumberFormatException e){
			LogAdapter.w(TAG, "NumberFormatException");
		}
		return size;
	}
}
