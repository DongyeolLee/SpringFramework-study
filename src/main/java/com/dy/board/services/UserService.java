package com.dy.board.services;

import com.dy.board.domains.UserVO;
import com.dy.board.dto.LoginDTO;

public interface UserService {
    public UserVO login(LoginDTO dto) throws Exception;
}
