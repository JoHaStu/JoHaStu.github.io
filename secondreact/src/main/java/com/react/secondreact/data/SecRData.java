package com.react.secondreact.data;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import lombok.Data;

@EntityScan
@Data
public class SecRData {
    
    public String id;
	private String name;
	private String pw;
	private String cpw;
	
	private int boardid;
	private String title;
	private String content;
	private String reg_id;
	private String reg_date;

	private int commentsid;
	private String comm_content;
	private String comreg_id;

	private String test_result;

	public SecRData(String id, String name, String pw, String cpw, int boardid, String title, String content, String reg_id,
            		  String reg_date, int commentsid, String comm_content, String comreg_id, String test_result) {
        this.id = id;
        this.name = name;
        this.pw = pw;
		this.cpw = cpw;
        this.boardid = boardid;
        this.title = title;
        this.content = content;
        this.reg_id = reg_id;
        this.reg_date = reg_date;
        this.commentsid = commentsid;
        this.comm_content = comm_content;
		this.comreg_id = comreg_id;
		this.test_result = test_result;
    }
    


    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getCpw() {
		return cpw;
	}
	public void setCpw(String cpw) {
		this.cpw = cpw;
	}
	public int getBoardid() {
		return boardid;
	}
	public void setBoardid(int boardid) {
		this.boardid = boardid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getReg_id() {
		return reg_id;
	}
	public void setReg_id(String reg_id) {
		this.reg_id = reg_id;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public int getCommentsid() {
		return commentsid;
	}
	public void setCommentsid(int commentsid) {
		this.commentsid = commentsid;
	}
	public String getComm_content() {
		return comm_content;
	}
	public void setComm_content(String comm_content) {
		this.comm_content = comm_content;
	}
	public String getComreg_id() {
		return comreg_id;
	}
	public void setComreg_id(String comreg_id) {
		this.comreg_id = comreg_id;
	}
	public String getTest_result() {
		return test_result;
	}
	public void setTest_result(String test_result) {
		this.test_result = test_result;
	}

}
