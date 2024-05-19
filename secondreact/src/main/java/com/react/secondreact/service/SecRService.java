package com.react.secondreact.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.react.secondreact.data.SecRData;
import com.react.secondreact.mapper.SecRMapper;


@Service
@Component
public class SecRService {
    
@Autowired
private SecRMapper mapper;

public List<SecRData> getAll() {
    return mapper.getAll();
}

/* public int checkPassword(String enterid, String enterpw) {
    return mapper.checkPassword(enterid, enterpw);
    
}*/

public int checkPassword(String id, String pw) {
    return mapper.checkPassword(id, pw);

}

// public void detailView(SecRData data) {
//     mapper.detailView(data);
// }
public Map<String, String> getOneBoard(String boardid) {
    return  mapper.getOneBoard(boardid);
}

public List<Map<String, String>> getComments(String boardid) {
    List<Map<String, String>> commlist = mapper.getComments(boardid);
    return commlist;
}

public void updateBoard(String boardid, String title, String content) {
    mapper.updateBoard(boardid, title, content);
}


public void deleteBoard(int boardid) {
    mapper.deleteBoard(boardid);
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

// public void addComment(int boardid, String comm_content, String reg_id) {
//     mapper.addComment(boardid, comm_content, reg_id);
// }
public void addComment(Map<String, String> newcomment) {
    mapper.addComment(newcomment);
}

public void insertBoard(String title, String content, String reg_id) {
     mapper.insertBoard(title, content, reg_id);
}

public int checkUser(String id) {
    return mapper.checkUser(id);
}

//  public void register(SecVueData data) {
//     mapper.register(data);
// }

public void register(Map<String, String> newuser) {
    mapper.register(newuser);
}

public Map<String, String> getOneComment(String commentsid) {
    return  mapper.getOneComment(commentsid);
}

public void updateComment(String commentsid, String comm_content) {
    mapper.updateComment(commentsid, comm_content);
}

}
