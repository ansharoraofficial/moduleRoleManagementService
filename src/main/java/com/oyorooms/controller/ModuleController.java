package com.oyorooms.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	@PostMapping(value = "/role/{roleId}/module")
	public void addModule(@PathVariable Long roleId, @Valid @RequestBody List<Module> module) {
		// System.out.println(module);

		moduleService.addModule(roleId, module);
	}

	@GetMapping(value = "/role/{roleId}/module")
	public List<Module> getModule(@PathVariable Long roleId) {
		return moduleService.getModule(roleId);
	}

}

/*
 * { "id": 6, "roleName": "PM", "description": null, "module":[ {
 * "moduleName":"Pricing", "readAction":"/pricing",
 * "writeAction":"/pricing_write" }, { "moduleName":"Booking",
 * "readAction":"/booking", "writeAction":"/booking_write" }] }
 */
