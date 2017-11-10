package com.dy.board.persistences;

import com.dy.board.domains.MemberVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;

@Repository
public class MemberDAOImpl implements MemberDAO {

    @Inject
    private SqlSession sqlSession;

    private static final String namespace = "com.dy.board.mapper.MemberMapper";

    @Override
    public String getTime() {
        return sqlSession.selectOne(namespace+".getTime");
    }

    @Override
    public void insertMember(MemberVO vo) {
        sqlSession.insert(namespace+".insertMember", vo);
    }
}
