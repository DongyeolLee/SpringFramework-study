package com.dy.board.services;

import com.dy.board.domains.UserVO;
import com.dy.board.dto.LoginDTO;

import java.util.Date;

public interface UserService {
    public UserVO login(LoginDTO dto) throws Exception;

    public void keepLogin(String uid, String sessionId, Date next);

    public UserVO checkLoginBefore(String value);
}
