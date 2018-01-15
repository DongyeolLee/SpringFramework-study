package com.dy.board.persistences;

import com.dy.board.domains.UserVO;
import com.dy.board.dto.LoginDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UserDAOImpl implements UserDAO {

    @Inject
    private SqlSession session;
    private static String namespace = "com.dy.board.mapper.UserMapper";

    @Override
    public UserVO login(LoginDTO dto) throws Exception {
        return session.selectOne(namespace + ".login", dto);
    }

    @Override
    public void keepLogin(String uid, String sessionId, Date next) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("uid", uid);
        paramMap.put("sessionId", sessionId);
        paramMap.put("next", next);

        session.update(namespace + ".keepLogin", paramMap);
    }

    @Override
    public UserVO checkUserWithSessionKey(String value) {
        return session.selectOne(namespace + ".checkUserWithSessionKey", value);
    }
}
