package com.Mintimate.model;

public class salary {
	private String empName;
	   private String bonus;

	   public salary(){
		  
	   }
	   public salary(String empName,int bonus){
		   this.empName=empName;
		   this.bonus=empName;
	   }
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getBonus() {
		return bonus;
	}
	public void setBonus(String bonus) {
		this.bonus = bonus;
	}
	   
}
