package com.spark.parquet;

import java.io.Serializable;
import java.sql.Date;

public class TrsserverClusterLog implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String topic;
	private String userName;
	private int params_length;
	private int wordNum;
	private String hostName;
	private String clientIP;
	private String object;
	private int recordNum;
	private long costTime;
	private String instanceID;
	private String filePath;
	private String threadID;
	private String errorID;
	private String errorPrompt;
	private String success;
	private String startID;
	private int classNum;
	private int validRecNum;
	private String operate;
	private String exParam;
	private Date beginTime;
	private String resultID;
	private String params;
	private Date endTime;
	private int sectionNum;
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getParams_length() {
		return params_length;
	}
	public void setParams_length(int params_length) {
		this.params_length = params_length;
	}
	public int getWordNum() {
		return wordNum;
	}
	public void setWordNum(int wordNum) {
		this.wordNum = wordNum;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getClientIP() {
		return clientIP;
	}
	public void setClientIP(String clientIP) {
		this.clientIP = clientIP;
	}
	public String getObject() {
		return object;
	}
	public void setObject(String object) {
		this.object = object;
	}
	public int getRecordNum() {
		return recordNum;
	}
	public void setRecordNum(int recordNum) {
		this.recordNum = recordNum;
	}
	public long getCostTime() {
		return costTime;
	}
	public void setCostTime(long costTime) {
		this.costTime = costTime;
	}
	public String getInstanceID() {
		return instanceID;
	}
	public void setInstanceID(String instanceID) {
		this.instanceID = instanceID;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getThreadID() {
		return threadID;
	}
	public void setThreadID(String threadID) {
		this.threadID = threadID;
	}
	public String getErrorID() {
		return errorID;
	}
	public void setErrorID(String errorID) {
		this.errorID = errorID;
	}
	public String getErrorPrompt() {
		return errorPrompt;
	}
	public void setErrorPrompt(String errorPrompt) {
		this.errorPrompt = errorPrompt;
	}
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	public String getStartID() {
		return startID;
	}
	public void setStartID(String startID) {
		this.startID = startID;
	}
	public int getClassNum() {
		return classNum;
	}
	public void setClassNum(int classNum) {
		this.classNum = classNum;
	}
	public int getValidRecNum() {
		return validRecNum;
	}
	public void setValidRecNum(int validRecNum) {
		this.validRecNum = validRecNum;
	}
	public String getOperate() {
		return operate;
	}
	public void setOperate(String operate) {
		this.operate = operate;
	}
	public String getExParam() {
		return exParam;
	}
	public void setExParam(String exParam) {
		this.exParam = exParam;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public String getResultID() {
		return resultID;
	}
	public void setResultID(String resultID) {
		this.resultID = resultID;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public int getSectionNum() {
		return sectionNum;
	}
	public void setSectionNum(int sectionNum) {
		this.sectionNum = sectionNum;
	}

}
