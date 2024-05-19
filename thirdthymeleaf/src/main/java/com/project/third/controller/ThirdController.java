package com.project.third.controller;

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

import com.project.third.service.ThirdService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@Component
public class ThirdController {

	@Autowired(required=true)
	private ThirdService service;
	
	@RequestMapping(value="/home" , method = {RequestMethod.GET, RequestMethod.POST})
	   public String home() {
		return "home";
	   }	

	@RequestMapping(value="/login" , method = {RequestMethod.GET, RequestMethod.POST})
	   public ModelAndView sessionRequest(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception{
		 ModelAndView model = new ModelAndView();
		 
		 String uname = request.getParameter("entername");
		 String pw = request.getParameter("enterpw");
		 
		 service.checkPassword(uname, pw);
		 if (service.checkPassword(uname, pw) == 1) {
		 
		 session.setAttribute("entername", uname);
		
		 List<Map<String, String>> listall = service.getAll();
	     model.addObject("list", listall);
	     model.setViewName("/sessionHome"); 
	      }
		 else {
			 model.addObject("error", "wrongpw");
			 model.setViewName("home");
		 }
		return model; 
	   }
	
	@RequestMapping(value="/reghome" , method = {RequestMethod.GET, RequestMethod.POST})
	   public String reghome() {
		return "reghome";
	   }
	
	
	@RequestMapping(value="/register" , method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView register (HttpServletRequest request) throws Exception {
	 ModelAndView model = new ModelAndView();
	
	 String username = request.getParameter("entername");
	 String pw = request.getParameter("enterpw");
	 String cpw = request.getParameter("confirmpw");
	 
	 if(!pw.equals(cpw)) {
		 model.addObject("error", "pwdiff");
		 model.setViewName("reghome");
	 }
	 
	 else {
	 Map<String, String> newuser = new HashMap<>();
	 newuser.put("entername", username);
	 newuser.put("enterpw", pw);
	
	 service.checkUser(username);
	 if (service.checkUser(username) == 0) {
	
	 service.register(newuser);
     model.setViewName("home");

      } else {
 
		 model.addObject("error", "usertaken");
		 model.setViewName("reghome"); 
	 }
   }
	return model;
}
    
	@RequestMapping(value="/inventory" , method = {RequestMethod.GET, RequestMethod.POST})
	   public ModelAndView inventory(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception{
		 ModelAndView model = new ModelAndView();
		 
		   List<Map<String, String>> listall = service.getAll();
	         model.addObject("list", listall);
			 model.setViewName("sessionHome");
		    
			 return model; 
	   }
	
	
	 @RequestMapping(value="/insert" , method = {RequestMethod.GET, RequestMethod.POST})
	   public ModelAndView insert(HttpSession session, HttpServletRequest req, Model model2) throws Exception {
		 ModelAndView model = new ModelAndView();
		 
		 String prodname = req.getParameter("insprodname");
		 String lotno = req.getParameter("inslotno");
		 String qty = req.getParameter("insqty");
		 String proddate = req.getParameter("insproddate");
		 String enddate = req.getParameter("insenddate");
		 
		 String username = session.getAttribute("entername")+"";
		 model2.addAttribute("username", username);
		 
		 Map<String, String> newentry = new HashMap<>();
		 newentry.put("prodname", prodname);
		 newentry.put("lotno", lotno);
		 newentry.put("qty", qty);
		 newentry.put("proddate", proddate);
		 newentry.put("enddate", enddate);
		
		 service.insert(newentry);
		 
		 List<Map<String, String>> listall = service.getAll();
	     model.addObject("list", listall);
	     model.setViewName("/sessionHome");
		 
	     return model;

	      }
	 
        @RequestMapping(value="/updateForm" , method = {RequestMethod.GET, RequestMethod.POST})
		public ModelAndView updateForm(@RequestParam(value="prodcode") String prodcode, Model model2,
				   HttpServletRequest request, HttpServletResponse response) throws Exception { 
        	 ModelAndView model = new ModelAndView();
        	 
        	 HttpSession session = request.getSession();
    		 String username = session.getAttribute("entername")+"";
        	 
        	 Map<String, String> oneRow = service.oneRow(prodcode); //get just one row to update
        	 
        	 model2.addAttribute("prodcode", prodcode);
        	 model2.addAttribute("updprodname", oneRow.get("prodname"));
        	 model2.addAttribute("updlotno", oneRow.get("lotno"));
        	 model2.addAttribute("updqty", oneRow.get("qty"));
    		 model2.addAttribute("updproddate", oneRow.get("proddate"));
    		 model2.addAttribute("updenddate", oneRow.get("enddate"));
        	
    		 model2.addAttribute("username", username);
    		 
    		 List<Map<String, String>> listall = service.getAll();
    	     model.addObject("list", listall);
    	     
    	     model.setViewName("update");
    	     
			 return model;
	 }
	 
        @RequestMapping(value="/returnhome" , method = {RequestMethod.GET, RequestMethod.POST})
 	     public ModelAndView sessionRequest(HttpServletRequest request, HttpServletResponse response, Model model2) throws Exception{
 		 ModelAndView model = new ModelAndView();
 		 
 		 HttpSession session = request.getSession();
		 String username = session.getAttribute("entername")+"";
 		
		 model2.addAttribute("username", username);
		 
 		 List<Map<String, String>> listall = service.getAll();
 	     model.addObject("list", listall);
 	     
 	     model.setViewName("sessionHome"); 
 		 return model; 
 	   } 
        
        @RequestMapping(value="/update" , method = {RequestMethod.GET, RequestMethod.POST})
 	    public ModelAndView update(HttpServletRequest request, Model model2) throws Exception {
 			 ModelAndView model = new ModelAndView();
 			 
 			 HttpSession session = request.getSession();
 			 String username = session.getAttribute("entername")+"";
 			 
 		   	 model2.addAttribute("username", username);
 		
 		   	 String prodcode = request.getParameter("prodcode");
 			 String prodname = request.getParameter("updprodname");
 			 String lotno = request.getParameter("updlotno");
 			 String qty = request.getParameter("updqty");
 			 String proddate = request.getParameter("updproddate");
 			 String enddate = request.getParameter("updenddate");
 	 
 			 service.update(prodcode, prodname, lotno, qty, proddate, enddate);
 			 
 			 List<Map<String, String>> listall = service.getAll();
 	         model.addObject("list", listall);
 	         
 		     model.setViewName("sessionHome");
 		     return model;
 	   }

        @RequestMapping(value="/delete" , method = {RequestMethod.GET, RequestMethod.POST})
	    public ModelAndView delete(@RequestParam(value="prodcode") int prodcode, 
	    						   HttpServletRequest request, HttpServletResponse response, Model model2) throws Exception{
		 	ModelAndView model = new ModelAndView();
		 	
		    HttpSession session = request.getSession();
		 	String username = session.getAttribute("entername")+"";
		 	
		 	model2.addAttribute("username", username);
		 
	        service.delete(prodcode);
	        
	        List<Map<String, String>> listall = service.getAll();
	          model.addObject("list", listall);
	          
		      model.setViewName("sessionHome");
			  return model;
	 }
 
 	 @RequestMapping(value="/search" , method = {RequestMethod.GET, RequestMethod.POST})
 	 public ModelAndView search(@RequestParam(value ="searchword", required = false) String searchword, 
 			 					HttpServletRequest request, HttpServletResponse response, Model model2) throws Exception {
 		 
 		 ModelAndView model = new ModelAndView();	
 	     
 		 HttpSession session = request.getSession();
 		 String username = session.getAttribute("entername")+"";
 		 
 		 List<Map<String, String>> searchresult = service.search(searchword);
 		 
 		 model2.addAttribute("username", username);
 		 
 		 model.addObject("list", searchresult);
 		 model.setViewName("sessionHome");
 		 
         return model;
       }
 	 
 	  @RequestMapping(value="/logout" , method = {RequestMethod.GET, RequestMethod.POST})
	    public ModelAndView sessionEnd(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception{
		 ModelAndView model = new ModelAndView();
		 
		 session.removeAttribute("entername");
		 session.invalidate();
		 
	     model.setViewName("home");
       return model;
	 }
	 
        
}
