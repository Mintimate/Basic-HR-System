package com.Mintimate.Database;

import com.Mintimate.UI.MainFrame;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.Mintimate.tool.Print_Error.getTrace;

public class CommonDao {
	public static Log log = LogFactory.getLog(CommonDao.class);
	private static String drivername = "com.mysql.cj.jdbc.Driver";
	private static String url = "jdbc:mysql://www.mintimate.cn:3306/intimate?characterEncoding=utf-8&useSSL=true&serverTimezone=GMT";
//	private static String url = "jdbc:mysql://127.0.0.1:3306/empsal?characterEncoding=utf-8&useSSL=true&serverTimezone=GMT";
//	private static String name = "intimate";
	private static String name = "intimate";
	private static String password = "20000215";
	private static Connection connection = null;

	private CommonDao() {

	}

	/**
	 * 连接数据库
	 * 
	 * @return connection
	 */
	public static Connection openConnection() {
		try {
			Class.forName(drivername);
			connection = DriverManager.getConnection(url, name, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			log.error(getTrace(e));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(getTrace(e));
		}catch (Exception e){
			log.error(getTrace(e));
		}
		return connection;
	}

	/**
	 * 查询方法
	 * 
	 * @param sql
	 * @param objs
	 * @return
	 */
	public static ResultSet query(String sql, Object... objs) {
		ResultSet rs = null;
		try {
			if (connection == null) {
				openConnection();
			}
			PreparedStatement ps = null;
			ps = connection.prepareStatement(sql);
			if (objs != null) {
				for (int i = 0; i < objs.length; i++) {
					ps.setObject(i + 1, objs[i]);
				}
				rs = ps.executeQuery();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 * 更新方法
	 * 
	 * @param sql
	 * @param objects
	 */
	public static int update(String sql, Object... objects) {
		int result = 0;
		PreparedStatement ps = null;
		try {
			if (connection == null) {
				openConnection();
			}
			ps = connection.prepareStatement(sql);
			for (int i = 0; i < objects.length; i++) {
				ps.setObject(i + 1, objects[i]);
			}
			result = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

	}

	/**
	 * 删除
	 * 
	 * @param sql
	 * @param objects
	 */
	public static void delete(String sql, Object... objects) {
		PreparedStatement ps = null;
		try {
			if (connection == null) {
				openConnection();
			}
			ps = connection.prepareStatement(sql);
			if (objects != null) {
				for (int i = 0; i < objects.length; i++) {
					ps.setObject(i + 1, objects[i]);
				}
			}

			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 关闭连接
	 */
	public static void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			connection = null;
		}
	}

}
