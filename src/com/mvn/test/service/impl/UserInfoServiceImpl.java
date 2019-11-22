package com.mvn.test.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mvn.test.dao.UserInfoDAO;
import com.mvn.test.dao.impl.UserInfoDAOImpl;
import com.mvn.test.service.UserInfoService;
import com.mvn.test.vo.UserInfoVO;

public class UserInfoServiceImpl implements UserInfoService {
	private UserInfoDAO uidao = new UserInfoDAOImpl();
	@Override
	public List<UserInfoVO> getUserList(Map<String, String> pUser) {
		return uidao.selectUserList(pUser);
	}
	@Override
	public Map<String,String> insertUser(UserInfoVO pUser) {
		Map<String,String> rMap = new HashMap<String,String>();
		rMap.put("msg","실패");
		rMap.put("result","false");
		if(uidao.insertUser(pUser)==1) {
			rMap.put("msg","성공");
			rMap.put("result","true");
		}
		return rMap;
	}
	@Override
	public Map<String,String> updatetUser(UserInfoVO pUser) {
		Map<String,String> rMap = new HashMap<String,String>();
		rMap.put("msg","실패");
		rMap.put("result","false");
		if(uidao.updatetUser(pUser)==1) {
			rMap.put("msg","성공");
			rMap.put("result","true");
		}
		return rMap;
	}
	@Override
	public Map<String,String> deleteUser(UserInfoVO pUser) {
		Map<String,String> rMap = new HashMap<String,String>();
		rMap.put("msg","실패");
		rMap.put("result","false");
		if(uidao.deleteUser(pUser)==1) {
			rMap.put("msg","성공");
			rMap.put("result","true");
		}
		return rMap;
	}
	@Override
	public UserInfoVO getUser(UserInfoVO pUser) {
		return uidao.selectUser(pUser);

	}

}
