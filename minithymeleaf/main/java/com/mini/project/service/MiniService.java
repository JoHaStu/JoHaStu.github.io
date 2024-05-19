package com.mini.project.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import com.mini.project.mapper.MiniMapper;

@Service
@Component
public class MiniService {
	
	@Autowired 
	private MiniMapper miniMapper;
	
	//show full master list
	public List<Map<String, String>> getAll() throws Exception {
		List<Map<String, String>> all = miniMapper.getAll();
		return all;
	}

	//insert data into master list
	public void insertMst(Map<String, String> insertData) {
		miniMapper.insertMst(insertData);
		
	}

	public int checkCode(String mst_cd) {
		return miniMapper.checkCode(mst_cd); 
		 
	}
	
	
	//grab one master entry/row
	public Map<String, String> oneRowMst(String mst_cd) {
		return miniMapper.oneRowMst(mst_cd);
	}
	
	//update entry in master list
	 public void updateMst(String mst_cd, String mst_name, String mst_notes) {
		 miniMapper.updateMst(mst_cd, mst_name, mst_notes);
	}

	//delete entry from master list
	public void deleteMst(String mst_cd) {
		miniMapper.deleteMst(mst_cd);
		
	}

	//search for entry in master list
	public List<Map<String, String>> searchMst(String search) throws Exception {
		List<Map<String, String>> searchMst = miniMapper.searchMst(search);
			return searchMst;
	}
	
	/*
	 * ======================================
	 * DETAILS LIST
	 * ======================================
	 * */
	
	//show full details list
		public List<Map<String, String>> getAllDtl(String mst_cd) throws Exception {
			List<Map<String, String>> all = miniMapper.getAllDtl(mst_cd);
			return all;
		}

		public void insertDtl(Map<String, String> insertData) {
			miniMapper.insertDtl(insertData);
			
		}

		public int checkDtlCode(String mst_cd, String dtl_cd) {
			return miniMapper.checkDtlCode(mst_cd, dtl_cd);
			
		}

		public List<Map<String, String>> searchDtl(String dtlsearch, String mst_cd) throws Exception {
			List<Map<String, String>> searchDtl = miniMapper.searchDtl(dtlsearch,mst_cd);
				return searchDtl;
		}
		
		//grab one detail entry/row
		public Map<String, String> oneRowDtl(String mst_cd, String dtl_cd) {
			return miniMapper.oneRowDtl(mst_cd, dtl_cd);
		}

		//update entry in details list
		public void updateDtl(String mst_cd, String dtl_cd, String dtl_name, String dtl_notes) {
			miniMapper.updateDtl(mst_cd, dtl_cd, dtl_name, dtl_notes);
			
		}

		public void deleteDtl(String mst_cd, String dtl_cd) {
			miniMapper.deleteDtl(mst_cd, dtl_cd);
			
		}

		public List<Map<String, String>> searchAll(String searchword) {
			List<Map<String, String>> searchAll = miniMapper.searchAll(searchword);
			return searchAll;
		}
		
}
