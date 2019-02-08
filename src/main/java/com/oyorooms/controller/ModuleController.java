package com.oyorooms.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oyorooms.entity.Module;
import com.oyorooms.service.ModuleService;

@RestController
public class ModuleController {

	@Autowired
	private ModuleService moduleService;

	/*
	 * @Autowired private RoleEntityService roleEntityService;
	 */

	@PostMapping(value = "/module/{roleId}")
	public void addModule(@PathVariable Long roleId, @Valid @RequestBody List<Module> module) {
		// System.out.println(module);

		moduleService.addModule(roleId, module);
	}

	@GetMapping(value = "/module/{roleId}")
	public List<Module> getModule(@PathVariable Long roleId) {
		return moduleService.getModule(roleId);
	}
	
	@RequestMapping(value = "/module/read/{moduleName}")
	public String getReadAction(@PathVariable String moduleName) {
		return moduleService.getReadAction(moduleName);
	}
	
	@RequestMapping(value = "/module/write/{moduleName}")
	public String getWriteAction(@PathVariable String moduleName) {
		return moduleService.getWriteAction(moduleName);
	}
	
	@RequestMapping(value = "/module/action/{action}")
	public String getModuleByAction(@PathVariable String action) {
		return moduleService.getModuleByAction(action);
	}
	
	@PutMapping(value = "module_update/{module_id}")
	public void updateModule(@PathVariable("module_id") Long moduleId, @Valid @RequestBody Module module) {
		moduleService.updateModule(moduleId, module);
	}

}

/*
 * { "id": 6, "roleName": "PM", "description": null, "module":[ {
 * "moduleName":"Pricing", "readAction":"/pricing",
 * "writeAction":"/pricing_write" }, { "moduleName":"Booking",
 * "readAction":"/booking", "writeAction":"/booking_write" }] }
 */
