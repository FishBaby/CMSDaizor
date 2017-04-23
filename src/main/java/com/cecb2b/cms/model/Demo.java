package com.cecb2b.cms.model;

import com.cecb2b.cms.common.base.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 */
@Entity
@Table(name = "demo", catalog = "zfa_cms")
public class Demo extends BaseModel {

	@Column(name = "name")
	private String name;

	@Column(name = "depict")
	private String depict;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepict() {
		return depict;
	}

	public void setDepict(String depict) {
		this.depict = depict;
	}
}
