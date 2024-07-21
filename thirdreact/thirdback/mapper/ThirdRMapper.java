package com.react.thirdreact.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.react.thirdreact.data.ThirdRData;

@Mapper
public interface ThirdRMapper {

public int checkPassword(ThirdRData data);

public List<ThirdRData> getInventory();

/*public void insert(Map<String, String> newitem);*/

public void insert(ThirdRData data);

public void delete(ThirdRData data);

public Map<String, String> oneRow(String prodcode);

public void update(ThirdRData data);

public List<Map<String, String>> invsearch(String paramword);

public int checkUser(String id);

public void register(Map<String, String> newuser);
    
}
