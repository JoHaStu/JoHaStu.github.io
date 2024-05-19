package com.project.third.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ThirdMapper {

	int checkPassword(String entername, String enterpw);

	public int checkUser(String username);

	void register(Map<String, String> newuser);

	List<Map<String, String>> getAll();

	public void insert(Map<String, String> newentry);

	Map<String, String> oneRow(String prodcode);

	public void update(String prodcode, String prodname, String lotno, String qty, String proddate, String enddate);

	void delete(int prodcode);

	List<Map<String, String>> search(String searchword);


	
	

}
