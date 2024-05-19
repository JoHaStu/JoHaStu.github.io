package com.react.mini.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.react.mini.data.MiniData;
import com.react.mini.mapper.MiniMapper;

@Service
@Component
public class MiniService {

@Autowired 
	private MiniMapper mapper;

   //show full master list
	public List<MiniData> getAll() {
		return mapper.getAll();
	}

	//insert data into master list
	//public void insertMst(Map<String, String> insertData) {
		//mapper.insertMst(insertData);	
	//}
	// public int checkCode(MiniData item) {
	// 	return mapper.checkCode(item); 	 
	// }
	public int checkCode(String mst_cd) {
		return mapper.checkCode(mst_cd); 	 
	}

	 public int checkDtlCode(String mst_cd, String dtl_cd) {
		return mapper.checkDtlCode(mst_cd, dtl_cd);
    }

	//delete entry from master list
	public void deleteMst(String mst_cd) {
	mapper.deleteMst(mst_cd);
	}

	/*//update entry in master list
	public void updateMst(String mst_cd, String mst_name, String mst_notes) {
	mapper.updateMst(mst_cd, mst_name, mst_notes);
   }*/

   public Map<String, String> oneRowMst(String mst_cd) {
	return mapper.oneRowMst(mst_cd);
}
   
    // public void updateMst(MiniData item){  
	// 	mapper.updateMst(item);  
	// }
	public void updateMst(String mst_cd, String mst_name, String mst_notes) {
	mapper.updateMst(mst_cd, mst_name, mst_notes);
}  

    // public void insertMst(MiniData item){  
	// 	mapper.insertMst(item);
	// }  
	
	public void insertMst(String mst_cd, String mst_name, String mst_notes) {
		mapper.insertMst(mst_cd, mst_name, mst_notes);
	}


	public void insertDtl(String mst_cd, String dtl_cd, String dtl_name, String dtl_notes) {
		mapper.insertDtl(mst_cd, dtl_cd, dtl_name, dtl_notes);
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

	 public Map<String, String> oneRowDtl(String mst_cd, String dtl_cd) {
        return mapper.oneRowDtl(mst_cd, dtl_cd);
    }

	// public void updateDtl(MiniData item){  
	// 	mapper.updateDtl(item);  
	// }

	public void updateDtl(String mst_cd, String dtl_cd, String dtl_name, String dtl_notes) {
		mapper.updateDtl (mst_cd, dtl_cd, dtl_name, dtl_notes);
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

	// public int checkDtlCode(MiniData item) {
	// 	return mapper.checkDtlCode(item); 	 
	// }

	//  public void insertDtl(MiniData item){  
	// 	mapper.insertDtl(item);
	// }


    public List<MiniData> getAllCombined() {
        return mapper.getAllCombined();
    }

	public List<Map<String, String>> searchAll(String searchword, String searchoptions) throws Exception {
		List<Map<String, String>> allsearch = mapper.searchAll(searchword, searchoptions);
			return allsearch;
	}

   



   
	

}
