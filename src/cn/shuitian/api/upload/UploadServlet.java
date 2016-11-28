package cn.shuitian.api.upload;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.google.gson.Gson;

import cn.tyrael.data.net.response.BaseResponse;

public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<String> urls = new ArrayList<>();
		String host = "http://" + request.getServerName() + ":" + request.getServerPort() + "/yinji";

		// 优化一下文件的存储
		String path = request.getSession().getServletContext().getRealPath("/") + "/upload";
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload sfu = new ServletFileUpload(factory);
		sfu.setHeaderEncoding("UTF-8"); // 处理中文问题
		// sfu.setSizeMax(1024*1024); //限制文件大小

		List<FileItem> fileItems = null;
		try {
			fileItems = sfu.parseRequest(request);
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 解码请求 得到所有表单元素
		for (FileItem fi : fileItems) {
			// 有可能是 文件，也可能是普通文字
			if (fi.isFormField()) { // 这个选项是 文字
				System.out.println("表单值为：" + fi.getString());
			} else {
				// 是文件
				String fn = fi.getName();
				System.out.println("文件名是：" + fn); // 文件名
				// fn 是可能是这样的 c:\abc\de\tt\fish.jpg
				try {
					fi.write(new File(path, fn));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				urls.add(host + "/upload/" + fn);
			}
		}
		BaseResponse<List<String>> br = new BaseResponse();
		br.data = urls;
		response.getWriter().append(new Gson().toJson(br));		
	}

}
