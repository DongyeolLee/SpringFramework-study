package com.dy.board;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:web/WEB-INF/spring/**/*.xml"})
public class MyBatisTest {

    @Inject
    private SqlSessionFactory sqlFactory;

    @Test
    public void testFactory() {
        System.out.println("connection test start");
        System.out.println(sqlFactory);
        System.out.println("connection test finish");
    }

    @Test
    public void testSession() throws Exception {

        try(SqlSession session = sqlFactory.openSession()) {
            System.out.println("session test start");
            System.out.println(session);
            System.out.println("session test finish");
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
