package com.huiqu.model;

import java.util.Date;

public class NoteEntity {
	private String id; //笔记id
	private UserEntity user_info; //发布人
	private String note; //笔记内容
	private Date modify_date;	//创建时间
	private String[] assets = null; //附件id
	private String from_device; //发布设备
	private int reply_count;	//评论人数
	private int transmit_count; //转发人数
	private int[] scores = new int[5];		//评分 5级
	private int version;
	
	public NoteEntity(){}
	public NoteEntity(String user_id,String note){
		this.note = note;
		this.user_info = UserEntity.get(user_id);
		this.modify_date = new Date();
		this.version = 0;
		this.scores = new int[]{0,0,0,0,0};
		
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public UserEntity getUser_info() {
		return user_info;
	}
	public void setUser_info(UserEntity user_info) {
		this.user_info = user_info;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Date getModify_date() {
		return modify_date;
	}
	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}
	public String[] getAssets() {
		return assets;
	}
	public void setAssets(String[] assets) {
		this.assets = assets;
	}
	public String getFrom_device() {
		return from_device;
	}
	public void setFrom_device(String from_device) {
		this.from_device = from_device;
	}
	public int getReply_count() {
		return reply_count;
	}
	public void setReply_count(int reply_count) {
		this.reply_count = reply_count;
	}
	public int getTransmit_count() {
		return transmit_count;
	}
	public void setTransmit_count(int transmit_count) {
		this.transmit_count = transmit_count;
	}
	public int[] getScores() {
		return scores;
	}
	public void setScores(int[] scores) {
		this.scores = scores;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
}
