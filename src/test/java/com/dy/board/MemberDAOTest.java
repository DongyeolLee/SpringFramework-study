package com.dy.board;

import com.dy.board.domains.MemberVO;
import com.dy.board.persistences.MemberDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:web/WEB-INF/spring/*.xml"})
public class MemberDAOTest {

    @Inject
    private MemberDAO dao;

    @Test
    public void testTime() throws Exception {
        System.out.println(dao.getTime());
    }

    @Test
    public void testInsertMember() throws Exception {

        MemberVO vo = new MemberVO();
        vo.setUserid("user00");
        vo.setUserpw("00");
        vo.setUsername("USER00");
        vo.setEmail("user00@test.com");

        dao.insertMember(vo);
    }
}
