package com.vueproject.second.controller;

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

import com.vueproject.second.data.SecVueData;
import com.vueproject.second.service.SecVueService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
@Controller
@Component
@RequestMapping("/api")
@CrossOrigin("http://localhost:8081/")
public class SecVueController {

    @Autowired
	private SecVueService service;


    //show posts list 
	@RequestMapping(value="/list" , method = {RequestMethod.GET, RequestMethod.POST})
	public List<SecVueData> getAll(){
		List<SecVueData> list = service.getAll();
	    return list;
	}

	@RequestMapping(value="/login" , method = {RequestMethod.GET, RequestMethod.POST})
	   private SecVueData sessionRequest(HttpSession session, 
	   @RequestBody SecVueData data, 
	  // @RequestParam(value ="enterid", required = false) String enterid, 
	 //  @RequestParam(value ="enterpw", required = false) String enterpw, 
	   //@RequestParam(value ="error", required = false) Boolean error,
	   HttpServletRequest request, HttpServletResponse response) throws Exception{
		 
		//SecVueData data = new SecVueData() ;
	
		
		 service.checkPassword(data);
		 
		 if (service.checkPassword(data) == 1) {
		 
		 	session.setAttribute("id", data.getId());
			//error = false;
		
	     	data.setTest_result("success");
			//data.setId(id);
			//data.setPw(pw);
			//return data;
			return data;
			
	      }
		 else {
			//error = true;
			data.setTest_result("wrongpw");
			return data;
		 }
	   }

	   @RequestMapping(value="/detailView" , method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT}) 
        private SecVueData detailView(@RequestBody SecVueData data, 
		HttpServletRequest request, HttpServletResponse response){  

		//HttpSession session = request.getSession();
		//String userid = session.getAttribute("id")+"";


            service.detailView(data);  
            return data;  
        }   

	   @RequestMapping(value="/getComments" , method = {RequestMethod.GET, RequestMethod.POST})
	    public List<Map<String, String>> getComments(@RequestParam(value="boardid") int boardid, 
		HttpServletRequest request, HttpServletResponse response) throws Exception{  						 
		 
		//HttpSession session = request.getSession();
		//String userid = session.getAttribute("id")+"";
 
	     List<Map<String, String>> comlist = service.getComments(boardid);
	     return comlist;
	    }

		
		@RequestMapping(value="/updateBoard" , method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT}) 
        private SecVueData updateBoard(@RequestBody SecVueData data, HttpServletRequest request, HttpServletResponse response){  
            
			//HttpSession session = request.getSession();
		//	String userid = session.getAttribute("id")+"";
			
			service.updateBoard(data);  
            return data;  
        }  

		@RequestMapping(value="/deletePost" , method = {RequestMethod.GET, RequestMethod.POST})
		    public String deletePost(
				//@RequestBody SecVueData data, 
			@RequestParam(value="boardid") int boardid, 
			//@RequestParam(value="reg_id") String reg_id,
			HttpServletRequest request, HttpServletResponse response){

				//HttpSession session = request.getSession();
				//String userid = session.getAttribute("id")+"";

				//System.out.println(userid);
				System.out.println(boardid);
				/*if(!userid.equals(data.getReg_id())){
				data.setTest_result("notauthor");
				
				} else{
				data.setTest_result("success");	*/
		       // service.deletePost(data.getBoardid()); 	
			    service.deletePost(boardid);
				//}	 
				return "/session";       
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

		 @RequestMapping(value="/deleteComm" , method = {RequestMethod.GET, RequestMethod.POST})
		    public String deleteComm(@RequestParam(value="commentsid") int commentsid, 
			HttpServletRequest request, HttpServletResponse response){

			    service.deleteComm(commentsid); 
				return "/detailview";       
		    }

			@RequestMapping(value="/addComment" , method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})  
			private SecVueData addComment(@RequestBody SecVueData data){  
		
			service.addComment(data);  
			return data;
			
	}

			@RequestMapping(value="/addPost" , method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})  
			private SecVueData addBoard(@RequestBody SecVueData data){  
		
			service.addBoard(data);  
			return data;

			}

			@RequestMapping(value="/logout" , method = {RequestMethod.GET, RequestMethod.POST})
	  		public void sessionEnd(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		 
		 		session.removeAttribute("id");
				session.invalidate();
			}


			@RequestMapping(value="/register1" , method = {RequestMethod.GET, RequestMethod.POST})
			//private SecVueData register(@RequestBody SecVueData data, 
			public String register(
			@RequestParam(value="enterpw1") String enterpw1,
			@RequestParam(value="confirmpw") String confirmpw,
			@RequestParam(value="enterid1") String enterid1,
			@RequestParam(value="entername1") String entername1,
			//@RequestParam(value="test_result") String test_result,
			HttpServletRequest request, HttpServletResponse response) throws Exception{

				//String enterid = request.getParameter("enterid");
			//	String entername = request.getParameter("entername");
			//	String enterpw = request.getParameter("enterpw");
			//	String confirmpw = request.getParameter("confirmpw");
				//String test_result = request.getParameter("test_result");
		
				System.out.println("password: " + enterpw1);
				System.out.println("confirmpw: " + confirmpw);

				if(!enterpw1.equals(confirmpw)) {
					System.out.println("password no match : " + enterpw1);
					//test_result = "pwnomatch";
					//return test_result;
					return "pwnomatch";
				}else {
					System.out.println("password match: " + enterpw1);
					Map<String, String> newuser = new HashMap<>();
					newuser.put("enterid1", enterid1);
					newuser.put("entername1", entername1);
					newuser.put("enterpw1", enterpw1);

					service.checkUser(enterid1);
					if (service.checkUser(enterid1) == 0) {
					
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

//String id = data.getId();
//String name = data.getName();
//String pw = data.getPw();
//String cpw = data.getCpw();

		/* 	service.checkUser(id);
			
			if (service.checkUser(id) == 0) {
			
			service.register(data);
			service.register()
			data.setTest_result("success");
			return data;

			} else if(service.checkUser(id) != 0){
				data.setTest_result("usertaken");
				return data;
			}

			else if(!pw.equals(cpw)) {
				data.setTest_result("pwnomatch");
				return data;
			}
			return data;*/
		}
	 

}