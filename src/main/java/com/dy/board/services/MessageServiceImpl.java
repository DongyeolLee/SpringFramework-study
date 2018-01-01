package com.dy.board.services;

import com.dy.board.domains.MessageVO;
import com.dy.board.persistences.MessageDAO;
import com.dy.board.persistences.PointDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class MessageServiceImpl implements MessageService {

    @Inject
    private MessageDAO messageDAO;
    @Inject
    private PointDAO pointDAO;

    private static final Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);

    @Override
    public void addMessage(MessageVO vo) throws Exception {
        logger.info("start serviceImpl");
        messageDAO.create(vo);
        pointDAO.updatePoint(vo.getSender(), 10);
    }

    @Override
    public MessageVO readMessage(String uid, Integer mid) throws Exception {
        messageDAO.updateState(mid);
        pointDAO.updatePoint(uid, 5);

        return messageDAO.readMessage(mid);
    }
}
