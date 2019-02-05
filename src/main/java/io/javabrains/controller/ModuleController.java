package io.javabrains.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.javabrains.entity.Module;
import io.javabrains.service.ModuleService;

@RestController
public class ModuleController {

	@Autowired
	private ModuleService moduleService;

	/*
	 * @Autowired private RoleEntityService roleEntityService;
	 */

	@PostMapping(value = "/role/{roleId}/module")
	public Module addModule(@PathVariable Long roleId, @Valid @RequestBody Module module) {

		return moduleService.addModule(roleId, module);
	}

	@GetMapping(value = "/role/{roleId}/module")
	public List<Module> getModule(@PathVariable Long roleId) {
		return moduleService.getModule(roleId);
	}

}
