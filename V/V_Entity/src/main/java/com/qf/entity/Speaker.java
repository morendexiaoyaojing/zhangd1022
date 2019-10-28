package com.qf.entity;

public class Speaker {

    private Integer id;

    private String speaker_name;

    private String speaker_desc;

    private String speaker_job;

    private String head_img_url;

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSpeaker_name() {
        return speaker_name;
    }

    public void setSpeaker_name(String speaker_name) {
        this.speaker_name = speaker_name;
    }

    public String getSpeaker_desc() {
        return speaker_desc;
    }

    public void setSpeaker_desc(String speaker_desc) {
        this.speaker_desc = speaker_desc;
    }

    public String getSpeaker_job() {
        return speaker_job;
    }

    public void setSpeaker_job(String speaker_job) {
        this.speaker_job = speaker_job;
    }

    public String getHead_img_url() {
        return head_img_url;
    }

    public void setHead_img_url(String head_img_url) {
        this.head_img_url = head_img_url;
    }
}
