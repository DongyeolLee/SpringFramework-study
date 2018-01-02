package com.dy.board.services;

import com.dy.board.domains.Criteria;
import com.dy.board.domains.ReplyVO;
import com.dy.board.persistences.BoardDAO;
import com.dy.board.persistences.ReplyDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService {

    @Inject
    ReplyDAO dao;
    @Inject
    private BoardDAO boardDAO;

    @Transactional
    @Override
    public void addReply(ReplyVO vo) throws Exception {
        dao.create(vo);
        boardDAO.updateCnt(vo.getBno(), 1);
    }

    @Override
    public List<ReplyVO> listReply(Integer bno) throws Exception {
        return dao.list(bno);
    }

    @Override
    public void modifyReply(ReplyVO vo) throws Exception {
        dao.update(vo);
    }

    @Transactional
    @Override
    public void removeReply(Integer rno) throws Exception {
        int bno = dao.getBno(rno);

        dao.delete(rno);
        boardDAO.updateCnt(bno, -1);
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
