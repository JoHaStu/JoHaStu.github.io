package com.vueproject.minivue.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.vueproject.minivue.data.MiniVueData;
import com.vueproject.minivue.mapper.MiniVueMapper;

@Service
@Component
public class MiniVueService {

@Autowired 
	private MiniVueMapper mapper;

   //show full master list
	public List<MiniVueData> getAll() {
		return mapper.getAll();
	}

	//insert data into master list
	//public void insertMst(Map<String, String> insertData) {
		//mapper.insertMst(insertData);	
	//}
	public int checkCode(MiniVueData item) {
		return mapper.checkCode(item); 	 
	}

	//delete entry from master list
	public void deleteMst(String mst_cd) {
	mapper.deleteMst(mst_cd);
	}

	/*//update entry in master list
	public void updateMst(String mst_cd, String mst_name, String mst_notes) {
	mapper.updateMst(mst_cd, mst_name, mst_notes);
   }*/

    public void updateMst(MiniVueData item){  
		mapper.updateMst(item);  
	}

    public void insertMst(MiniVueData item){  
		mapper.insertMst(item);
	}  

	//search for entry in master list
	public List<Map<String, String>> searchMst(String searchword) throws Exception {
		 List<Map<String, String>> searchMst = mapper.searchMst(searchword);
			return searchMst;
			
	}


	//show details list
	/*public List<MiniVueData> getDtlList(String mst_cd) {
		return mapper.getDtlList();
	}*/
	public List<Map<String, String>> getDtlList(String mst_cd) throws Exception {
		List<Map<String, String>> listdtl = mapper.getDtlList(mst_cd);
		return listdtl;
	}

	public void updateDtl(MiniVueData item){  
		mapper.updateDtl(item);  
	}

	//delete entry from detail list
	public void deleteDtl(String mst_cd, String dtl_cd) {
	mapper.deleteDtl(mst_cd, dtl_cd);
	}

	//search for entry in detail list
	public List<Map<String, String>> searchDtl(String dtlsearchword) throws Exception {
		List<Map<String, String>> searchDtl = mapper.searchDtl(dtlsearchword);
			return searchDtl;
	}

	public int checkDtlCode(MiniVueData item) {
		return mapper.checkDtlCode(item); 	 
	}

	 public void insertDtl(MiniVueData item){  
		mapper.insertDtl(item);
	}


    public List<MiniVueData> getAllCombined() {
        return mapper.getAllCombined();
    }

	public List<Map<String, String>> searchAll(String paramword, String searchoptions) throws Exception {
		List<Map<String, String>> allsearch = mapper.searchAll(paramword, searchoptions);
			return allsearch;
	}  

}
