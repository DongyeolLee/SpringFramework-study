package com.dy.board.persistences;

import com.dy.board.domains.UserVO;
import com.dy.board.dto.LoginDTO;

public interface UserDAO {

    public UserVO login(LoginDTO dto) throws Exception;
}
