package com.oyorooms.model.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.oyorooms.entity.Module;

public class UpdatedRoleEntity implements Serializable {

	private static final long serialVersionUID = -7641493390639214732L;
	private Long id;
	private String roleName;
	private String description;
	private List<Module> module = new ArrayList<>();

	public List<Module> getModule() {
		return module;
	}

	public void setModule(List<Module> module) {
		this.module = module;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
