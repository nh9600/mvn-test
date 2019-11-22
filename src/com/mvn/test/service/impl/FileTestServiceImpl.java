package com.mvn.test.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;
import org.apache.ibatis.session.SqlSession;

import com.mvn.test.controller.InitServlet;
import com.mvn.test.dao.FileTestDAO;
import com.mvn.test.dao.impl.FileTestDAOImpl;
import com.mvn.test.service.FileTestService;
import com.mvn.test.vo.FileTestVO;

public class FileTestServiceImpl implements FileTestService {
	private FileTestDAO ftdao = new FileTestDAOImpl();
	private String path = "C:\\ict\\mvn-test\\WebContent\\img\\";
	@Override
	public Map<String, String> insertFileTest(Map<String, Object> param) {//맵을 구분지을 필요가 있음 
		SqlSession ss = InitServlet.getSqlSession();
		try {
			String ftName = (String)param.get("ftName");
			String ftId = (String)param.get("ftId");
			FileItem fi = (FileItem)param.get("ftFile");
			Map<String,String> fileTest = new HashMap<>();
			fileTest.put("ftName",ftName);
			fileTest.put("ftId",ftId);
			fileTest.put("ftFile","/img/"+fi.getName());
			int cnt = ftdao.insertFileTest(ss,fileTest);//저장이 안될수도 있으니까 cnt
			if(cnt!=1) {//안들어갔으면 
				throw new Exception("저장 안됨!!");//강제로 예외처리
			}
			File targetFile = new File(path+fi.getName());
			fi.write(targetFile);
			ss.commit();//위에 것이 발생되면 커밋 안함 
		}catch(Exception e) {//롤백을 하기위해 서비스에서 함 
			ss.rollback();//
			e.printStackTrace();
		}finally {
			ss.close();
		}
		return null;
	}
	@Override
	public List<FileTestVO> selectFileTestList() {
		SqlSession ss = InitServlet.getSqlSession();
		try {
			return ftdao.selectFileTestList(ss);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			ss.close();
		}
		return null;
	}

}
