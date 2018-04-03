package com.higasi.booksystem;

/**
 * 本の情報を保持するEntityクラス
 * @author Kim
 * @since 2018/3/28
 */
public class BookListEntity {
	
	private int ID;
	private String NAME;
	private String SUBJECT;
	private String INSERT_TIME;
	private int RENTAL_CHECK;
	private int RENTAL_USERID;
	private String RENTAL_USERINFO;
	
	/**
	 * IDを取得する。
	 * @return ID
	 */
	public int getId() {
		return ID;
	}
	
	/**
	 * IDを設定する。
	 * @param id 本のID
	 */
	public void setId(int id) {
		ID = id;
	}
	public String getName() {
		return NAME;
	}
	public void setName(String name) {
		NAME = name;
	}
	public String getSubject() {
		return SUBJECT;
	}
	public void setSubject(String subject) {
		SUBJECT = subject;
	}
	public String getInsertTime() {
		return INSERT_TIME;
	}
	public void setInsertTime(String insertTime) {
		INSERT_TIME = insertTime;
	}
	public int getRentalCheck() {
		return RENTAL_CHECK;
	}
	public void setRentalCheck(int rentalCheck) {
		RENTAL_CHECK = rentalCheck;
	}
	public int getRentalUserID() {
		return RENTAL_USERID;
	}

	public void setRentalUserID(int rentalUserID) {
		RENTAL_USERID = rentalUserID;
	}
	public String getRentalUserInfo() {
		return RENTAL_USERINFO;
	}

	public void setRentalUserInfo(String rentalUserInfo) {
		RENTAL_USERINFO = rentalUserInfo;
	}
}
