package com.dy.board;

import com.dy.board.domains.BoardVO;
import com.dy.board.domains.Criteria;
import com.dy.board.domains.SearchCriteria;
import com.dy.board.persistences.BoardDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:web/WEB-INF/spring/*.xml"})
public class BoardDAOTest {

    @Inject
    private BoardDAO dao;

    @Test
    public void testCreate() throws Exception {

        BoardVO board = new BoardVO();

        board.setTitle("new title2");
        board.setContent("new content2");
        board.setWriter("user02");
        dao.create(board);
    }

    @Test
    public void testRead() throws Exception {
        System.out.println(dao.read(2).toString());
    }

    @Test
    public void testUpdate() throws Exception {
        BoardVO board = new BoardVO();

        board.setBno(1);
        board.setTitle("new title200");
        board.setContent("new content200");
        dao.update(board);
    }

    @Test
    public void testListAll() throws Exception {
        System.out.println(dao.listAll());
    }

    @Test
    public void testDelete() throws Exception {
        dao.delete(1);
    }

    @Test
    public void testListPage() throws Exception {

        int page = 1;

        List<BoardVO> list = dao.listPage(page);

        for (BoardVO boardVO : list) {
            System.out.println(boardVO.getBno() + ":" + boardVO.getTitle());
        }
    }

    @Test
    public void testListCriteria() throws Exception {

        Criteria cri = new Criteria();
        cri.setPage(1);
        cri.setPerPageNum(10);

        List<BoardVO> list = dao.listCriteria(cri);

        for (BoardVO boardVO : list) {
            System.out.println(boardVO.getBno() + ":" + boardVO.getTitle());
        }
    }

    @Test
    public void testURI() throws Exception {

        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .path("/board/read")
                .queryParam("bno", 12)
                .queryParam("perPageNum", 20).build();

        System.out.println("*******************************");
        System.out.println("/board/read?bno=12&perPageNum=20");
        System.out.println(uriComponents.toString());
    }

    @Test
    public void dynamic1() throws Exception {

        SearchCriteria cri = new SearchCriteria();
        cri.setPage(1);
        cri.setKeyword("444");
        cri.setSearchType("t");

        System.out.println("*********************");

        List<BoardVO> list = dao.listSearch(cri);

        for(BoardVO boardVO : list) {
            System.out.println(boardVO.getBno() + ": " + boardVO.getTitle());
        }

        System.out.println("*********************");
        System.out.println("COUNT : " + dao.listSearchCount(cri));
    }
}
