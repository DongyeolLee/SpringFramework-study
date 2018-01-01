package com.dy.board.persistences;

import com.dy.board.domains.MessageVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;

@Repository
public class MessageDAOImpl implements MessageDAO {

    @Inject
    private SqlSession session;
    private static String namespace = "com.dy.board.mapper.MessageMapper";

    @Override
    public void create(MessageVO vo) throws Exception {
        System.out.println("****************** message VO");
        System.out.println(vo);
        session.insert(namespace + ".create", vo);
    }

    @Override
    public MessageVO readMessage(Integer mid) throws Exception {
        return session.selectOne(namespace + ".readMessage", mid);
    }

    @Override
    public void updateState(Integer mid) throws Exception {
        session.update(namespace + ".updateState", mid);
    }
}
