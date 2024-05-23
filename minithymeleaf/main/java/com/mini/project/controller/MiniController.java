package com.mini.project.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mini.project.service.MiniService;
import com.mini.project.vo.MiniVo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@Component
public class MiniController {

	@Autowired(required=true)
	private MiniService service; //object of service class
	
	
	//MASTER LIST:
	
	
	//show full master list 
	
	@RequestMapping(value="/list" , method = {RequestMethod.GET, RequestMethod.POST})
	   public ModelAndView listMst(ModelAndView model) throws Exception {

		
		 List<Map<String, String>> listall = service.getAll();
	      model.addObject("list", listall);
	      model.setViewName("list");

	      return model;
	   }
	 
	//insert data into master list
	//call input form 
	
	@RequestMapping(value="/insertMstForm" , method = {RequestMethod.GET, RequestMethod.POST})
	    public String insertMst(){
	        return "insertMst"; //returns insertMst.html
	    }
	 
	 //add input data from form to master list
	
	 @RequestMapping(value="/insertMst" , method = {RequestMethod.GET, RequestMethod.POST})
	   public ModelAndView insertMst(HttpServletRequest req) throws Exception {
		 ModelAndView model = new ModelAndView();
		 String mst_cd = req.getParameter("mst_cd");
		 String mst_name = req.getParameter("mst_name");
		 String mst_notes = req.getParameter("mst_notes");
		 
		 Map<String, String> insertData = new HashMap<>();
		 insertData.put("mst_cd", mst_cd);
		 insertData.put("mst_name", mst_name);
		 insertData.put("mst_notes", mst_notes);
		 
		 service.checkCode(mst_cd);
		 if (service.checkCode(mst_cd) == 0) {
		 
		 service.insertMst(insertData);
		 
		 List<Map<String, String>> listall = service.getAll();
	     model.addObject("list", listall);
	     model.setViewName("redirect:/list"); //redirects to full list with new entry 

	      return model;
	      }
		 
		 else {
			 //model.addObject("error", true);
			 model.setViewName("redirect:/error");
			 return model;
		 }
	   }
	 
	    //update data in master list
		//call update form 
	
	 @RequestMapping(value="/updateMstForm" , method = {RequestMethod.GET, RequestMethod.POST})
		    public String updateMst(@RequestParam("mst_cd") String mst_cd, Model model){ 
		
			 Map<String, String> mstRow = service.oneRowMst(mst_cd); //get just one row to update
		
			 model.addAttribute("mst_cd", mstRow.get("mst_cd"));
			 model.addAttribute("mst_name", mstRow.get("mst_name"));
			 model.addAttribute("mst_notes", mstRow.get("mst_notes"));
		        return "updateMst"; //returns updateMst.html
			    	
		 }
		 
		 
		 //update data in master list according to update form (code fixed)
		
		 @RequestMapping(value="/updateMst" , method = {RequestMethod.GET, RequestMethod.POST})
		   public ModelAndView updateMst(HttpServletRequest req) throws Exception {
				 ModelAndView model = new ModelAndView();	
			     
			 
				 String mst_cd = req.getParameter("mst_cd");
				 String mst_name = req.getParameter("mst_name");
				 String mst_notes = req.getParameter("mst_notes");

				 service.updateMst(mst_cd, mst_name, mst_notes);
				
				 List<Map<String, String>> listall = service.getAll();
			     model.addObject("list", listall);
			     model.setViewName("redirect:/list");
		         return model;
		    }
		 
		 //delete one row from master list
		 
		 @RequestMapping(value="/deleteMst" , method = {RequestMethod.GET, RequestMethod.POST})
		    public String deleteMst(@RequestParam(value="mst_cd") String mst_cd){

		        service.deleteMst(mst_cd);
		        
		        return "redirect:/list";
		        
		    }
		 
		 // Search bar search for entry by name
	
		 @RequestMapping(value="/searchMst" , method = {RequestMethod.GET, RequestMethod.POST})
		 public ModelAndView searchMst(@RequestParam(value ="search", required = false) String search, HttpServletRequest request, HttpServletResponse response) throws Exception {
			 ModelAndView model = new ModelAndView();	
		     
			 List<Map<String, String>> mstsearch = service.searchMst(search);
			 
			 model.addObject("list", mstsearch);
			 model.setViewName("list");
             return model;
             
		 }
		 
		 //========================================
		 //DETAILS LIST:
		 //========================================
		 
		//show full details list for selected master code		
		 @RequestMapping(value="/dtllist" , method = {RequestMethod.GET, RequestMethod.POST})
		   public ModelAndView listDtl(@RequestParam(value="mst_cd") String mst_cd,@RequestParam(value="mst_cd_dtl") String mst_cd_dtl, Model model) throws Exception {
			 ModelAndView model2 = new ModelAndView();
			 List<Map<String, String>> listall = service.getAllDtl(mst_cd_dtl);
			 List<Map<String, String>> listallMst = service.getAll();
			 model.addAttribute("mst_cd_dtl",mst_cd);
		      model2.addObject("list", listallMst);
		      model2.addObject("dtllist", listall);
		      model2.setViewName("list");

		      return model2;
		   }
		 
		//insert data into details list
			//call input form 
		
		 @RequestMapping(value="/insertDtlForm" , method = {RequestMethod.GET, RequestMethod.POST})
			    public ModelAndView insertDtl(@RequestParam(value="mst_cd_dtl") String mst_cd_dtl, Model model){
				 ModelAndView model2 = new ModelAndView();
				 
				 model.addAttribute("mst_cd_dtl", mst_cd_dtl);
			      model2.setViewName("insertDtl");

			      return model2;
			     
			    }
			 
			 //add input data from form to detail list
			
			 @RequestMapping(value="/insertDtl" , method = {RequestMethod.GET, RequestMethod.POST})
			   public ModelAndView insertDtl(HttpServletRequest req, Model model2) throws Exception {
				 ModelAndView model = new ModelAndView();
				 String mst_cd = req.getParameter("mst_cd_dtl");
				 String dtl_cd = req.getParameter("dtl_cd");
				 String dtl_name = req.getParameter("dtl_name");
				 String dtl_notes = req.getParameter("dtl_notes");
				 
				 Map<String, String> insertData = new HashMap<>();
				 insertData.put("mst_cd", mst_cd);
				 insertData.put("dtl_cd", dtl_cd);
				 insertData.put("dtl_name", dtl_name);
				 insertData.put("dtl_notes", dtl_notes);
				 
				 if (service.checkDtlCode(mst_cd,dtl_cd) == 0) { //only allow insert if mst_cd and dtl_cd doesn't already exist
				 
				 service.insertDtl(insertData);
				 
				 List<Map<String, String>> listalldtl = service.getAllDtl(mst_cd);
				 List<Map<String, String>> listall = service.getAll();
			     model.addObject("dtllist", listalldtl);
			     model.addObject("list", listall);
			     model2.addAttribute("mst_cd_dtl", mst_cd);
			     model.setViewName("list"); //shows full list with new entry 

			      return model;
			      }
				 
				 else {
					//if mst_cd and dtl_cd already exist together (count not 0), show this error
					 model.setViewName("/errorDtl");
					 return model;
				 }
			   }
			 
			 //search detail entries for a certain name
			 @RequestMapping(value="/searchDtl" , method = {RequestMethod.GET, RequestMethod.POST})
			 public ModelAndView searchDtl(@RequestParam(value ="dtlsearch", required = false) String dtlsearch, HttpServletRequest request, HttpServletResponse response, Model model2) throws Exception {
				 ModelAndView model = new ModelAndView();	
				 String mst_cd = request.getParameter("mst_cd_dtl");
				 List<Map<String, String>> searchdtl = service.searchDtl(dtlsearch,mst_cd);
				 List<Map<String, String>> listall = service.getAll();
				
				 
				 
				 model.addObject("dtllist", searchdtl); 
				 model.addObject("list", listall); //show results of detail search and also master table
				 model2.addAttribute("mst_cd_dtl", mst_cd);
				 model.setViewName("list");
	             return model;
	             
			 }
			 
			 
			//update data in details list
				//call update form 
				
				 @RequestMapping(value="/updateDtlForm" , method = {RequestMethod.GET, RequestMethod.POST})
				    public String updateDtl(@RequestParam("mst_cd_dtl") String mst_cd_dtl, @RequestParam("dtl_cd") String dtl_cd, Model model){ 
					
					 Map<String, String> dtlRow = service.oneRowDtl(mst_cd_dtl, dtl_cd); //get just one row to update
				
					 model.addAttribute("mst_cd_dtl", dtlRow.get("mst_cd"));
					 model.addAttribute("dtl_cd", dtlRow.get("dtl_cd"));
					 model.addAttribute("mst_name", dtlRow.get("dtl_name"));
					 model.addAttribute("mst_notes", dtlRow.get("dtl_notes"));
				        return "updateDtl";  // returns updateDtl.html
					    	
				 }
				 
			    	
				 
				 //update data in details list according to update form (master & detail code fixed)
				 @RequestMapping(value="/updateDtl" , method = {RequestMethod.GET, RequestMethod.POST})
				   public ModelAndView updateDtl(HttpServletRequest req, Model model2) throws Exception {
						 ModelAndView model = new ModelAndView();	
						 
						 String mst_cd_dtl = req.getParameter("mst_cd_dtl");
						 String dtl_cd = req.getParameter("dtl_cd");
						 String dtl_name = req.getParameter("dtl_name");
						 String dtl_notes = req.getParameter("dtl_notes");

						 service.updateDtl(mst_cd_dtl, dtl_cd, dtl_name, dtl_notes);
						   
						 List<Map<String, String>> listalldtl = service.getAllDtl(mst_cd_dtl);
						 List<Map<String, String>> listall = service.getAll();
						 model2.addAttribute("mst_cd_dtl", mst_cd_dtl); //stores mst_cd_dtl value so that you don't get an error when you try to re-update again after just updating
					     model.addObject("dtllist", listalldtl);
					     model.addObject("list", listall);
					     model.setViewName("list");
				         return model;
				 }
			    
				 @RequestMapping(value="/deleteDtl" , method = {RequestMethod.GET, RequestMethod.POST})
				 public ModelAndView deleteDtl(HttpServletRequest req, Model model2) throws Exception {
					 ModelAndView model = new ModelAndView();
					 
					 String mst_cd = req.getParameter("mst_cd");
					 String dtl_cd = req.getParameter("dtl_cd");
					 
					 String mst_cd_dtl = req.getParameter("mst_cd");
					 
					 service.deleteDtl(mst_cd, dtl_cd);
					 
					 List<Map<String, String>> listalldtl = service.getAllDtl(mst_cd_dtl);
				     List<Map<String, String>> listall = service.getAll();
				     
				     model.addObject("dtllist", listalldtl);
				     model.addObject("list", listall);
				     model2.addAttribute("mst_cd_dtl", mst_cd_dtl);
				     model.setViewName("list");
			         return model;
				 }
				 
				 //========================================
				 //COMBINED LIST:
				 //========================================
				 
				 
				 @RequestMapping(value="/allSearch" , method = {RequestMethod.GET, RequestMethod.POST})
				  /*  public String allSearch(){
				        return "allSearch"; //returns allSearch.html
				    }*/
				 
				 //new==========>
				     public ModelAndView combinedList(ModelAndView model) throws Exception {
					 List<Map<String, String>> alllist = service.getAllCombined();
				      model.addObject("alllist", alllist);
				      model.setViewName("allSearch");

				      return model;
				 }

				 //============<
				 
				 
				 @RequestMapping(value="/searchAll" , method = {RequestMethod.GET, RequestMethod.POST})
				 public ModelAndView searchAll(@RequestParam(value ="searchword", required = false) String searchword, @RequestParam(value ="searchoptions", required = false) String searchoptions, 
						 HttpServletRequest request, HttpServletResponse response) throws Exception {
					 ModelAndView model = new ModelAndView();	
					 List<Map<String, String>> searchall = service.searchAll(searchword, searchoptions);
				
					 model.addObject("alllist", searchall); 
					 model.setViewName("allSearch");
		             return model;
		             
				 }
				
				 
}

        
