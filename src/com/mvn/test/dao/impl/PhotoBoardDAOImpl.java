package com.mvn.test.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.mvn.test.controller.InitServlet;
import com.mvn.test.dao.PhotoBoardDAO;
import com.mvn.test.service.PhotoBoardService;
import com.mvn.test.service.impl.PhotoBoardServiceImpl;
import com.mvn.test.vo.PhotoBoardVO;

public class PhotoBoardDAOImpl implements PhotoBoardDAO {

	@Override
	public List<Map<String, String>> selectPhotoBoardList(SqlSession ss) {
		return ss.selectList("PhotoBoard.selectPhotoBoardList");
	}
	
	@Override
	public int insertPhotoBoard(SqlSession ss, PhotoBoardVO pb) {
		return ss.insert("PhotoBoard.insertPhotoBoard",pb);
	}

	@Override
	public int deletePhotoBoard(SqlSession ss, PhotoBoardVO pb) {
		return ss.delete("PhotoBoard.deletePhotoBoard",pb);
	}
	
	@Override
	public Map<String, String> selectPhotoBoard(SqlSession ss,PhotoBoardVO pb) {
		return ss.selectOne("PhotoBoard.selectPhotoBoard",pb);
	}
	
	public static void main(String[] args) {
		SqlSession ss = InitServlet.getSqlSession();
		PhotoBoardDAO pbdao = new PhotoBoardDAOImpl();
		PhotoBoardVO vo = new PhotoBoardVO();
		vo.setPbNum(1);
		System.out.println(pbdao.selectPhotoBoard(ss,vo));
	}






}
