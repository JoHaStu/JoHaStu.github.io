package com.vueproject.second.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.vueproject.second.data.SecVueData;
import com.vueproject.second.mapper.SecVueMapper;

@Service
@Component
public class SecVueService {
    
    @Autowired 
	private SecVueMapper mapper;


    public List<SecVueData> getAll() {
        return mapper.getAll();
    }

  /* public int checkPassword(String enterid, String enterpw) {
		return mapper.checkPassword(enterid, enterpw);
		
	}*/
 
    public int checkPassword(SecVueData data) {
        return mapper.checkPassword(data);
    
}

    public void detailView(SecVueData data) {
        mapper.detailView(data);
    }

    public List<Map<String, String>> getComments(int boardid) {
        List<Map<String, String>> commlist = mapper.getComments(boardid);
        return commlist;
    }

    public void updateBoard(SecVueData data) {
        mapper.updateBoard(data);
    }


    public void deletePost(int boardid) {
        mapper.deletePost(boardid);
        }
    // public void deletePost(SecVueData data) {
   //     mapper.deletePost(data);
   // }

    public List<Map<String, String>> searchPosts(String paramword) {
        List<Map<String, String>> searchPosts = mapper.searchPosts(paramword);
        return searchPosts;
    }

    public List<Map<String, String>> searchComments(String paramword) {
        List<Map<String, String>> searchComments = mapper.searchComments(paramword);
        return searchComments;
    }

    public void deleteComm(int commentsid) {
        mapper.deleteComm(commentsid);
    }

    public void addComment(SecVueData data) {
        mapper.addComment(data);
    }

    public void addBoard(SecVueData data) {
         mapper.addBoard(data);
    }

    public int checkUser(String enterid) {
        return mapper.checkUser(enterid);
    }

  //  public void register(SecVueData data) {
   //     mapper.register(data);
   // }

    public void register(Map<String, String> newuser) {
        mapper.register(newuser);
    }

   

}
