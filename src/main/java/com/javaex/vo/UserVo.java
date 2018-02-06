package com.javaex.vo;

public class UserVo {

	private int no;
	private String name; //변수명도 joinform.jsp에서 이름, 이메일, 패스워드,성별등등 변수이름(name=" ") 확인한 후 만들어주기
	private String email;
	private String password;
	private String gender;
	//약관동의는 체크안되면 다음페이지로 안넘어가게 하면 됨. 나중에~
	
	public UserVo() {
		
	}
	
	public UserVo(int no, String name, String email, String password, String gender) {
		this.no = no;
		this.name = name;
		this.email = email;
		this.password = password;
		this.gender = gender;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "UserVo [no=" + no + ", name=" + name + ", email=" + email + ", password=" + password + ", gender="
				+ gender + "]";
	}
	
	
	
}
