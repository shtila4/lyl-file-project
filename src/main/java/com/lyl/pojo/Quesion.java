package com.lyl.pojo;

public class Quesion {

    private long qid;
    private long uid;
    private String aim;
    private String evaluation;
    private String msg;

    public long getQid() {
        return qid;
    }

    public void setQid(long qid) {
        this.qid = qid;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getAim() {
        return aim;
    }

    public void setAim(String aim) {
        this.aim = aim;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public String getmsg() {
        return msg;
    }

    public void setmsg(String msg) {
        this.msg = msg;
    }

    public Quesion(long qid, long uid, String aim, String evaluation, String msg) {
        this.qid = qid;
        this.uid = uid;
        this.aim = aim;
        this.evaluation = evaluation;
        this.msg = msg;
    }

    public Quesion() {
    }

    @Override
    public String toString() {
        return "Quesion{" +
                "qid=" + qid +
                ", uid=" + uid +
                ", aim='" + aim + '\'' +
                ", evaluation='" + evaluation + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
