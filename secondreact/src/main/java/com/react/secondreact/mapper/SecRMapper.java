package com.react.secondreact.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.react.secondreact.data.SecRData;


@Mapper
public interface SecRMapper {
   
    //Show full master list
	public List<SecRData> getAll();

    public Map<String, String> getOneBoard(String boardid);

    public int checkPassword(String id, String pw);

    //public void detailView(SecRData data);

    public List<Map<String, String>> getComments(String boardid);

    public void updateBoard(String boardid, String title, String content);

    public void deleteBoard(int boardid);
    
    // public void deletePost(SecRData data);

    public List<Map<String, String>> searchPosts(String paramword);

    public List<Map<String, String>> searchComments(String paramword);

    public void deleteComm(int commentsid);

    // public void addComment(int boardid, String comm_content, String reg_id);
    public void addComment(Map<String, String> newcomment);

    public void insertBoard(String title, String content, String reg_id);

    public int checkUser(String id);

   // public void register(SecRData data);

    public void register(Map<String, String> newuser);

    public Map<String, String> getOneComment(String commentsid);

    public void updateComment(String commentsid, String comm_content);



}
