package com.mvn.test.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/send")
public class SendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int memSize = 1024*1024*5;//5MB
		int totalSize = 1024*1024*50;//50MB
		int fileSize = 1024*1024*10;//10MB
		DiskFileItemFactory dfif = new DiskFileItemFactory();//FileItem객체를 사용
		dfif.setSizeThreshold(memSize);//메모리에다가 5MB를 기억하고 파일을 write해줌
		dfif.setRepository(new File(System.getProperty("java.io.tmpdir")));//잠깐 기억할 공간
		ServletFileUpload sfu = new ServletFileUpload(dfif);
		sfu.setFileSizeMax(fileSize);
		sfu.setFileSizeMax(totalSize);//이 서버에 올릴 수 있는 총 양과 파일 양을 정함 
		if(ServletFileUpload.isMultipartContent(request)) {
			try {
			List<FileItem> fList = sfu.parseRequest(request);//서비스에서 리스트로 주면 힘듦 
			for(FileItem fi:fList) {
				String key = fi.getFieldName();//name
				if(fi.isFormField()) {
					String value = fi.getString("utf-8");
					System.out.println(key+":"+value);
				}else {
					String path = "C:\\ict\\mvn-test\\WebContent\\img";//여기 안에다가 파일아이템객체를 저장
					String fileName = fi.getName();//업로드한 파일의 이름
					File targetFile = new File(path+"\\"+fileName);//그 폴더에 업로드한 파일
					fi.write(targetFile);
				}
			}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else {
			throw new ServletException("파일 형식 잘못됨");
		}
	}
}

