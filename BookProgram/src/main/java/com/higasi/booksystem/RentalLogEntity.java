package com.higasi.booksystem;

/**
 * 本のレンタル情報を保持するDTOクラス
 * @author Kim
 * @since 2018/3/30
 */
public class RentalLogEntity {
	private int ID;
	private String BOOKNAME;
	private String PEOPLENAME;
	private String TIME;
	private String RENTAL_CHECK;
	
	public int getID() {
		return ID;
	}
	public void setID(int ID) {
		this.ID = ID;
	}
	public String getBOOKNAME() {
		return BOOKNAME;
	}
	public void setBOOKNAME(String BOOKNAME) {
		this.BOOKNAME = BOOKNAME;
	}
	public String getPEOPLENAME() {
		return PEOPLENAME;
	}
	public void setPEOPLENAME(String PEOPLENAME) {
		this.PEOPLENAME = PEOPLENAME;
	}
	public String getTIME() {
		return TIME;
	}
	public void setTIME(String TIME) {
		this.TIME = TIME;
	}
	public String getRENTAL_CHECK() {
		return RENTAL_CHECK;
	}
	public void setRENTAL_CHECK(String rentalType) {
		this.RENTAL_CHECK = rentalType;
	}
}
