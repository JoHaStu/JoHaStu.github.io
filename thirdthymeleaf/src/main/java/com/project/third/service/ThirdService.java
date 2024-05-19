package com.project.third.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.project.third.mapper.ThirdMapper;

@Service
@Component
public class ThirdService {

	@Autowired
	private ThirdMapper mapper;

	public int checkPassword(String entername, String enterpw) {
		return mapper.checkPassword(entername, enterpw);
	}

	public int checkUser(String username) {
		return mapper.checkUser(username);
		
	}

	public void register(Map<String, String> newuser) {
		mapper.register(newuser);
		
	}

	public List<Map<String, String>> getAll() throws Exception{
		List<Map<String, String>> all = mapper.getAll();
		return all;
	}

	public void insert(Map<String, String> newentry) {
		mapper.insert(newentry);	
	}

	public Map<String, String> oneRow(String prodcode) {
		return mapper.oneRow(prodcode);
	}

	public void update(String prodcode, String prodname, String lotno, String qty, String proddate, String enddate) {
		mapper.update(prodcode, prodname, lotno, qty, proddate, enddate);
		
	}

	public void delete(int prodcode) {
		mapper.delete(prodcode);
	}

	public List<Map<String, String>> search(String searchword) {
		List<Map<String, String>> searchresult = mapper.search(searchword);
		return searchresult;
	}
		
}

