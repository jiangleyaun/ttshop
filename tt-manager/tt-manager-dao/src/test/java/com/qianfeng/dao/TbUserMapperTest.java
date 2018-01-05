package com.qianfeng.dao;

import com.qianfeng.pojo.po.TbUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao-test.xml"})

public class TbUserMapperTest {
    @Autowired
    private TbUserMapper userDao;

    @Test
    public void selectByPrimaryKey() throws Exception {
        TbUser tbUser = userDao.selectByPrimaryKey(5L);
        System.out.println(tbUser);
        System.out.println("1111111");
    }

}
