package com.react.thirdreact.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.react.thirdreact.mapper.ThirdRMapper;
import com.react.thirdreact.data.ThirdRData;

@Service
@Component
public class ThirdRService {

    @Autowired
    private ThirdRMapper mapper;

    public int checkPassword(ThirdRData data) {
        return mapper.checkPassword(data);
    }

    public List<ThirdRData> getInventory() {
       return mapper.getInventory();
    }

	public void insert(ThirdRData data) {
	   mapper.insert(data);
	}

	public void delete(ThirdRData data) {
		mapper.delete(data);
	}

    public Map<String, String> oneRow(String prodcode) {
      return mapper.oneRow(prodcode);
    }

    public void update(ThirdRData data) {
      mapper.update(data);
    }

    public List<Map<String, String>> searchInv(String paramword) {
        return mapper.invsearch(paramword);
    }

    public int checkUser(String id) {
      return mapper.checkUser(id);
    }

    public void register(Map<String, String> newuser) {
      mapper.register(newuser);
    }





}
