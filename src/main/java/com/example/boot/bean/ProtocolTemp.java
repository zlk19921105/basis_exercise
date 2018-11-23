package com.example.boot.bean;

import java.util.Date;

/**
 * 模板协议实体
 * @author zhoulk
 * @date 2018-10-27
 */
public class ProtocolTemp {
	/**协议模板主键*/
	private Integer id;
	/**名称*/
	private String tempName;
	/**协议模板类型，json或者xml*/
	private String tempType;
	/**参考文档id,对应sys_file表主键*/
	private Integer documentId;
	/**参考文档名称*/
	private String documentName;
	/**报文（xml或者json格式）*/
	private String tempValue;
	/**创建人*/
	private String createUser;
	/**创建时间*/
	private Date createTime;
	/**最后修改人*/
	private String editUser;
	/**最后修改时间*/
	private Date editTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTempName() {
		return tempName;
	}

	public void setTempName(String tempName) {
		this.tempName = tempName;
	}

	public String getTempType() {
		return tempType;
	}

	public void setTempType(String tempType) {
		this.tempType = tempType;
	}

	public Integer getDocumentId() {
		return documentId;
	}

	public void setDocumentId(Integer documentId) {
		this.documentId = documentId;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public String getTempValue() {
		return tempValue;
	}

	public void setTempValue(String tempValue) {
		this.tempValue = tempValue;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getEditUser() {
		return editUser;
	}

	public void setEditUser(String editUser) {
		this.editUser = editUser;
	}

	public Date getEditTime() {
		return editTime;
	}

	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}

	@Override
	public String toString() {
		return "ProtocolTemp{" +
				"id=" + id +
				", tempName='" + tempName + '\'' +
				", tempType='" + tempType + '\'' +
				", documentId=" + documentId +
				", documentName='" + documentName + '\'' +
				", tempValue='" + tempValue + '\'' +
				", createUser='" + createUser + '\'' +
				", createTime=" + createTime +
				", editUser='" + editUser + '\'' +
				", editTime=" + editTime +
				'}';
	}

	public static void main(String[] args) {
		System.out.println("args = [" + args + "]");
	}
}
