package com.dy.board.persistences;

import com.dy.board.domains.UserVO;
import com.dy.board.dto.LoginDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;

@Repository
public class UserDAOImpl implements UserDAO {

    @Inject
    private SqlSession session;
    private static String namespace = "com.dy.board.mapper.UserMapper";

    @Override
    public UserVO login(LoginDTO dto) throws Exception {
        return session.selectOne(namespace + ".login", dto);
    }
}
