package com.project.second.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SecondMapper {

	public List<Map<String, String>> getAll() throws Exception;

	public int checkPassword(String enterid, String enterpw);

	public int checkUser(String enterid);

	public void register(Map<String, String> newuser);

	public void insert(Map<String, String> newentry);

	public void delete(int boardid);

	public Map<String, String> oneRow(String boardid);

	public void update(String title, String content, String boardid);

	public List<Map<String, String>> getComments(String boardid) throws Exception;


	public void deleteCom(String boardid, String commentsid);

	public void addComment(Map<String, String> newcomment);

	public List<Map<String, String>> search(String searchword);

	




}
