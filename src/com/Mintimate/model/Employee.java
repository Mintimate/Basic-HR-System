package com.Mintimate.model;

public class Employee {
	private String empNo;
	private String empName;
	private String empSex;
	private String empEdu;
	private String empAge;
	private String depName;
	private String position;
	private String salary;

	public Employee() {

	}

	public Employee(String empNo, String empName, String empSex, String empEdu, String empAge, String depName,
			String position, String salary) {
		this.empNo = empNo;
		this.empName = empName;
		this.empSex = empSex;
		this.empEdu = empEdu;
		this.empAge = empAge;
		this.depName = depName;
		this.position = position;
		this.salary = salary;
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpSex() {
		return empSex;
	}

	public void setEmpSex(String empSex) {
		this.empSex = empSex;
	}

	public String getEmpEdu() {
		return empEdu;
	}

	public void setEmpEdu(String empEdu) {
		this.empEdu = empEdu;
	}

	public String getEmpAge() {
		return empAge;
	}

	public void setEmpAge(String empAge) {
		this.empAge = empAge;
	}

	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [empNo=" + empNo + ", empName=" + empName + ", empSex=" + empSex + ", empEdu=" + empEdu
				+ ", empAge=" + empAge + ", depName=" + depName + ", position=" + position + ", salary=" + salary + "]";
	}

}
