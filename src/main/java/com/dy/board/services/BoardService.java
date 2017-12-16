package com.dy.board.services;

import com.dy.board.domains.BoardVO;
import com.dy.board.domains.Criteria;
import com.dy.board.domains.SearchCriteria;

import java.util.List;

public interface BoardService {
    public void regist(BoardVO vo) throws Exception;

    public BoardVO read(Integer bno) throws Exception;

    public void modify(BoardVO vo) throws Exception;

    public void remove(Integer bno) throws Exception;

    public List<BoardVO> listAll() throws Exception;

    public List<BoardVO> listCriteria(Criteria cri) throws Exception;

    public int listCountCriteria(Criteria cri) throws Exception;

    public List<BoardVO> listSearchCriteria(SearchCriteria cri) throws Exception;

    public int listSearchCount(SearchCriteria cri) throws Exception;
}
