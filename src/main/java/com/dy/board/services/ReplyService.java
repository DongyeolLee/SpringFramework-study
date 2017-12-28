package com.dy.board.services;

import com.dy.board.domains.Criteria;
import com.dy.board.domains.ReplyVO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ReplyService {

    public void addReply(ReplyVO vo) throws Exception;

    public List<ReplyVO> listReply(Integer bno) throws Exception;

    public void modifyReply(ReplyVO vo) throws Exception;

    public void removeReply(Integer rno) throws Exception;

    public List<ReplyVO> listReplyPage(Integer bno, Criteria cri) throws Exception;

    public int count(Integer bno) throws Exception;
}
