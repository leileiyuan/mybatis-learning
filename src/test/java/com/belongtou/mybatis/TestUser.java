package com.belongtou.mybatis;

import com.belongtou.mybatis.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class TestUser {
    private SqlSession sqlSession = null;
    SqlSessionFactory sqlSessionFactory = null;

    private final String namespace = "com.belongtou.mybatis.model.User";

    @Test
    public void testSelectOne() {
        try {
            sqlSession = sqlSessionFactory.openSession();
            User user = sqlSession.selectOne(namespace + ".selectById", 1);
            System.out.println(user.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectList() {
        try {
            sqlSession = sqlSessionFactory.openSession();
            List<User> list = sqlSession.selectList(namespace + ".selectByName", "%ale%");
            System.out.println(list.get(0).getUsername());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testInsertUser() {
        try {
            sqlSession = sqlSessionFactory.openSession();
            User user = new User();
            user.setAddress("河南");
            user.setBirthday(new Date());
            user.setSex("男");
            user.setUsername("bbb");
            sqlSession.insert(namespace + ".insertUser2", user);
            sqlSession.commit();
            System.out.println("获取主键: " +user.getId());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }


    @Before
    public void init() {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        SqlSession sqlSession = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession = sqlSessionFactory.openSession();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
