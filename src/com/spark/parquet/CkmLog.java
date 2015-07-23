package com.spark.parquet;

import java.io.Serializable;
import java.sql.Date;

public class CkmLog implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String topic;
	private String checkcode;
	private long processcosttime;
	private Date logtime;
	private String table;
	private String transid;
	private String logtype;
	private int totalcount;
	private int readcount;
	private String hostid;
	private String logcontent;
	private String succeed;
	private String writecount;
	private long writecosttime;
	private long costtime;
	private long readcosttime;
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getCheckcode() {
		return checkcode;
	}
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	public long getProcesscosttime() {
		return processcosttime;
	}
	public void setProcesscosttime(long processcosttime) {
		this.processcosttime = processcosttime;
	}
	public Date getLogtime() {
		return logtime;
	}
	public void setLogtime(Date logtime) {
		this.logtime = logtime;
	}
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public String getTransid() {
		return transid;
	}
	public void setTransid(String transid) {
		this.transid = transid;
	}
	public String getLogtype() {
		return logtype;
	}
	public void setLogtype(String logtype) {
		this.logtype = logtype;
	}
	public int getTotalcount() {
		return totalcount;
	}
	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
	}
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	public String getHostid() {
		return hostid;
	}
	public void setHostid(String hostid) {
		this.hostid = hostid;
	}
	public String getLogcontent() {
		return logcontent;
	}
	public void setLogcontent(String logcontent) {
		this.logcontent = logcontent;
	}
	public String getSucceed() {
		return succeed;
	}
	public void setSucceed(String succeed) {
		this.succeed = succeed;
	}
	public String getWritecount() {
		return writecount;
	}
	public void setWritecount(String writecount) {
		this.writecount = writecount;
	}
	public long getWritecosttime() {
		return writecosttime;
	}
	public void setWritecosttime(long writecosttime) {
		this.writecosttime = writecosttime;
	}
	public long getCosttime() {
		return costtime;
	}
	public void setCosttime(long costtime) {
		this.costtime = costtime;
	}
	public long getReadcosttime() {
		return readcosttime;
	}
	public void setReadcosttime(long readcosttime) {
		this.readcosttime = readcosttime;
	}
}
