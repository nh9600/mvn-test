package com.mvn.test.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.mvn.test.controller.InitServlet;
import com.mvn.test.dao.FileTestDAO;
import com.mvn.test.vo.FileTestVO;

public class FileTestDAOImpl implements FileTestDAO {

	@Override
	public int insertFileTest(SqlSession ss, Map<String, String> fileTest) {
			return ss.insert("FileTest.insertFileTest",fileTest);
	}

	@Override
	public List<FileTestVO> selectFileTestList(SqlSession ss) {
		return ss.selectList("FileTest.selectFileTestList");
	}
	public static void main(String[] args) {
		SqlSession ss = InitServlet.getSqlSession();
		FileTestDAO ft = new FileTestDAOImpl();
		System.out.println(ft.selectFileTestList(ss));
	}

}
