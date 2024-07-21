package com.react.thirdreact.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.react.thirdreact.service.ThirdRService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.react.thirdreact.data.ThirdRData;

@RestController
@Controller
@Component
@RequestMapping("/api")
@CrossOrigin("http://localhost:3000/")
public class ThirdRController {
    
@Autowired
private ThirdRService service;


@RequestMapping(value="/login" , method = {RequestMethod.GET, RequestMethod.POST})
	   private ThirdRData sessionRequest(HttpSession session, 
	   @RequestBody ThirdRData data, HttpServletRequest request, HttpServletResponse response) throws Exception{

        service.checkPassword(data);
		 
        if (service.checkPassword(data) == 1) {
            data.setTest_result("success");
           return data;
         }
        else {
           data.setTest_result("wrongpw");
           return data;
        }

}

@RequestMapping(value="/list", method = {RequestMethod.GET, RequestMethod.POST})
   private List<ThirdRData> showInventory(){
      List<ThirdRData> inventory = service.getInventory();
      return inventory;
   }
@RequestMapping(value="/insert", method = {RequestMethod.GET, RequestMethod.POST})
private ThirdRData insert (
@RequestBody ThirdRData data, HttpServletRequest req, HttpServletResponse res) throws Exception{

   service.insert(data);
   return data;
}

@RequestMapping(value="/delete", method = {RequestMethod.GET, RequestMethod.POST})
public List<ThirdRData> delete(@RequestBody ThirdRData data, HttpServletRequest request, HttpServletResponse response)
  // @RequestParam(value="prodcode") int prodcode)
    throws Exception{
   service.delete(data);
   List<ThirdRData> inventory = service.getInventory();
   return inventory;
} 

@RequestMapping(value="/updateData" , method = {RequestMethod.GET, RequestMethod.POST}) 
public Map<String, String> oneRow(@RequestParam(value="prodcode") String prodcode) {
Map<String, String> oneItemData = service.oneRow(prodcode);
return oneItemData;}

@RequestMapping(value="/update" , method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT}) 
private ThirdRData update(@RequestBody ThirdRData data, HttpServletRequest request, HttpServletResponse response){  

   service.update(data);  
   return data;  
} 

@RequestMapping(value="/searchInv" , method = {RequestMethod.GET, RequestMethod.POST})
public List<Map<String, String>> searchInv(@RequestParam(value ="paramword", required = false) String paramword, HttpServletRequest request, HttpServletResponse response) throws Exception {
              System.out.print(paramword);
  List<Map<String, String>> invsearch = service.searchInv(paramword);
     return invsearch; 
}

@RequestMapping(value="/registerUser" , method = {RequestMethod.GET, RequestMethod.POST})
		//	public String register(
		//	@RequestParam(value="pw") String pw,
		//	@RequestParam(value="cpw") String cpw,
		//	@RequestParam(value="id") String id,
		//	HttpServletRequest request, HttpServletResponse response) throws Exception{
         private ThirdRData registerUser(HttpSession session, 
         @RequestBody ThirdRData data, HttpServletRequest request, HttpServletResponse response) throws Exception{
   
				if(!data.getPw().equals(data.getCpw())) {
					data.setTest_result("pwnomatch");
           return data;
				}else {
					Map<String, String> newuser = new HashMap<>();
					newuser.put("id", data.getId());
					newuser.put("pw", data.getPw());

					service.checkUser(data.id);
					if (service.checkUser(data.id) == 0) {
					
					service.register(newuser);
               data.setTest_result("success");
               return data;
					} else {
                  data.setTest_result("usertaken");
                  return data;
					}
				}

}}