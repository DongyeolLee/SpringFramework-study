package com.dy.board.services;

import com.dy.board.domains.Criteria;
import com.dy.board.domains.ReplyVO;
import com.dy.board.persistences.ReplyDAO;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService {

    @Inject
    ReplyDAO dao;

    @Override
    public void addReply(ReplyVO vo) throws Exception {
        dao.create(vo);
    }

    @Override
    public List<ReplyVO> listReply(Integer bno) throws Exception {
        return dao.list(bno);
    }

    @Override
    public void modifyReply(ReplyVO vo) throws Exception {
        dao.update(vo);
    }

    @Override
    public void removeReply(Integer rno) throws Exception {
        dao.delete(rno);
    }

    @Override
    public List<ReplyVO> listReplyPage(Integer bno, Criteria cri) throws Exception {
        return dao.listPage(bno, cri);
    }

    @Override
    public int count(Integer bno) throws Exception {
        return dao.count(bno);
    }
}
