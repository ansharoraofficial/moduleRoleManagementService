package io.javabrains.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.javabrains.entity.RoleEntity;
import io.javabrains.service.RoleEntityService;

@RestController
public class RoleEntityController {

	@Autowired
	private RoleEntityService roleEntityService;

	@RequestMapping(value = "/role_write", method = RequestMethod.POST)
	public void addRoles(@RequestBody RoleEntity roleEntity) {
		roleEntityService.addRole(roleEntity);
	}

	@RequestMapping(value = "/role")
	public List<RoleEntity> getAllRoles() {
		return roleEntityService.getAllRoles();
	}

	@RequestMapping(value = "/role/{id}")
	public RoleEntity getRoleById(@PathVariable Long id) {
		return roleEntityService.getRoleById(id);
	}

	@PutMapping(value = "/role_update/{id}")
	public RoleEntity updateRoleEntity(@PathVariable Long id, @Valid @RequestBody RoleEntity roleEntity) {
		return roleEntityService.updateRoleEntity(id, roleEntity);
	}

	@DeleteMapping(value = "/role_delete/{id}")
	public String deleteRole(@PathVariable Long id) {
		return roleEntityService.deleteRole(id);
	}

}
