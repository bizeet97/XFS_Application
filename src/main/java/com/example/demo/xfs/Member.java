package com.example.demo.xfs;

public class Member {
	
	private String member_name;
	private String member_no;
	public String getMember_name() {
	 return member_name;
	}
	public void setMember_name(String member_name) {
	 this.member_name = member_name;
	}
	public String getMember_no() {
	 return member_no;
	}
	public void setMember_no(String member_no) {
	 this.member_no = member_no;
	}
	public Member(String member_name, String member_no) {
	 super();
	 this.member_name = member_name;
	 this.member_no = member_no;
	}
	public Member() {
	 super();
	 // TODO Auto-generated constructor stub
	}

	}


