package com.Mintimate.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.Mintimate.model.Employee;

public class EmpDao {

	//
	public static List<Employee> selectAll(String str) {
		String mString = "%" + str + "%";
		String sql = "select * from emp where empNo like ? or empName like ? or empSex like ? or empEdu like ? or empAge like ? or depName like ? or position like ? or salary like ?";
		ResultSet rs = CommonDao.query(sql, mString, mString, mString, mString, mString, mString, mString, mString);
		List<Employee> list = new ArrayList<Employee>();
		try {
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setEmpNo(rs.getString(1));
				emp.setEmpName(rs.getString(2));
				emp.setEmpSex(rs.getString(3));
				emp.setEmpEdu(rs.getString(4));
				emp.setEmpAge(rs.getString(5));
				emp.setDepName(rs.getString(6));
				emp.setPosition(rs.getString(7));
				emp.setSalary(rs.getString(8));
				list.add(emp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	public static void deleteEmp(String mStr) {
		String sql = "delete from emp where empNo=?";
		CommonDao.update(sql, mStr);
	}

	public static void updateEmp(Employee emp) {
		String sql = "update emp set empName=?,empSex=?,empEdu=?,empAge=?,depName=?,position=?,salary=? where empNo=?";
		CommonDao.update(sql, emp.getEmpName(), emp.getEmpSex(), emp.getEmpEdu(), emp.getEmpAge(), emp.getDepName(),
				emp.getPosition(), emp.getSalary(), emp.getEmpNo());
	}

	public static void addEmp(Employee emp) {
		// TODO Auto-generated method stub
		String sql = "insert into emp values(?,?,?,?,?,?,?,?)";
		CommonDao.update(sql, emp.getEmpNo(), emp.getEmpName(), emp.getEmpSex(), emp.getEmpEdu(), emp.getEmpAge(),
				emp.getDepName(), emp.getPosition(), emp.getSalary());
	}
}
