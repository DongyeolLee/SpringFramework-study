package com.dy.board.services;

import com.dy.board.domains.BoardVO;
import com.dy.board.domains.Criteria;
import com.dy.board.domains.SearchCriteria;
import com.dy.board.persistences.BoardDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    @Inject
    private BoardDAO dao;
    private static final Logger logger = LoggerFactory.getLogger(BoardServiceImpl.class);

    @Transactional
    @Override
    public void regist(BoardVO board) throws Exception {
        dao.create(board);
        String[] files = board.getFiles();

        if(files == null) {
            return;
        }

        for(String fileName : files) {
            dao.addAttach(fileName);
        }
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public BoardVO read(Integer bno, Integer bit) throws Exception {
        if(bit == 1)
            dao.updateViewCnt(bno);

        return dao.read(bno);
    }

    @Transactional
    @Override
    public void modify(BoardVO board) throws Exception {
        dao.update(board);

        Integer bno = board.getBno();
        dao.deleteAttach(bno);
        String[] files = board.getFiles();
        if(files == null) {return;}

        for(String fileName : files) {
            dao.replaceAttach(fileName, bno);
        }
    }

    @Transactional
    @Override
    public void remove(Integer bno) throws Exception {
        dao.deleteAttach(bno);
        dao.delete(bno);
    }

    @Override
    public List<BoardVO> listAll() throws Exception {
        return dao.listAll();
    }

    @Override
    public List<BoardVO> listCriteria(Criteria cri) throws Exception {
        return dao.listCriteria(cri);
    }

    @Override
    public int listCountCriteria(Criteria cri) throws Exception {
        return dao.countPaging(cri);
    }

    @Override
    public List<BoardVO> listSearchCriteria(SearchCriteria cri) throws Exception {
        return dao.listSearch(cri);
    }

    @Override
    public int listSearchCount(SearchCriteria cri) throws Exception {
        return dao.listSearchCount(cri);
    }

    @Override
    public List<String> getAttach(Integer bno) throws Exception {
        return dao.getAttach(bno);
    }
}
