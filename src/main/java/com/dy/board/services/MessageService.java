package com.dy.board.services;

import com.dy.board.domains.MessageVO;

public interface MessageService {

    public void addMessage(MessageVO vo) throws Exception;

    public MessageVO readMessage(String uid, Integer mid) throws Exception;
}
