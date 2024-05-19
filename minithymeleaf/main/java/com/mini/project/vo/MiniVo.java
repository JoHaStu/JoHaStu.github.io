package com.mini.project.vo;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import lombok.Data;

@EntityScan
@Data
public class MiniVo {

	// master table (TB_mst) variables:
	private String mst_cd;
	private String mst_name;
	private String mst_notes;
	private String reg_date;
	private String upd_date;

	// details table (TB_dtl) variables
	private String dtl_cd;
	private String dtl_name;
	private String dtl_notes;
	
	public void MstEntry(String mst_cd, String mst_name, String mst_notes, String reg_date, String upd_date) {
		this.mst_cd = mst_cd;
		this.mst_name = mst_name;
		this.mst_notes = mst_notes;
		this.reg_date = reg_date;
		this.upd_date = upd_date;
	}
	
	public String getMst_cd() {
		return mst_cd;
	}
	public void setMst_cd(String mst_cd) {
		this.mst_cd = mst_cd;
	}
	public String getMst_name() {
		return mst_name;
	}
	public void setMst_name(String mst_name) {
		this.mst_name = mst_name;
	}
	public String getMst_notes() {
		return mst_notes;
	}
	public void setMst_notes(String mst_notes) {
		this.mst_notes = mst_notes;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public String getUpd_date() {
		return upd_date;
	}
	public void setUpd_date(String upd_date) {
		this.upd_date = upd_date;
	}
	public String getDtl_cd() {
		return dtl_cd;
	}
	public void setDtl_cd(String dtl_cd) {
		this.dtl_cd = dtl_cd;
	}
	public String getDtl_name() {
		return dtl_name;
	}
	public void setDtl_name(String dtl_name) {
		this.dtl_name = dtl_name;
	}
	public String getDtl_notes() {
		return dtl_notes;
	}
	public void setDtl_notes(String dtl_notes) {
		this.dtl_notes = dtl_notes;
	}


}
