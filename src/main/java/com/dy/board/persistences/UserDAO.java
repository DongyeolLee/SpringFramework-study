package com.dy.board.persistences;

import com.dy.board.domains.UserVO;
import com.dy.board.dto.LoginDTO;

import java.util.Date;

public interface UserDAO {

    public UserVO login(LoginDTO dto) throws Exception;

    public void keepLogin(String uid, String sessionId, Date next);

    public UserVO checkUserWithSessionKey(String value);
}
