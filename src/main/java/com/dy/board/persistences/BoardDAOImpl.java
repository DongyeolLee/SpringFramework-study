package com.dy.board.persistences;

import com.dy.board.domains.BoardVO;
import com.dy.board.domains.Criteria;
import com.dy.board.domains.SearchCriteria;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BoardDAOImpl implements BoardDAO {

    @Inject
    private SqlSession session;

    private static final String namespace = "com.dy.board.mapper.BoardMapper";

    @Override
    public void create(BoardVO vo) throws Exception {
        session.insert(namespace+".create", vo);
    }

    @Override
    public BoardVO read(Integer bno) throws Exception {
        return session.selectOne(namespace+".read", bno);
    }

    @Override
    public void update(BoardVO vo) throws Exception {
        session.update(namespace+".update", vo);
    }

    @Override
    public void delete(Integer bno) throws Exception {
        session.delete(namespace+".delete", bno);
    }

    @Override
    public List<BoardVO> listAll() throws Exception {
        return session.selectList(namespace+".listAll");
    }

    @Override
    public List<BoardVO> listPage(int page) throws Exception {
        if (page <= 0) {
            page = 1;
        }

        page = (page - 1) * 10;

        return session.selectList(namespace + ".listPage", page);
    }

    @Override
    public List<BoardVO> listCriteria(Criteria cri) throws Exception {

        return session.selectList(namespace + ".listCriteria", cri);
    }

    @Override
    public int countPaging(Criteria cri) throws Exception {

        return session.selectOne(namespace + ".countPaging", cri);
    }

    @Override
    public List<BoardVO> listSearch(SearchCriteria cri) throws Exception {
        return session.selectList(namespace + ".listSearch", cri);
    }

    @Override
    public int listSearchCount(SearchCriteria cri) throws Exception {
        return session.selectOne(namespace + ".listSearchCount", cri);
    }

    @Override
    public void updateCnt(Integer bno, int amount) throws Exception {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("bno", bno);
        paramMap.put("amount", amount);

        session.update(namespace + ".updateReplyCnt", paramMap);
    }

    @Override
    public void updateViewCnt(Integer bno) throws Exception {
        session.update(namespace + ".updateViewCnt", bno);
    }

    @Override
    public void addAttach(String fullName) throws Exception {
        session.insert(namespace + ".addAttach", fullName);
    }
}
