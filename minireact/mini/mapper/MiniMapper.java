package com.react.mini.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.react.mini.data.MiniData;

@Mapper
public interface MiniMapper {

    //Show full master list
	public List<MiniData> getAll();
	
    
    //count number of times a certain code exists in master list
	// public int checkCode(MiniData item);
    public int checkCode(String mst_cd);


    //delete entry from master list
	public void deleteMst(String mst_cd);

     //grab one entry/row
     Map<String, String> oneRowMst(String mst_cd);
     
    //update entry in master list
	public void updateMst(String mst_cd, String mst_name, String mst_notes);
    //public void updateMst(MiniData item);

    //add new entry to master list
    //public void insertMst(MiniData item);
    public void insertMst(String mst_cd, String mst_name, String mst_notes);

    //search for entry in master list
	public List<Map<String, String>> searchMst(String searchword) throws Exception;

    //Show details list
	/*public List<MiniVueData> getDtlList();*/
    

    public List<Map<String, String>> getDtlList(String mst_cd);

    
    public Map<String, String> oneRowDtl(String mst_cd, String dtl_cd);

    //public void updateDtl(MiniData item);
    public void updateDtl(String mst_cd, String dtl_cd, String dtl_name, String dtl_notes);

    //delete entry from detail list
	public void deleteDtl(String mst_cd, String dtl_cd);

    //search for entry in detail list
	public List<Map<String, String>> searchDtl(String dtlsearchword) throws Exception;

    //count number of times a certain code exists in detail list
	public int checkDtlCode(String mst_cd, String dtl_cd);

    //add new entry to details list
    //public void insertDtl(MiniData item);
    public void insertDtl (String mst_cd, String dtl_cd, String dtl_name, String dtl_notes);


    public List<MiniData> getAllCombined();


    public List<Map<String, String>> searchAll(String searchword, String searchoptions) throws Exception;


 


 




    
}
