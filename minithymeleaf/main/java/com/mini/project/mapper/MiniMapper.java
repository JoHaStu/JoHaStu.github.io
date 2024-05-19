package com.mini.project.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
public interface  MiniMapper {

	//Show full master list
	public List<Map<String, String>> getAll() throws Exception;
	
	//insert data into master list
    void insertMst(Map<String, String> insertData);

    //grab one entry/row
    Map<String, String> oneRowMst(String mst_cd);
	
	//update entry in master list
	void updateMst(String mst_cd, String mst_name, String mst_notes);

	//delete entry from master list
	public void deleteMst(String mst_cd);
	
	//count number of times a certain code exists in master list
	public int checkCode(String mst_cd);
	
	//search for entry in master list
	public List<Map<String, String>> searchMst(String search) throws Exception;
	
	/*
	 * ==============================================
	 * DETAILS LIST
	 * ==============================================
	 * */

	//Show full details list
	public List<Map<String, String>> getAllDtl(String mst_cd);

	//insert data into details list
	public void insertDtl(Map<String, String> insertData);

	//check that master and details code don't double
	public int checkDtlCode(String mst_cd, String dtl_cd);

	//search for entry in details list
	public List<Map<String, String>> searchDtl(String dtlsearch, String mst_cd) throws Exception;

	//grab one entry/row by mst_cd AND dtl_cd
	public Map<String, String> oneRowDtl(String mst_cd, String dtl_cd);

	//update entry in details list
	void updateDtl(String mst_cd_dtl, String dtl_cd, String dtl_name, String dtl_notes);

	//search for entry in details list
	public void deleteDtl(String mst_cd, String dtl_cd);

	public List<Map<String, String>> searchAll(String searchword);
	
	
}
