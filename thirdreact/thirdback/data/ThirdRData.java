package com.react.thirdreact.data;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import lombok.Data;

@Data
@EntityScan
public class ThirdRData {
 
    public String id;
	private String pw;
    private String cpw;
	
	private String prodnamecat;
	private String lotnocat;

	private int prodcode;
	private String prodname;
    private String lotno;
    private int qty;
	private String proddate;
    private String enddate;

	private String test_result;

	public ThirdRData(String id, String pw, String cpw, String prodnamecat, String lotnocat, int prodcode, String prodname, String lotno,  int qty,
            		  String proddate, String enddate, String test_result) {
        this.id = id;
        this.pw = pw;
		this.cpw = cpw;
        this.prodnamecat = prodnamecat;
        this.lotnocat = lotnocat;
        this.prodcode = prodcode;
        this.prodname = prodname;
        this.lotno = lotno;
        this.qty = qty;
        this.proddate = proddate;
		this.enddate = enddate;
		this.test_result = test_result;
    }

    public String getId() {
		return id;
	}
	public void setId (String id) {
		this.id = id;
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
	public int getProdcode() {
		return prodcode;
	}
	public void setProdcode(int prodcode) {
		this.prodcode = prodcode;
	}
	public String gePprodnamecat() {
		return prodnamecat;
	}
	public void setProdnamecat(String prodnamecat) {
		this.prodnamecat = prodnamecat;
	}
	public String getLotnocat() {
		return lotnocat;
	}
	public void setLotnocat(String lotnocat) {
		this.lotnocat = lotnocat;
	}
	public String getProdname() {
		return prodname;
	}
	public void setProdname(String prodname) {
		this.prodname = prodname;
	}
	public String getLotno() {
		return lotno;
	}
	public void setLotno(String lotno) {
		this.lotno = lotno;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public String getProd_date() {
		return proddate;
	}
	public void setProd_date(String proddate) {
		this.proddate = proddate;
	}
	public String getEnd_date() {
		return enddate;
	}
	public void setEnd_date(String enddate) {
		this.enddate = enddate;
	}
	public String getTest_result() {
		return test_result;
	}
	public void setTest_result(String test_result) {
		this.test_result = test_result;
	}

}
