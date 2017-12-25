package com.dy.board.domains;

import java.util.Date;

public class ReplyVO {

    private Integer rno;
    private Integer bno;
    private String replytext;
    private String replyer;
    private Date regdate;
    private Date updatedate;

    public Integer getRno() {
        return rno;
    }

    public Integer getBno() {
        return bno;
    }

    public String getReplytext() {
        return replytext;
    }

    public String getReplyer() {
        return replyer;
    }

    public Date getRegdate() {
        return regdate;
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setRno(Integer rno) {
        this.rno = rno;
    }

    public void setBno(Integer bno) {
        this.bno = bno;
    }

    public void setReplytext(String replytext) {
        this.replytext = replytext;
    }

    public void setReplyer(String replyer) {
        this.replyer = replyer;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    @Override
    public String toString() {
        return "ReplyVO{" +
                "rno=" + rno +
                ", bno=" + bno +
                ", replytext='" + replytext + '\'' +
                ", replyer='" + replyer + '\'' +
                ", regdate=" + regdate +
                ", updatedate=" + updatedate +
                '}';
    }
}
