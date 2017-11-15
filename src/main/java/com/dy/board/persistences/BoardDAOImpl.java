package com.dy.board.persistences;

import com.dy.board.domains.BoardVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;

@Repository
public class BoardDAOImpl implements BoardDAO {

    @Inject
    private SqlSession session;

    private static final String namespace = "com.dy.board.mapper.BoardMapper";

    @Override
    public void create(BoardVO vo) throws Exception {
        session.insert(namespace+".create", vo);
    }

    @Override
    public BoardVO read(Integer bno) throws Exception {
        return session.selectOne(namespace+".read", bno);
    }

    @Override
    public void update(BoardVO vo) throws Exception {
        session.update(namespace+".update", vo);
    }

    @Override
    public void delete(Integer bno) throws Exception {
        session.delete(namespace+".delete", bno);
    }

    @Override
    public List<BoardVO> listAll() throws Exception {
        return session.selectList(namespace+".listAll");
    }
}
