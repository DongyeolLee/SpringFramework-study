package com.dy.board.domains;

import java.util.Date;

public class MemberVO {
    private String userid;
    private String userpw;
    private String username;
    private String email;
    private Date regdate;
    private Date updatedate;

    public String getUserid() {
        return userid;
    }

    public String getUserpw() {
        return userpw;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Date getRegdate() {
        return regdate;
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setUserpw(String userpw) {
        this.userpw = userpw;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    @Override
    public String toString() {
        return "MemberVO{" +
                "userid='" + userid + '\'' +
                ", userpw='" + userpw + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", regdate=" + regdate +
                ", updatedate=" + updatedate +
                '}';
    }
}
