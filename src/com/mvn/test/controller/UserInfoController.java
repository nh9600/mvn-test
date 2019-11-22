package com.mvn.test.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.mvn.test.service.UserInfoService;
import com.mvn.test.service.impl.UserInfoServiceImpl;
import com.mvn.test.vo.UserInfoVO;

/**
 * Servlet implementation class UserInfoController
 */
@WebServlet(name="/UserInfoController",urlPatterns= {"/ajax/user/*"})
public class UserInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserInfoService uis = new UserInfoServiceImpl();
	private UserInfoVO user = new UserInfoVO();
	private Gson gson = new Gson();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getRequestURI();
		String cmd = request.getParameter("cmd");
		response.setContentType("application/json;charset=utf-8");
		PrintWriter pw = response.getWriter();
		String json = "";
		List<UserInfoVO> uiList = uis.getUserList(null);
		if("list".equals(cmd)) {
			response.getWriter().print(gson.toJson(uiList));
		}else if("view".equals(cmd)) {
			user.setUiNum(Integer.parseInt(request.getParameter("uiNum")));
			response.getWriter().print(gson.toJson(uis.getUser(user)));
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    BufferedReader br = request.getReader();
	    String str = null;
	    String json = "";
	    while((str=br.readLine())!=null) {
	    	json += str;
	    }
		response.setContentType("application/json;charset=utf-8");
		UserInfoVO pUser = gson.fromJson(json,UserInfoVO.class);
		String path = request.getRequestURI();
		String cmd = request.getParameter("cmd");
		if("signup".equals(cmd)) {
			json = gson.toJson(uis.insertUser(pUser));
		}else if("update".equals(cmd)) {
			json = gson.toJson(uis.updatetUser(pUser));
		}else if("delete".equals(cmd)){
			json = gson.toJson(uis.deleteUser(pUser));
		}
		PrintWriter pw = response.getWriter();
		pw.print(json);
		//Map<String,String> rMap = uis.insertUser(pUser);
		//json = gson.toJson(rMap);

	}

}
