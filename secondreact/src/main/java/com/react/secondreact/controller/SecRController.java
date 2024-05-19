package com.react.secondreact.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.react.secondreact.data.SecRData;
import com.react.secondreact.service.SecRService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
@Controller
@Component
@RequestMapping("/api")
@CrossOrigin("http://localhost:3006/")
public class SecRController {
    
@Autowired
private SecRService service;

//show posts list 
	@RequestMapping(value="/list" , method = {RequestMethod.GET, RequestMethod.POST})
	public List<SecRData> getAll(){
		List<SecRData> list = service.getAll();
	    return list;
	}

	@RequestMapping(value="/login" , method = {RequestMethod.GET, RequestMethod.POST})
	   private String sessionRequest(HttpSession session, 
	   //@RequestBody SecRData data, 
	//    @RequestParam(value ="id", required = false) String id, 
	//    @RequestParam(value ="pw", required = false) String pw, 
	   //@RequestParam(value ="error", required = false) Boolean error,
	   HttpServletRequest request, HttpServletResponse response) throws Exception{
		 
		//SecVueData data = new SecVueData() ;
		String id = request.getParameter("enterid");
		String pw = request.getParameter("enterpw");

		 service.checkPassword(id, pw);
		 
		 if (service.checkPassword(id, pw) == 1) {
		 
		 	//session.setAttribute("userid", id);
			System.out.println("onlogin setAttribute:  " + id);
			//error = false;
		
	     	// data.setTest_result("success");
			//data.setId(id);
			//data.setPw(pw);
			//return data;
			return "success";
			
	      }
		 else {
			//error = true;
			//data.setTest_result("wrongpw");
			return "nomatch";
		 }
	   }

	   @RequestMapping(value="/detailView" , method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT}) 
        // private SecRData detailView(@RequestBody SecRData data, 
		// HttpServletRequest request, HttpServletResponse response){  

		// HttpSession session = request.getSession();
		// String userid = session.getAttribute("id")+"";


        //     service.detailView(data);  
        //     return data;  
        // }   
		public Map<String, String> detailView(@RequestParam(value="boardid") String boardid,  HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> oneBoard = service.getOneBoard(boardid);

		// HttpSession session = request.getSession();
		// String userid = session.getAttribute("id")+"";

		return oneBoard;
		}

	   @RequestMapping(value="/getComments" , method = {RequestMethod.GET, RequestMethod.POST})
	    public List<Map<String, String>> getComments(@RequestParam(value="boardid") String boardid, 
		HttpServletRequest request, HttpServletResponse response) throws Exception{  						 
		 
		// HttpSession session = request.getSession();
		// String userid = session.getAttribute("id")+"";
 
	     List<Map<String, String>> comlist = service.getComments(boardid);
	     return comlist;
	    }

		@RequestMapping(value="/updateBoardForm" , method = {RequestMethod.GET, RequestMethod.POST}) 
		public Map<String, String> oneRow(@RequestParam(value="boardid") String boardid) {
		Map<String, String> boardRow = service.getOneBoard(boardid);
		return boardRow;}

		@RequestMapping(value="/updateBoard" , method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT}) 
		public String updateBoard (HttpServletRequest req) throws Exception {

			 
		// HttpSession session = req.getSession();
		// String userid = session.getAttribute("id")+"";
			
			 String boardid = req.getParameter("boardid");
			 String title = req.getParameter("title");
			 String content = req.getParameter("content");
			 

			 System.out.println(boardid);
			 System.out.println(title);
			 System.out.println(content);

			 service.updateBoard(boardid, title, content);
			
			 return "success";
			}

		
		@RequestMapping(value="/updateCommentForm" , method = {RequestMethod.GET, RequestMethod.POST}) 
		public Map<String, String> oneComment(@RequestParam(value="commentsid") String commentsid) {
		Map<String, String> oneComment = service.getOneComment(commentsid);
		return oneComment;}


		@RequestMapping(value="/updateComment" , method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT}) 
		public List<Map<String, String>> updateComment (HttpServletRequest req) throws Exception {
			
			String boardid = req.getParameter("boardid");
			String commentsid = req.getParameter("commentsid");
			String comm_content = req.getParameter("comm_content");
		
 
		// HttpSession session = req.getSession();
		// String userid = session.getAttribute("id")+"";

			service.updateComment(commentsid, comm_content);
			
			List<Map<String, String>> comlist = service.getComments(boardid);
	     	return comlist;
			}



		@RequestMapping(value="/deleteBoard" , method = {RequestMethod.GET, RequestMethod.POST})
		    public List<SecRData> deleteBoard(
				//@RequestBody SecVueData data, 
			@RequestParam(value="boardid") int boardid, 
			//@RequestParam(value="reg_id") String reg_id,
			HttpServletRequest request, HttpServletResponse response){

				// HttpSession session = request.getSession();
				// String userid = session.getAttribute("id")+"";

				//System.out.println(userid);
				System.out.println(boardid);
				/*if(!userid.equals(data.getReg_id())){
				data.setTest_result("notauthor");
				
				} else{
				data.setTest_result("success");	*/
		       // service.deletePost(data.getBoardid()); 	
			    service.deleteBoard(boardid);
				List<SecRData> list = service.getAll();
				
	    		return list;
				//}	 
				     
		    }

			 @RequestMapping(value="/searchPosts" , method = {RequestMethod.GET, RequestMethod.POST})
		 public List<Map<String, String>> searchPosts(@RequestParam(value ="paramword", required = false) String paramword, HttpServletRequest request, HttpServletResponse response) throws Exception {
			 		     
			List<Map<String, String>> postsearch = service.searchPosts(paramword);
            return postsearch; 
		 }

		 @RequestMapping(value="/searchComments" , method = {RequestMethod.GET, RequestMethod.POST})
		 public List<Map<String, String>> searchComments(@RequestParam(value ="paramword", required = false) String paramword, HttpServletRequest request, HttpServletResponse response) throws Exception {
			 		     
			List<Map<String, String>> commsearch = service.searchComments(paramword);
            return commsearch; 
		 }

		 @RequestMapping(value="/deleteComment" , method = {RequestMethod.GET, RequestMethod.POST})
		    public List<Map<String, String>> deleteComment(@RequestParam(value="boardid") String boardid, 
			@RequestParam(value="commentsid") int commentsid, 
			HttpServletRequest request, HttpServletResponse response){

				// HttpSession session = request.getSession();
				// String userid = session.getAttribute("id")+"";

			    service.deleteComm(commentsid); 

				List<Map<String, String>> comlist = service.getComments(boardid);
	     		return comlist;     
		    }


			@RequestMapping(value="/addComment" , method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})  
			private String addComment(@RequestParam(value="boardid") String boardid, HttpServletRequest req){  
		
				// HttpSession session = req.getSession();
				// String userid = session.getAttribute("id")+"";

				// int boardid = Integer.parseInt(req.getParameter("boardid"));
			    // String boardid = req.getParameter("boardid");
				 String comm_content = req.getParameter("comm_content");
				//  String comreg_id = session.getAttribute("id")+"";
				String comreg_id = req.getParameter("comreg_id");

				 Map<String, String> newcomment = new HashMap<>();

				newcomment.put("boardid", boardid);
				newcomment.put("comm_content", comm_content);
				newcomment.put("comreg_id", comreg_id);
				System.out.println("comreg_id===> " + comreg_id);
				
			service.addComment(newcomment);  
			return "success";
			
	}
	

			// @RequestMapping(value="/addPost" , method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})  
			// private SecRData addBoard(@RequestBody SecRData data){  
		
			// service.addBoard(data);  
			// return data;

			// }

			
		@RequestMapping(value="/insertBoard" , method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})  
		private String insertBoard(HttpServletRequest req) throws Exception {  

			// HttpSession session = req.getSession();
			// String userid = session.getAttribute("userid")+"";

				 String title = req.getParameter("title");
				 String content = req.getParameter("content");
				 String reg_id = req.getParameter("reg_id");
				// String reg_id = session.getAttribute("id")+"";
				
				
			     System.out.println("on insert -> reg_id  " + reg_id);
				 
				//  System.out.println(mst_notes);
						
			service.insertBoard(title, content, reg_id);  
			return "success";
			
		}  


			// @RequestMapping(value="/logout" , method = {RequestMethod.GET, RequestMethod.POST})
	  		// public void sessionEnd(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		 
		 	// 	session.removeAttribute("id");
			// 	session.invalidate();
			// }


			@RequestMapping(value="/registerUser" , method = {RequestMethod.GET, RequestMethod.POST})
			//private SecVueData register(@RequestBody SecVueData data, 
			public String register(
			@RequestParam(value="pw") String pw,
			@RequestParam(value="cpw") String cpw,
			@RequestParam(value="id") String id,
			@RequestParam(value="name") String name,
			//@RequestParam(value="test_result") String test_result,
			HttpServletRequest request, HttpServletResponse response) throws Exception{

				//String enterid = request.getParameter("enterid");
			//	String entername = request.getParameter("entername");
			//	String enterpw = request.getParameter("enterpw");
			//	String confirmpw = request.getParameter("confirmpw");
				//String test_result = request.getParameter("test_result");
		
				System.out.println("password: " + pw);
				System.out.println("confirmpw: " + cpw);

				if(!pw.equals(cpw)) {
					System.out.println("password no match : " + pw);
					//test_result = "pwnomatch";
					//return test_result;
					return "pwnomatch";
				}else {
					System.out.println("password match: " + pw);
					Map<String, String> newuser = new HashMap<>();
					newuser.put("id", id);
					newuser.put("name", name);
					newuser.put("pw", pw);

					service.checkUser(id);
					if (service.checkUser(id) == 0) {
					
					service.register(newuser);
					//test_result = "success";
					//return test_result;
					return "success";
					} else {
					//test_result = "usertaken";
					//return test_result; 
					return "usertaken";
					}
				}

		}

}
