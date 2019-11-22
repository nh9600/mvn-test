package com.mvn.test.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.mvn.test.controller.InitServlet;
import com.mvn.test.dao.UserInfoDAO;
import com.mvn.test.vo.UserInfoVO;

public class UserInfoDAOImpl implements UserInfoDAO {

	private SqlSessionFactory ssf;// sqlsession공장이라고 알기

	@Override
	public List<UserInfoVO> selectUserList(Map<String, String> pUser) {
		SqlSession ss = InitServlet.getSqlSession();
		try {
			return ss.selectList("UserInfo.selectUserList");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ss.close();// 오픈했으니까 클로즈 해줘야함
		}
		return null;
	}

	@Override
	public int insertUser(UserInfoVO pUser) {
		SqlSession ss = InitServlet.getSqlSession();
		try {
			int cnt = ss.insert("UserInfo.insertUser",pUser);
			ss.commit();
			return cnt;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ss.close();
		}
		return 0;
	}

	@Override
	public int updatetUser(UserInfoVO pUser) {
		SqlSession ss = InitServlet.getSqlSession();
		try {
			int cnt = ss.update("UserInfo.updateUser",pUser);
			ss.commit();
			return cnt;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ss.close();
		}
		return 0;
	}
	
	@Override
	public int deleteUser(UserInfoVO pUser) {
		SqlSession ss = InitServlet.getSqlSession();
		try {
			int cnt = ss.delete("UserInfo.deleteUser",pUser);
			ss.commit();
			return cnt;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ss.close();
		}
		return 0;
	}
	
	@Override
	public UserInfoVO selectUser(UserInfoVO pUser) {
		SqlSession ss = InitServlet.getSqlSession();
		try {
			return ss.selectOne("UserInfo.selectUser",pUser);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ss.close();
		}
		return null;
	}


	public static void main(String[] args) {
		UserInfoDAO udao = new UserInfoDAOImpl();
		UserInfoVO user = new UserInfoVO();
		//System.out.println(udao.selectUserList(null));
		user.setUiNum(87); //where문 조건으로 uiNum을 줬기 때문에 setUiNum을 해줘야함!! 
		System.out.println(udao.selectUser(user));

	}


}
