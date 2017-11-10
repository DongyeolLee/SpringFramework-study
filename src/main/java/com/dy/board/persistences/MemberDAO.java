package com.dy.board.persistences;

import com.dy.board.domains.MemberVO;

public interface MemberDAO {
    public String getTime();

    public void insertMember(MemberVO vo);
}
