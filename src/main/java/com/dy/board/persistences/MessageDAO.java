package com.dy.board.persistences;

import com.dy.board.domains.MessageVO;

public interface MessageDAO {

    public void create(MessageVO vo) throws Exception;

    public MessageVO readMessage(Integer mid) throws Exception;

    public void updateState(Integer mid) throws Exception;
}
