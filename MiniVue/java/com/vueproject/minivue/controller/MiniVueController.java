package com.vueproject.minivue.controller;

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


import com.vueproject.minivue.data.MiniVueData;
import com.vueproject.minivue.service.MiniVueService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@Controller
@Component
@RequestMapping("/api")
@CrossOrigin("http://localhost:8081/")
public class MiniVueController {

    @Autowired
	private MiniVueService service;


    //show full master list 
	@RequestMapping(value="/list" , method = {RequestMethod.GET, RequestMethod.POST})
	public List<MiniVueData> getAll(){
		List<MiniVueData> list = service.getAll();
	    return list;
	}

 		//delete one row from master list
		@RequestMapping(value="/deleteMst" , method = {RequestMethod.GET, RequestMethod.POST})
		    public String deleteMst(@RequestParam(value="mst_cd") String mst_cd){

		        service.deleteMst(mst_cd);
		        
		        return "/list";		        
		    }

		//update data in master list
        @RequestMapping(value="/updateMst" , method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT}) 
        private MiniVueData updateMst(@RequestBody MiniVueData item){  
            service.updateMst(item);  
            return item;  
        }  

		//insert data into master list
		@RequestMapping(value="/insertMst" , method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})  
		private MiniVueData insertMst(@RequestBody MiniVueData item){  
						
			service.checkCode(item);
			if (service.checkCode(item) == 0) {
			service.insertMst(item);  
			item.setMst_result("success");
			return item;
			}
			else{
			 item.setMst_result("codetaken");
			 return item;
			}
		}  

		// Search bar search for entry by name
		 @RequestMapping(value="/searchMst" , method = {RequestMethod.GET, RequestMethod.POST})
		 public List<Map<String, String>> searchMst(@RequestParam(value ="paramword", required = false) String paramword, HttpServletRequest request, HttpServletResponse response) throws Exception {
			 		     
			List<Map<String, String>> mstsearch = service.searchMst(paramword);
            return mstsearch; 
		 }

		 
		  @RequestMapping(value="/dtllist" , method = {RequestMethod.GET, RequestMethod.POST})
		   public List<Map<String, String>> getDtlList(@RequestParam(value="mst_cd") String mst_cd) throws Exception{
			 List<Map<String, String>> listdtl = service.getDtlList(mst_cd);
		     return listdtl; 
		   }

		//update data in master list
        @RequestMapping(value="/updateDtl" , method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT}) 
        private MiniVueData updateDtl(@RequestBody MiniVueData item){  
            service.updateDtl(item);  
            return item;  
        }  

		//delete one row from detail list
		@RequestMapping(value="/deleteDtl" , method = {RequestMethod.GET, RequestMethod.POST})
		    public List<Map<String, String>> deleteDtl(@RequestParam(value="mst_cd") String mst_cd, @RequestParam(value="dtl_cd") String dtl_cd) throws Exception{

		        service.deleteDtl(mst_cd, dtl_cd);
		        List<Map<String, String>> listdtl = service.getDtlList(mst_cd);
		        return listdtl;		        
			
		    }


			// Search bar search for entry by name
		 @RequestMapping(value="/searchDtl" , method = {RequestMethod.GET, RequestMethod.POST})
		 public List<Map<String, String>> searchDtl(@RequestParam(value ="paramword", required = false) String paramword, HttpServletRequest request, HttpServletResponse response) throws Exception {
			 	
		     
			 List<Map<String, String>> dtlsearch = service.searchDtl(paramword);
			//List<MiniVueData> mstsearch = service.getAll();
             return dtlsearch; 
		 }


		 //insert data into detail list
		@RequestMapping(value="/insertDtl" , method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})  
		private MiniVueData insertDtl(@RequestBody MiniVueData item){  
						
			service.checkDtlCode(item);
			service.checkCode(item);
			if (service.checkCode(item) == 0){
			item.setDtl_result("noMstCode");
			return item;	
			}
			else if (service.checkDtlCode(item) == 0 && service.checkCode(item) == 1) {
		
			service.insertDtl(item);  
			item.setDtl_result("success");
			return item;
			}
			else{
			 item.setDtl_result("codetaken");
			 return item;
			}
		}  


	//show combined master and detail list 
	@RequestMapping(value="/getAllCombined" , method = {RequestMethod.GET, RequestMethod.POST})
	public List<MiniVueData> getAllCombined(){
		List<MiniVueData> alllist = service.getAllCombined();
	    return alllist;
	}

		//Search bar search for combined overview detail & master
		 @RequestMapping(value="/searchAll" , method = {RequestMethod.GET, RequestMethod.POST})
		 public List<Map<String, String>> searchAll(@RequestParam(value ="paramword", required = false) String paramword, @RequestParam(value ="searchoptions", required = false) String searchoptions, HttpServletRequest request, HttpServletResponse response) throws Exception {
			 	
		     
			 List<Map<String, String>> allsearch = service.searchAll(paramword, searchoptions);
             return allsearch; 
		 }

		}