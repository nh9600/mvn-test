package com.mvn.test.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.google.gson.Gson;
import com.mvn.test.service.FileTestService;
import com.mvn.test.service.impl.FileTestServiceImpl;
import com.mvn.test.vo.FileTestVO;

/**
 * Servlet implementation class FileServlet
 */
@WebServlet("/file")
public class FileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private FileTestService fts = new FileTestServiceImpl();
    private Gson gson = new Gson();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getRequestURI();
		String cmd = request.getParameter("cmd");
		response.setContentType("application/json;charset=utf-8");
		String json = "";
		List<FileTestVO> ftList = fts.selectFileTestList();
		if("list".equals(cmd)) {
			response.getWriter().print(gson.toJson(ftList));
			System.out.println(gson.toJson(ftList));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int memSize = 1024*1024*5;//5MB
		int totalSize = 1024*1024*400;//50MB
		int fileSize = 1024*1024*400;//10MB
		DiskFileItemFactory dfif = new DiskFileItemFactory();
		dfif.setSizeThreshold(memSize);//메모리에다가 5MB를 기억하고 파일을 write해줌
		dfif.setRepository(new File(System.getProperty("java.io.tmpdir")));//잠깐 기억할 공간
		ServletFileUpload sfu = new ServletFileUpload(dfif);
		sfu.setFileSizeMax(fileSize);
		sfu.setFileSizeMax(totalSize);//이 서버에 올릴 수 있는 총 양과 파일 양을 정함 
		if(ServletFileUpload.isMultipartContent(request)) {
			try {
			List<FileItem> fList = sfu.parseRequest(request);
			Map<String,Object> param = new HashMap<>();
			
			for(FileItem fi:fList) {
				String key = fi.getFieldName();
				if(fi.isFormField()) {
					String value = fi.getString("utf-8");
					param.put(key,value);
					//System.out.println(key+":"+value);
				}else {
					param.put(key,fi);
				}
			}
			fts.insertFileTest(param);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else {
			throw new ServletException("파일 형식이 잘못되었습니다.");
		}
	}
	public static void main(String[] args) {
		String tmp = System.getProperty("java.io.tmpdir");
		System.out.println(tmp);
	}

}
