package com.xh.service.impl;

import com.xh.dao.db1.UserMapper;
import com.xh.dao.db2.DocumentMapper;
import com.xh.service.ITestServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author xiaohe
 * @version V1.0.0
 * @Description:
 * @date: 2018-10-14 14:15
 * @Copyright:
 */
@Service("testServiceImpl")
public class TestServiceImpl implements ITestServices {

    private Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DocumentMapper documentMapper;

    @Override
    public void queryUsers() {
        logger.info("=========================== start query user ========================");
        List<Map<String, Object>> users = this.userMapper.queryUsers();
        parseResponseData(users);
        logger.info("=========================== end query user ========================");
    }

    @Override
    public void queryDocuments() {
        logger.info("=========================== start query document ========================");
        List<Map<String, Object>> documents = this.documentMapper.queryDocuments();
        parseResponseData(documents);
        logger.info("=========================== end query document ========================");
    }

    private void parseResponseData(List<Map<String, Object>> users){
        for(Map<String,Object> user : users){
            StringBuffer userInfo = new StringBuffer();
            for(String column : user.keySet()){
                userInfo.append(column);
                userInfo.append(":");
                userInfo.append(user.get(column));
            }
            logger.info(userInfo.toString());
        }
    }

}
