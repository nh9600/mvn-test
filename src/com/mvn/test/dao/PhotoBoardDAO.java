package com.mvn.test.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.mvn.test.vo.PhotoBoardVO;

public interface PhotoBoardDAO {
	public List<Map<String,String>> selectPhotoBoardList (SqlSession ss);
	public Map<String,String> selectPhotoBoard(SqlSession ss, PhotoBoardVO pb);
	public int insertPhotoBoard (SqlSession ss, PhotoBoardVO pb);
	public int deletePhotoBoard (SqlSession ss, PhotoBoardVO pb);

}
