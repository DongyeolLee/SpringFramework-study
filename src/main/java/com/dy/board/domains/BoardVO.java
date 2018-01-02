package com.dy.board.domains;

import java.util.Date;

public class BoardVO {
    private Integer bno;
    private String title;
    private String content;
    private String writer;
    private Date regdate;
    private int viewcnt;
    private int replycnt;

    public Integer getBno() {
        return bno;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getWriter() {
        return writer;
    }

    public Date getRegdate() {
        return regdate;
    }

    public int getViewcnt() {
        return viewcnt;
    }

    public int getReplycnt() {
        return replycnt;
    }

    public void setBno(Integer bno) {
        this.bno = bno;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    public void setViewcnt(int viewcnt) {
        this.viewcnt = viewcnt;
    }

    public void setReplycnt(int replycnt) {
        this.replycnt = replycnt;
    }

    @Override
    public String toString() {
        return "BoardVO{" +
                "bno=" + bno +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", writer='" + writer + '\'' +
                ", regdate=" + regdate +
                ", viewcnt=" + viewcnt +
                ", replycnt=" + replycnt +
                '}';
    }
}
