package com.oyorooms.model.dto;

import com.oyorooms.model.request.UpdatedRoleEntity;

public class ModuleDTO {

	private String moduleName;
	private String readAction;
	private String writeAction;
	private UpdatedRoleEntity roleEntity;

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getReadAction() {
		return readAction;
	}

	public void setReadAction(String readAction) {
		this.readAction = readAction;
	}

	public String getWriteAction() {
		return writeAction;
	}

	public void setWriteAction(String writeAction) {
		this.writeAction = writeAction;
	}

	public UpdatedRoleEntity getRoleEntity() {
		return roleEntity;
	}

	public void setRoleEntity(UpdatedRoleEntity roleEntity) {
		this.roleEntity = roleEntity;
	}

}
