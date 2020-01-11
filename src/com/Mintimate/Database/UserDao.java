package com.Mintimate.Database;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.Mintimate.model.User;
import com.Mintimate.tool.Md5;
//import com.mysql.jdbc.Connection;

public class UserDao {
	/**
	 * 查询用户
	 * */
	public static User selectUser(User user) {
		User user2 = null;
		String name = user.getName();
		String sqlString = "select * from user where name = ?";
		ResultSet rs = CommonDao.query(sqlString, name);
		
		try {
			if (rs.next()) {
				user2 = new User();
				user2.setId(rs.getInt("id"));
				user2.setName(rs.getString("name"));
				user2.setPwd(rs.getString("pwd"));
				user2.setLimit(rs.getInt("limit"));
				user2.setPin(rs.getString("pin"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user2;
	}
	
	public static int addUser(String name, String pwd, int limit,String pin){
		String sqlString = "insert into user(name, pwd, `limit`, pin) values(?, ?, ?, ?)";
		int rs = CommonDao.update(sqlString, name, pwd, limit, pin);
		return rs;
	}
	public static int UpUser(String name,String pin,String pwd) {
		String sqlString="update user set pwd=? where name=? and pin=?";
		int rs=CommonDao.update(sqlString, Md5.Md5(pwd),name,pin);
		return rs;
	}

}
