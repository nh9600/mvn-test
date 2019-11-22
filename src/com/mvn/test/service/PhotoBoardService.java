package com.mvn.test.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

public interface PhotoBoardService {
	public List<Map<String,String>> selectPhotoBoardList ();
	public Map<String,String> selectPhotoBoard(Map<String,Object> param);
	public Map<String,String> insertPhotoBoard(Map<String,Object> param);
	public Map<String,String> deletePhotoBoard(Map<String,Object> param);

}
