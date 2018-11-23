package com.example.boot.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 *应答报文实体
 */
public class ResMessage implements Serializable {
	@JsonIgnore
	private Integer id;//主键
	@JsonIgnore
	private String field_name;//名称
	@JsonIgnore
	private String field_type;//类型,String、num、数组
	@JsonIgnore
	private String field_length;//长度
	@JsonIgnore
	private String is_empty;//是否为空，可选、必选
	@JsonIgnore
	private String rule;//规则规则
	@JsonIgnore
	private Integer parent_id;//父级id,根节点为0
	@JsonIgnore
	private String parent_name;//父级名称，根节点为null
	@JsonIgnore
	private String field_desc;//描述
	@JsonIgnore
	private String create_user;//创建人
	@JsonIgnore
	private String create_time;//创建时间
	@JsonIgnore
	private String edit_user;//最后修改人
	@JsonIgnore
	private String edit_time;//最后修改时间
	@JsonIgnore
	private String constraints;//约束
	@JsonIgnore
	private String value_desc;//值说明
	@JsonIgnore
	private Integer temp_id;//协议模板主键
	@JsonIgnore
	private String order_id;//排序，在该树唯一
	@JsonIgnore
	private String is_select;//是否下拉，0 非下拉选项，1下拉选项
	@JsonIgnore
	private String select_list;//下拉值；key1:value1;key2:value2;
	@JsonIgnore
	ArrayList<HashMap<String, String>> selectList;
	@JsonIgnore
	private List<ResMessage> children=new ArrayList<>();
	@JsonIgnore
	private String value;

	@JsonIgnore
	private HashMap<String,String> map;

	public ResMessage() {
	}

	public HashMap<String, String> getMap() {
		return map;
	}
	public void setMap(HashMap<String, String> map) {
		this.map = map;
	}
	public List<ResMessage> getChildren() {
		return children;
	}
	public void setChildren(List<ResMessage> children) {
		this.children = children;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getField_name() {
		return field_name;
	}
	public void setField_name(String field_name) {
		this.field_name = field_name;
	}
	public String getField_type() {
		return field_type;
	}
	public void setField_type(String field_type) {
		this.field_type = field_type;
	}
	public String getField_length() {
		return field_length;
	}
	public void setField_length(String field_length) {
		this.field_length = field_length;
	}
	public String getIs_empty() {
		return is_empty;
	}
	public void setIs_empty(String is_empty) {
		this.is_empty = is_empty;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public ArrayList<HashMap<String, String>> getSelectList() {
		return selectList;
	}

	public void setSelectList(ArrayList<HashMap<String, String>> selectList) {
		this.selectList = selectList;
	}

	public String getRule() {
		return rule;
	}
	public void setRule(String rule) {
		this.rule = rule;
	}
	public Integer getParent_id() {
		return parent_id;
	}
	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}
	public String getParent_name() {
		return parent_name;
	}
	public void setParent_name(String parent_name) {
		this.parent_name = parent_name;
	}
	public String getField_desc() {
		return field_desc;
	}
	public void setField_desc(String field_desc) {
		this.field_desc = field_desc;
	}
	public String getCreate_user() {
		return create_user;
	}
	public void setCreate_user(String create_user) {
		this.create_user = create_user;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getEdit_user() {
		return edit_user;
	}
	public void setEdit_user(String edit_user) {
		this.edit_user = edit_user;
	}
	public String getEdit_time() {
		return edit_time;
	}
	public void setEdit_time(String edit_time) {
		this.edit_time = edit_time;
	}

	public String getConstraints() {
		return constraints;
	}
	public void setConstraints(String constraints) {
		this.constraints = constraints;
	}
	public String getValue_desc() {
		return value_desc;
	}
	public void setValue_desc(String value_desc) {
		this.value_desc = value_desc;
	}
	public Integer getTemp_id() {
		return temp_id;
	}
	public void setTemp_id(Integer temp_id) {
		this.temp_id = temp_id;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	
	public String getIs_select() {
		return is_select;
	}
	public void setIs_select(String is_select) {
		this.is_select = is_select;
	}
	public String getSelect_list() {
		return select_list;
	}
	public void setSelect_list(String select_list) {
		this.select_list = select_list;
	}
	@Override
	public String toString() {
		return "ResMessage [id=" + id + ", field_name=" + field_name + ", field_type=" + field_type
				+ ", field_length=" + field_length + ", is_empty=" + is_empty + ", rule=" + rule + ", parent_id="
				+ parent_id + ", parent_name=" + parent_name + ", field_desc=" + field_desc + ", create_user="
				+ create_user + ", create_time=" + create_time + ", edit_user=" + edit_user + ", edit_time=" + edit_time
				+ ", constraints=" + constraints + ", value_desc=" + value_desc + ", temp_id=" + temp_id + ", order_id="
				+ order_id + ", children=" + children + "]";
	}
}
