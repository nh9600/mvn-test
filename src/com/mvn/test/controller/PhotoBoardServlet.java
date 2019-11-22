package com.mvn.test.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.mvn.test.common.ServletFileUtil;
import com.mvn.test.service.PhotoBoardService;
import com.mvn.test.service.impl.PhotoBoardServiceImpl;
import com.mvn.test.vo.PhotoBoardVO;

/**
 * Servlet implementation class PhotoBoardServlet
 */
@WebServlet(name="/PhotoBoardServlet",urlPatterns= {"/photo/*"})
public class PhotoBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private PhotoBoardService pbs = new PhotoBoardServiceImpl();
    private PhotoBoardVO pbv = new PhotoBoardVO();
    private Gson gson = new Gson();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		String cmd = request.getParameter("cmd");
		if("list".equals(cmd)) {
		List<Map<String,String>> pbList = pbs.selectPhotoBoardList();
		response.getWriter().println(gson.toJson(pbList));
		}else if("view".equals(cmd)) {
			Map<String,Object> param = new HashMap<>();
			Map<String,String> pb = pbs.selectPhotoBoard(param);
			response.getWriter().println(gson.toJson(pb));
		}
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


