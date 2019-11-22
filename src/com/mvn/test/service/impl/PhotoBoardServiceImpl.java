package com.mvn.test.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;
import org.apache.ibatis.session.SqlSession;

import com.mvn.test.common.ServletFileUtil;
import com.mvn.test.controller.InitServlet;
import com.mvn.test.dao.PhotoBoardDAO;
import com.mvn.test.dao.impl.PhotoBoardDAOImpl;
import com.mvn.test.service.PhotoBoardService;
import com.mvn.test.vo.PhotoBoardVO;

public class PhotoBoardServiceImpl implements PhotoBoardService {
	private PhotoBoardDAO pbdao = new PhotoBoardDAOImpl();
	private String path = "C:\\ict\\mvn-test\\WebContent\\img\\";
	private PhotoBoardVO pb = new PhotoBoardVO();

	@Override
	public List<Map<String, String>> selectPhotoBoardList() {
		SqlSession ss = InitServlet.getSqlSession();
		try {
			return pbdao.selectPhotoBoardList(ss);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ss.close();
		}
		return null;
	}

	@Override
	public Map<String, String> insertPhotoBoard(Map<String, Object> param) {
		pb.setPbTitle((String)param.get("pbTitle"));
		pb.setPbContent((String)param.get("pbContent"));
		pb.setCreusr(Integer.parseInt((String)param.get("creusr")));
		String path = "";	
		Map<String,String> rMap = new HashMap<>();
		rMap.put("msg","실패");
		rMap.put("result","false");
		SqlSession ss = InitServlet.getSqlSession();
		try {
			if(param.get("pbImg1")!=null) {
				FileItem fi = (FileItem)param.get("pbImg1");
				String fileName = ServletFileUtil.saveFile(fi);
				pb.setPbImg1(fileName);
			}
			if(param.get("pbImg2")!=null) {
				FileItem fi = (FileItem)param.get("pbImg2");
				String fileName = ServletFileUtil.saveFile(fi);
				pb.setPbImg2(fileName);
			}
		int cnt = pbdao.insertPhotoBoard(ss, pb);
		if(cnt==1) {
			rMap.put("msg","성공");
			rMap.put("result","true");
		}
		ss.commit();
		}catch(Exception e) {
			ss.rollback();
			e.printStackTrace();
		}finally {
			ss.close();
		}
		//System.out.println(pb);
		return rMap;
	}
	
	@Override
	public Map<String, String> deletePhotoBoard(Map<String, Object> param) {
		PhotoBoardVO pb = new PhotoBoardVO();
		Map<String,String> rMap = new HashMap<>();
		//pb.setPbNum();
		rMap.put("msg","실패");
		rMap.put("result","false");
		SqlSession ss = InitServlet.getSqlSession();
		try {
		int cnt = pbdao.deletePhotoBoard(ss, pb);
		if(cnt==1) {
			rMap.put("msg","성공");
			rMap.put("result","true");
		}
		ss.commit();
		}catch(Exception e) {
			ss.rollback();
			e.printStackTrace();
		}finally {
			ss.close();
		}
		return rMap;
	}
	
	@Override
	public Map<String, String> selectPhotoBoard(Map<String, Object> param) {
		pb.setPbNum((Integer) param.get("pbNum"));
		SqlSession ss = InitServlet.getSqlSession();
		try {
			return pbdao.selectPhotoBoard(ss,pb);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ss.close();
		}
		return null;
	}
	

	public static void main(String[] args) {
		SqlSession ss = InitServlet.getSqlSession();
		PhotoBoardService pbs = new PhotoBoardServiceImpl();
		System.out.println(pbs.selectPhotoBoardList());
		
		String fileName = "img.jpg";
	}




}
