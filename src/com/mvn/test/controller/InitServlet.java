package com.mvn.test.controller;

import java.io.InputStream;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.mvn.test.dao.impl.UserInfoDAOImpl;

@WebServlet(name="Init",urlPatterns= {"/init"},loadOnStartup = 1)
public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static SqlSessionFactory ssf;
	static {
		String path = "/config/mybatis-config.xml";
		InputStream is = UserInfoDAOImpl.class.getResourceAsStream(path);
		ssf = new SqlSessionFactoryBuilder().build(is);//공장을 지어야하니까 빌더
		//하나만 있으면 되고 먼저 준비해두기 
	}
	public void init() {
	}
	
	public static SqlSession getSqlSession() {//얘만 호출할 수 있으면 됨 
		return ssf.openSession();
	}

}
