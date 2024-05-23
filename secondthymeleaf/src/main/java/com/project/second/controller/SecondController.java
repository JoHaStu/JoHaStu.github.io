package com.project.second.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.project.second.service.SecondService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@Component
public class SecondController {
	
	@Autowired(required=true)
	private SecondService service;
	
	
	@RequestMapping(value="/main" , method = {RequestMethod.GET, RequestMethod.POST})
	   public ModelAndView listAll (ModelAndView model) throws Exception {

		 List<Map<String, String>> listall = service.getAll();
	      model.addObject("list", listall);
	      model.setViewName("main");

	      return model;
	   }

	@RequestMapping(value="/loginForm" , method = {RequestMethod.GET, RequestMethod.POST})
	 public String login(){
        return "login";
    }
	
	
	@RequestMapping(value="/login" , method = {RequestMethod.GET, RequestMethod.POST})
	   public ModelAndView sessionRequest(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception{
		 ModelAndView model = new ModelAndView();
		 
		 String id = request.getParameter("enterid");
		 String pw = request.getParameter("enterpw");
		 
		 service.checkPassword(id, pw);
		 if (service.checkPassword(id, pw) == 1) {
		 
		 session.setAttribute("enterid", id);
		 
		 List<Map<String, String>> listall = service.getAll();
	     model.addObject("list", listall);
	     model.setViewName("/sessionMain"); 
	      }
		 else {
			 model.addObject("error", "wrongpw");
			 model.setViewName("login"); 
		 }
		 return model;
	   }
	
	/*---------------------------------*/
	@RequestMapping(value="/backHome" , method = {RequestMethod.GET, RequestMethod.POST})
	   public ModelAndView backHome(HttpServletRequest request, Model model2) throws Exception{
		 ModelAndView model = new ModelAndView();
		 
		 HttpSession session = request.getSession();
		 String userid = session.getAttribute("enterid")+"";
		 
		 model2.addAttribute("userid", userid);
		 
		 List<Map<String, String>> listall = service.getAll();
	     model.addObject("list", listall);
	     model.setViewName("/sessionMain"); 
	     
		 return model;
	   } 
	/*--------------------------------*/
	
	//register new user
	//get register form
	@RequestMapping(value="/registerForm" , method = {RequestMethod.GET, RequestMethod.POST})
	 public String register(){
       return "register";
   }
	
	//sign-up: enter new user details
	@RequestMapping(value="/register" , method = {RequestMethod.GET, RequestMethod.POST})
		public ModelAndView register (HttpServletRequest request) throws Exception {
		 ModelAndView model = new ModelAndView();
		
		 String id = request.getParameter("enterid");
		 String name = request.getParameter("entername");
		 String pw = request.getParameter("enterpw");
		 String cpw = request.getParameter("confirmpw");
		 
		 if(!pw.equals(cpw)) {
			 model.addObject("error", "pwdiff");
			 model.setViewName("register");
		 }
		 
		 else {
		 Map<String, String> newuser = new HashMap<>();
		 newuser.put("enterid", id);
		 newuser.put("entername", name);
		 newuser.put("enterpw", pw);
		
		 service.checkUser(id);
		 if (service.checkUser(id) == 0) {
		
		 service.register(newuser);
	     model.setViewName("login");

	      } else {
	 
			 model.addObject("error", "usertaken");
			 model.setViewName("register"); 
		 }
	   }
		return model;
	}
	
	
	@RequestMapping(value="/insertForm" , method = {RequestMethod.GET, RequestMethod.POST})
	 public String insert(){
      return "insert";
  }
	
	 @RequestMapping(value="/insert" , method = {RequestMethod.GET, RequestMethod.POST})
	   public ModelAndView insert(HttpSession session, HttpServletRequest req) throws Exception {
		 ModelAndView model = new ModelAndView();
		 
		 String title = req.getParameter("title");
		 String content = req.getParameter("content");
		 
		 String reg_id = session.getAttribute("enterid")+""; //(+"" converts to String)
	
		 Map<String, String> newentry = new HashMap<>();
		 newentry.put("title", title);
		 newentry.put("content", content);
		 newentry.put("reg_id", reg_id);
		
		 service.insert(newentry);
		 
		 List<Map<String, String>> listall = service.getAll();
	     model.addObject("list", listall);
	     
	     model.setViewName("/sessionMain"); //redirects to full list with new entry 
         return model;
	      }
	 
	 
	 @RequestMapping(value="/delete" , method = {RequestMethod.GET, RequestMethod.POST})
	    public ModelAndView delete(@RequestParam(value="boardid") int boardid, @RequestParam(value="reg_id") String reg_id, 
	    						   HttpServletRequest request, HttpServletResponse response) throws Exception{
		 	ModelAndView model = new ModelAndView();
		 	
		    HttpSession session = request.getSession();
		 	String userid = session.getAttribute("enterid")+"";
		 	
		 	if(!userid.equals(reg_id)) {
		 		 model.addObject("error", "nodelete");
		 		 
		 	 	  List<Map<String, String>> listall = service.getAll();
		          model.addObject("list", listall);
			      model.setViewName("/sessionMain");
			 }
		 	 else {
	        service.delete(boardid);
	        
	        List<Map<String, String>> listall = service.getAll();
	          model.addObject("list", listall);
		      model.setViewName("/sessionMain");
	       	 }
		 	return model;
	 }
	 
	 @RequestMapping(value="/updateForm" , method = {RequestMethod.GET, RequestMethod.POST})
	    public ModelAndView updateForm(@RequestParam(value="boardid") String boardid, @RequestParam(value="reg_id") String reg_id, Model model2,
	    						   HttpServletRequest request, HttpServletResponse response) throws Exception{ 
		 
		 ModelAndView model = new ModelAndView();
		
		 HttpSession session = request.getSession();
		 String userid = session.getAttribute("enterid")+"";
		
		  if(!userid.equals(reg_id)) {
 		  model.addObject("error", "noupdate");
 		
 	 	  List<Map<String, String>> listall = service.getAll();
          model.addObject("list", listall);
	      model.setViewName("/sessionMain");
	      }
 	      else {
 	    	
 	      Map<String, String> oneRow = service.oneRow(boardid);  
 	      
 	      model2.addAttribute("boardid", oneRow.get("boardid"));
 	      model2.addAttribute("title", oneRow.get("title"));
 	      model2.addAttribute("content", oneRow.get("content"));
 	      model2.addAttribute("reg_id", oneRow.get("reg_id"));
 	      model.setViewName("/update");
	
		  }		 
		  return model; 	
	    }


	   @RequestMapping(value="/update" , method = {RequestMethod.GET, RequestMethod.POST})
	   public ModelAndView update(HttpServletRequest request, Model model2) throws Exception {
			 ModelAndView model = new ModelAndView();
			 
			 HttpSession session = request.getSession();
			 String userid = session.getAttribute("enterid")+"";
		
			 String boardid = request.getParameter("boardid");
			 String title = request.getParameter("title");
			 String content = request.getParameter("content");
	 
			 service.update(title, content, boardid);
			 
			 List<Map<String, String>> listall = service.getAll();
			  model2.addAttribute("userid", userid);
	          model.addObject("list", listall);
		      model.setViewName("/sessionMain");
		      return model;
	   }
	   
	   @RequestMapping(value="/detailview" , method = {RequestMethod.GET, RequestMethod.POST})
	    public ModelAndView detailview(@RequestParam(value="boardid") String boardid, Model model2,
	    						   HttpServletRequest request, HttpServletResponse response) throws Exception{ 
		 
		 ModelAndView model = new ModelAndView();
		
		 HttpSession session = request.getSession();
		 String userid = session.getAttribute("enterid")+"";
		 
	     Map<String, String> oneRow = service.oneRow(boardid);  
	      
	      model2.addAttribute("boardid", oneRow.get("boardid"));
	      model2.addAttribute("title", oneRow.get("title"));
	      model2.addAttribute("content", oneRow.get("content"));
	      model2.addAttribute("reg_id", oneRow.get("reg_id"));
	      model2.addAttribute("reg_date", oneRow.get("reg_date"));
	     
	      model2.addAttribute("userid", userid);
	      
	      List<Map<String, String>> comlist = service.getComments(boardid);
	      model.addObject("comlist", comlist);
	      
	      model.setViewName("/detailview");
		  return model; 	
	    }
	   
	   
	  @RequestMapping(value="/updateFormDtl" , method = {RequestMethod.GET, RequestMethod.POST})
	    public ModelAndView updateDtl(@RequestParam(value="boardid") String boardid, @RequestParam(value="reg_id") String reg_id, Model model2,
	    						   HttpServletRequest request, HttpServletResponse response) throws Exception{ 
		 
		 ModelAndView model = new ModelAndView();
		
		 HttpSession session = request.getSession();
		 String userid = session.getAttribute("enterid")+"";
		
		  if(!userid.equals(reg_id)) {
 		  model.addObject("error", "noupdatedtl");
 		   
 		    Map<String, String> oneRow = service.oneRow(boardid);  
 		    
 		    model2.addAttribute("boardid", oneRow.get("boardid"));
			model2.addAttribute("title", oneRow.get("title"));
			model2.addAttribute("content", oneRow.get("content"));
			model2.addAttribute("reg_id", oneRow.get("reg_id"));
			model2.addAttribute("reg_date", oneRow.get("reg_date"));
			
			model2.addAttribute("userid", userid);
			
			List<Map<String, String>> comlist = service.getComments(boardid);
			model.addObject("comlist", comlist);
	      
	        model.setViewName("/detailview");
	      }
 	      else {
 	    	  Map<String, String> oneRow = service.oneRow(boardid);  
 	 	      
 	 	      model2.addAttribute("boardid", oneRow.get("boardid"));
 	 	      model2.addAttribute("title", oneRow.get("title"));
 	 	      model2.addAttribute("content", oneRow.get("content"));
 	 	      model2.addAttribute("reg_id", oneRow.get("reg_id"));
 	 	      
 	 	      model.setViewName("/update");
				
		  }		 
		  return model; 	
	  }

	  @RequestMapping(value="/deleteCom" , method = {RequestMethod.GET, RequestMethod.POST})
	    public ModelAndView deleteCom(@RequestParam(value="boardid") String boardid, @RequestParam(value="commentsid") String commentsid, 
	    							  @RequestParam(value="comreg_id") String comreg_id, 
	    						      HttpServletRequest request, HttpServletResponse response, Model model2) throws Exception{
		 	ModelAndView model = new ModelAndView();
		 	
		    HttpSession session = request.getSession();
		 	String userid = session.getAttribute("enterid")+"";
		 	
		 	
		 	if(!userid.equals(comreg_id)) {
		 		 model.addObject("error", "nodeleteCom");
		 		 
		 	 	   Map<String, String> oneRow = service.oneRow(boardid);  
	      
				      model2.addAttribute("boardid", oneRow.get("boardid"));
				      model2.addAttribute("title", oneRow.get("title"));
				      model2.addAttribute("content", oneRow.get("content"));
				      model2.addAttribute("reg_id", oneRow.get("reg_id"));
				      model2.addAttribute("reg_date", oneRow.get("reg_date"));
				      model2.addAttribute("commentsid_rtn", commentsid);
				     
				      model2.addAttribute("userid", userid);
				      
				      List<Map<String, String>> comlist = service.getComments(boardid);
				      model.addObject("comlist", comlist);
				      
				      model.setViewName("/detailview");
			 }
		 	 else {
	        	service.deleteCom(boardid, commentsid);
	        
	        	Map<String, String> oneRow = service.oneRow(boardid);  
	      
				      model2.addAttribute("boardid", oneRow.get("boardid"));
				      model2.addAttribute("title", oneRow.get("title"));
				      model2.addAttribute("content", oneRow.get("content"));
				      model2.addAttribute("reg_id", oneRow.get("reg_id"));
				      model2.addAttribute("reg_date", oneRow.get("reg_date"));
				     
				      model2.addAttribute("userid", userid);
				      
				      List<Map<String, String>> comlist = service.getComments(boardid);
				      model.addObject("comlist", comlist);
				      
				      model.setViewName("/detailview");
	       	 }
		 	return model;
	 }
	  
	  @RequestMapping(value="/addCommentForm" , method = {RequestMethod.GET, RequestMethod.POST})
	  public ModelAndView addCommentForm(@RequestParam(value="boardid") int boardid, HttpServletRequest request, HttpServletResponse response, Model model2) throws Exception{
		 
		  ModelAndView model = new ModelAndView();
		  
		  HttpSession session = request.getSession();
		  String comreg_id = session.getAttribute("enterid")+"";
		  
		  model2.addAttribute("comreg_id", comreg_id);
		  model2.addAttribute("boardid", boardid);
		  model.setViewName("comment"); 
	      return model;
	  }
	  
	  
	  
	 @RequestMapping(value="/addComment" , method = {RequestMethod.GET, RequestMethod.POST})
	   public ModelAndView addComment(@RequestParam(value="boardid") String boardid, HttpSession session, HttpServletRequest req, Model model2) throws Exception {
		 ModelAndView model = new ModelAndView();

		 String comreg_id = session.getAttribute("enterid")+""; 
		 String comm_content = req.getParameter("comm_content");
	
		 Map<String, String> newcomment = new HashMap<>();

		 newcomment.put("boardid", boardid);
		 newcomment.put("comm_content", comm_content);
		 newcomment.put("comreg_id", comreg_id);
		
		 model2.addAttribute(boardid);
		 service.addComment(newcomment);
		 
		 Map<String, String> oneRow = service.oneRow(boardid);  
	      
				      model2.addAttribute("boardid", oneRow.get("boardid"));
				      model2.addAttribute("title", oneRow.get("title"));
				      model2.addAttribute("content", oneRow.get("content"));
				      model2.addAttribute("reg_id", oneRow.get("reg_id"));
				      model2.addAttribute("reg_date", oneRow.get("reg_date"));
				     
				      model2.addAttribute("comreg_id", comreg_id);
				      
				      List<Map<String, String>> comlist = service.getComments(boardid);
				      model.addObject("comlist", comlist);
				      
				      model.setViewName("/detailview");
					  return model;
	      }
	  
	 
	 @RequestMapping(value="/logout" , method = {RequestMethod.GET, RequestMethod.POST})
	   public ModelAndView sessionEnd(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception{
		 ModelAndView model = new ModelAndView();
		 
		 session.removeAttribute("enterid");
		 session.invalidate();
		 
		  List<Map<String, String>> listall = service.getAll();
	      model.addObject("list", listall);
	      model.setViewName("main");

	      return model;
	 }
	 
	 @RequestMapping(value="/search" , method = {RequestMethod.GET, RequestMethod.POST})
	 public ModelAndView search(@RequestParam(value ="searchword", required = false) String searchword, 
			 					HttpServletRequest request, HttpServletResponse response, Model model2) throws Exception {
		 
		 ModelAndView model = new ModelAndView();	
	     
		 HttpSession session = request.getSession();
		 String userid = session.getAttribute("enterid")+"";
		 
		 List<Map<String, String>> searchresult = service.search(searchword);
		 
		 model2.addAttribute("userid", userid);
		 
		 model.addObject("list", searchresult);
		 model.setViewName("sessionMain");
		 
         return model;
         
	 }
	 
	 
	 
 }



