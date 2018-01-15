package com.dy.board.services;

import com.dy.board.domains.UserVO;
import com.dy.board.dto.LoginDTO;
import com.dy.board.persistences.UserDAO;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService{

    @Inject
    private UserDAO dao;

    @Override
    public UserVO login(LoginDTO dto) throws Exception {
        return dao.login(dto);
    }

    @Override
    public void keepLogin(String uid, String sessionId, Date next) {
        dao.keepLogin(uid, sessionId, next);
    }

    @Override
    public UserVO checkLoginBefore(String value) {
        return dao.checkUserWithSessionKey(value);
    }
}
