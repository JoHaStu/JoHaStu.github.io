package com.project.second.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.project.second.mapper.SecondMapper;

@Service
@Component
public class SecondService {
	
	@Autowired 
	private SecondMapper mapper;

	public List<Map<String, String>> getAll() throws Exception{
		List<Map<String, String>> all = mapper.getAll();
		return all;
	}

	public int checkPassword(String enterid, String enterpw) {
		return mapper.checkPassword(enterid, enterpw);
		
	}

	public int checkUser(String enterid) {
		return mapper.checkUser(enterid);
	}

	public void register(Map<String, String> newuser) {
			mapper.register(newuser);
		
	}

	public void insert(Map<String, String> newentry) {
			mapper.insert(newentry);		
	}

	public void delete(int boardid) {
		mapper.delete(boardid);
		
	}

	public Map<String, String> oneRow(String boardid) {
		return mapper.oneRow(boardid);
	}

	public void update(String title, String content, String boardid) {
		mapper.update(title, content, boardid);
		
	}

	public List<Map<String, String>> getComments(String boardid) throws Exception{
		List<Map<String, String>> comms = mapper.getComments(boardid);
		return comms;
	}

	public void deleteCom(String boardid, String commentsid) {
		mapper.deleteCom(boardid, commentsid);
		
	}

	public void addComment(Map<String, String> newcomment) {
			mapper.addComment(newcomment);
	}

	public List<Map<String, String>> search(String searchword) {
		List<Map<String, String>> searchresult = mapper.search(searchword);
		return searchresult;
	}
	
	
	}
