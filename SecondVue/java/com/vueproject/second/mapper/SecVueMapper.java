package com.vueproject.second.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.vueproject.second.data.SecVueData;

@Mapper
public interface SecVueMapper {

    public List<SecVueData> getAll();

   // int checkPassword(String enterid, String enterpw);

    public int checkPassword(SecVueData data);

    public void detailView(SecVueData data);

    public List<Map<String, String>> getComments(int boardid);

    public void updateBoard(SecVueData data);

    public void deletePost(int boardid);
    
    // public void deletePost(SecVueData data);

    public List<Map<String, String>> searchPosts(String paramword);

    public List<Map<String, String>> searchComments(String paramword);

    public void deleteComm(int commentsid);

    public void addComment(SecVueData data);

    public void addBoard(SecVueData data);

    public int checkUser(String enterid);

   // public void register(SecVueData data);

    public void register(Map<String, String> newuser);

   
    
}
