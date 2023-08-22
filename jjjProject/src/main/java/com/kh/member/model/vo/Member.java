package com.kh.member.model.vo;

import java.sql.Date;

public class Member {
	
	 private int memberNo;
	 private String memberName;
	 private String memberId;
	 private String memberPwd;
	 private String phone;
	 private String email;
	 private String nickname;
	 private String status;
	 private Date enrollDate;
	 private String profileImg;
	 
	 
	public Member() {}


	public Member(int memberNo, String memberName, String memberId, String memberPwd, String phone, String email,
			String nickname, String status, Date enrollDate, String profileImg) {
		super();
		this.memberNo = memberNo;
		this.memberName = memberName;
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.phone = phone;
		this.email = email;
		this.nickname = nickname;
		this.status = status;
		this.enrollDate = enrollDate;
		this.profileImg = profileImg;
	}


	public int getMemberNo() {
		return memberNo;
	}


	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}


	public String getMemberName() {
		return memberName;
	}


	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}


	public String getMemberId() {
		return memberId;
	}


	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}


	public String getMemberPwd() {
		return memberPwd;
	}


	public void setMemberPwd(String memberPwd) {
		this.memberPwd = memberPwd;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getNickname() {
		return nickname;
	}


	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Date getEnrollDate() {
		return enrollDate;
	}


	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}


	public String getProfileImg() {
		return profileImg;
	}


	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}


	@Override
	public String toString() {
		return "Member [memberNo=" + memberNo + ", memberName=" + memberName + ", memberId=" + memberId + ", memberPwd="
				+ memberPwd + ", phone=" + phone + ", email=" + email + ", nickname=" + nickname + ", status=" + status
				+ ", enrollDate=" + enrollDate + ", profileImg=" + profileImg + "]";
	}
	
    
	
	
}
