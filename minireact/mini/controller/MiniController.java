package com.react.mini.controller;

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

import com.react.mini.data.MiniData;
import com.react.mini.service.MiniService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@Controller
@Component
@RequestMapping("/api")
@CrossOrigin("http://localhost:3000/")
public class MiniController {


    @Autowired
	private MiniService service;


    //show full master list 
	@RequestMapping(value="/list" , method = {RequestMethod.GET, RequestMethod.POST})
	public List<MiniData> getAll(){
		List<MiniData> list = service.getAll();
	    return list;
	}

 		
		@RequestMapping(value="/deleteMst" , method = {RequestMethod.GET, RequestMethod.POST})
		    //public String deleteMst(@RequestParam(value="mst_cd") String mst_cd){
			public List<MiniData> deleteMst(@RequestParam(value="mst_cd") String mst_cd){
		        service.deleteMst(mst_cd);
		        List<MiniData> list = service.getAll();
	   			return list;
		        //return "deleted";       
		    }

		//delete one row from detail list
		@RequestMapping(value="/deleteDtl" , method = {RequestMethod.GET, RequestMethod.POST})
		    public List<Map<String, String>> deleteDtl(@RequestParam(value="mst_cd") String mst_cd, @RequestParam(value="dtl_cd") String dtl_cd) throws Exception{

		        service.deleteDtl(mst_cd, dtl_cd);
		        List<Map<String, String>> listdtl = service.getDtlList(mst_cd);
		        return listdtl;		        
			
		    }

		//grab one master entry/row
		@RequestMapping(value="/updateMstForm" , method = {RequestMethod.GET, RequestMethod.POST}) 
		public Map<String, String> oneRow(@RequestParam(value="mst_cd") String mst_cd) {
		Map<String, String> mstRow = service.oneRowMst(mst_cd);
		return mstRow;}



        @RequestMapping(value="/updateMst" , method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT}) 
			public String updateMst(HttpServletRequest req) throws Exception {
				
				 String mst_cd = req.getParameter("mst_cd");
				 String mst_name = req.getParameter("mst_name");
				 String mst_notes = req.getParameter("mst_notes");

				 System.out.println(mst_cd);
				 System.out.println(mst_name);
				 System.out.println(mst_notes);

				 service.updateMst(mst_cd, mst_name, mst_notes);
				
		         return "success";
				}


				@RequestMapping(value="/updateDtlForm" , method = {RequestMethod.GET, RequestMethod.POST}) 
				public Map<String, String> oneRowDtl(@RequestParam(value="mst_cd") String mst_cd, @RequestParam(value="dtl_cd") String dtl_cd) {
				Map<String, String> dtlRow = service.oneRowDtl(mst_cd, dtl_cd);
				return dtlRow;}

				//update data in detail list
				@RequestMapping(value="/updateDtl" , method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT}) 
				private String updateDtl(HttpServletRequest req) throws Exception {  

				 String mst_cd = req.getParameter("mst_cd");
				 String dtl_cd = req.getParameter("dtl_cd");
				 String dtl_name = req.getParameter("dtl_name");
				 String dtl_notes = req.getParameter("dtl_notes");

				 System.out.println(mst_cd);
				 System.out.println(dtl_cd);
				 System.out.println(dtl_name);
				 System.out.println(dtl_notes);

					service.updateDtl(mst_cd, dtl_cd, dtl_name, dtl_notes);  
					return "success";  
				}  


		@RequestMapping(value="/insertMst" , method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})  
		private String insertMst(HttpServletRequest req) throws Exception {  

				 String mst_cd = req.getParameter("mst_cd");
				 String mst_name = req.getParameter("mst_name");
				 String mst_notes = req.getParameter("mst_notes");

				 System.out.println(mst_cd);
				 System.out.println(mst_name);
				 System.out.println(mst_notes);
						
			service.checkCode(mst_cd);
			if (service.checkCode(mst_cd) == 0) {
			service.insertMst(mst_cd, mst_name, mst_notes);  
			return "success";
			}
			else{
			 return "codetaken";
			}
		}  

		@RequestMapping(value="/insertDtl" , method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})  
		private String insertDtl(HttpServletRequest req) throws Exception {  

				 String mst_cd = req.getParameter("mst_cd");
				 String dtl_cd = req.getParameter("dtl_cd");
				 String dtl_name = req.getParameter("dtl_name");
				 String dtl_notes = req.getParameter("dtl_notes");

				 System.out.println(mst_cd);
				 System.out.println(dtl_cd);
				 System.out.println(dtl_name);
				 System.out.println(dtl_notes);
						
			service.checkDtlCode(mst_cd, dtl_cd);
			if (service.checkDtlCode(mst_cd, dtl_cd) == 0) {
			service.insertDtl(mst_cd, dtl_cd, dtl_name, dtl_notes);  
			return "success";
			}
			else{
			 return "codetaken";
			}
		}  

		// Search bar search for master entry by name
		 @RequestMapping(value="/searchMst" , method = {RequestMethod.GET, RequestMethod.POST})
		 public List<Map<String, String>> searchMst(@RequestParam(value ="searchword", required = false) String searchword, HttpServletRequest request, HttpServletResponse response) throws Exception {
			 		     
			List<Map<String, String>> mstsearch = service.searchMst(searchword);
            return mstsearch; 
		 }

		 
		  @RequestMapping(value="/dtllist", method = {RequestMethod.GET, RequestMethod.POST})
		   public List<Map<String, String>> getDtlList(@RequestParam(value="mst_cd") String mst_cd) throws Exception{
			 List<Map<String, String>> listdtl = service.getDtlList(mst_cd);
		     return listdtl; 
		   }


			// Search bar search for entry by name
		//  @RequestMapping(value="/searchDtl" , method = {RequestMethod.GET, RequestMethod.POST})
		//  public List<Map<String, String>> searchDtl(@RequestParam(value ="paramword", required = false) String paramword, HttpServletRequest request, HttpServletResponse response) throws Exception {
			
		// 	 List<Map<String, String>> dtlsearch = service.searchDtl(paramword);
		// 	//List<MiniVueData> mstsearch = service.getAll();
        //      return dtlsearch; 
		//  }



	//show combined master and detail list 
	@RequestMapping(value="/getAllCombined" , method = {RequestMethod.GET, RequestMethod.POST})
	public List<MiniData> getAllCombined(){
		List<MiniData> alllist = service.getAllCombined();
	    return alllist;
	}

		//Search bar search for combined overview detail & master
		 @RequestMapping(value="/searchAll" , method = {RequestMethod.GET, RequestMethod.POST})
		 public List<Map<String, String>> searchAll(@RequestParam(value ="searchword", required = false) String searchword, @RequestParam(value ="searchoptions", required = false) String searchoptions, HttpServletRequest request, HttpServletResponse response) throws Exception {
			 	
		     
			 List<Map<String, String>> allsearch = service.searchAll(searchword, searchoptions);
             return allsearch; 
		 }

		}