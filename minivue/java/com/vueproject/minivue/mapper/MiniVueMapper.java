package com.vueproject.minivue.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.vueproject.minivue.data.MiniVueData;

@Mapper
public interface MiniVueMapper {

    //Show full master list
	public List<MiniVueData> getAll();
	
    
    //count number of times a certain code exists in master list
	public int checkCode(MiniVueData item);

    //delete entry from master list
	public void deleteMst(String mst_cd);

    /*//update entry in master list
	public void updateMst(String mst_cd, String mst_name, String mst_notes);*/
    public void updateMst(MiniVueData item);

    //add new entry to master list
    public void insertMst(MiniVueData item);

    //search for entry in master list
	public List<Map<String, String>> searchMst(String searchword) throws Exception;

    //Show details list
	/*public List<MiniVueData> getDtlList();*/

    public List<Map<String, String>> getDtlList(String mst_cd);

    public void updateDtl(MiniVueData item);

    //delete entry from detail list
	public void deleteDtl(String mst_cd, String dtl_cd);

    //search for entry in detail list
	public List<Map<String, String>> searchDtl(String dtlsearchword) throws Exception;

    //count number of times a certain code exists in detail list
	public int checkDtlCode(MiniVueData item);

    //add new entry to details list
    public void insertDtl(MiniVueData item);


    public List<MiniVueData> getAllCombined();


    public List<Map<String, String>> searchAll(String paramword, String searchoptions) throws Exception;
}
