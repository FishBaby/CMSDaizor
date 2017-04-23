package com.cecb2b.cms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cecb2b.cms.common.base.BaseModel;

@Entity
@Table(name = "user_menu", catalog = "zfa_cms")
public class UserMenu extends BaseModel {
	@Column(name = "pid")
	private Long pid;

	@Column(name = "icon")
	private String icon;

	@Column(name = "name")
	private String name;

	@Column(name = "url")
	private String url;

	@Column(name = "description")
	private String description;

	@Override
	public String toString() {
		return "UserMenu [pid=" + pid + ", icon=" + icon + ", name=" + name
				+ ", url=" + url + ", description=" + description + "]";
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
