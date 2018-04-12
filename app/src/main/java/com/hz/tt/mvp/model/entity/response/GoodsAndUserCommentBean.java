package com.hz.tt.mvp.model.entity.response;

/**
 * Created by Administrator on 2018-04-12.
 */

public class GoodsAndUserCommentBean {
    /**
     * commentId : 12
     * commentUser : 张三
     * content : 还不错
     * createDate : Apr 8, 2018 5:02:20 PM
     * createUser : admin
     * recordCode : 123456786
     * starLevel : 1
     * recordName
     */
    private int commentId;
    private String commentUser;
    private String content;
    private String createDate;
    private String createUser;
    private String recordCode;
    private int starLevel;

    public String getRecordName() {
        return recordName;
    }

    public void setRecordName(String recordName) {
        this.recordName = recordName;
    }

    private String recordName;

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getCommentUser() {
        return commentUser;
    }

    public void setCommentUser(String commentUser) {
        this.commentUser = commentUser;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getRecordCode() {
        return recordCode;
    }

    public void setRecordCode(String recordCode) {
        this.recordCode = recordCode;
    }

    public int getStarLevel() {
        return starLevel;
    }

    public void setStarLevel(int starLevel) {
        this.starLevel = starLevel;
    }
}
