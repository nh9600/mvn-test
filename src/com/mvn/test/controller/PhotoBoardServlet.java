package com.mvn.test.controller;

import java.io.File;
import java.io.IOException;
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
import com.mvn.test.common.ServletFileUtil;
import com.mvn.test.service.PhotoBoardService;
import com.mvn.test.service.impl.PhotoBoardServiceImpl;

/**
 * Servlet implementation class PhotoBoardServlet
 */
@WebServlet(name="/PhotoBoardServlet",urlPatterns= {"/photo/*"})
public class PhotoBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private PhotoBoardService pbs = new PhotoBoardServiceImpl();
    private Gson gson = new Gson();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		//String cmd = request.getParameter(cmd);
		List<Map<String,String>> pbList = pbs.selectPhotoBoardList();
		response.getWriter().println(gson.toJson(pbList));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		Map<String,Object> param = ServletFileUtil.parseRequest(request);
		Map<String,String> rMap = pbs.insertPhotoBoard(param);
		response.getWriter().println(gson.toJson(rMap));
		System.out.println((gson.toJson(rMap)));//잘 나오는 지 테스트!!!
		
	}
	public static void main(String[] args) {
		String tmp = System.getProperty("java.io.tmpdir");
		System.out.println(tmp);
	}
	}


