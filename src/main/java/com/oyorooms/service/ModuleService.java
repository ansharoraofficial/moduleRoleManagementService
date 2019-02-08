package com.oyorooms.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oyorooms.entity.Module;
import com.oyorooms.entity.RoleEntity;
import com.oyorooms.errors.NotFoundException;
import com.oyorooms.repository.ModuleRepository;
import com.oyorooms.repository.RoleEntityRepository;

@Service
public class ModuleService {

	@Autowired
	private ModuleRepository moduleRepository;

	@Autowired
	private RoleEntityRepository roleEntityRepository;

	public void addModule(Long roleId, @Valid List<Module> module) {

		Optional<RoleEntity> role = roleEntityRepository.findById(roleId);
		if (role.isPresent()) {
			for (Module moduleObj : module) {
				// Module m=new Module();
				moduleObj.setRoleEntity(role.get());
				moduleRepository.save(moduleObj);

			}
		} else {
			throw new NotFoundException("Role Not Found");
		}
		// return moduleRepository.save(module);

		/*
		 * return roleEntityRepository.findById(roleId).map(role -> {
		 * module.setRoleEntity(role); return moduleRepository.save(module);
		 * }).orElseThrow(() -> new NotFoundException("Role Not Found"));
		 */

	}

	public List<Module> getModule(Long roleId) {

		if (!roleEntityRepository.existsById(roleId)) {
			throw new NotFoundException("Role Not Found");
		}
		return moduleRepository.findByRoleEntityId(roleId);
	}

	public String getReadAction(String moduleName) {
		return moduleRepository.findReadActionByModuleName(moduleName);
	}

	public String getWriteAction(String moduleName) {
		return moduleRepository.findWriteActionByModuleName(moduleName);
	}

	public String getModuleByAction(String action) {
		return moduleRepository.getModuleByAction(action);
	}

	public void updateModule(Long moduleId, @Valid Module module) {
		Optional<Module> oldModule = moduleRepository.findById(moduleId);
		if(oldModule.isPresent()) {
			if(!module.getModuleName().isEmpty())
			oldModule.get().setModuleName(module.getModuleName());
			if(!module.getReadAction().isEmpty())
			oldModule.get().setReadAction(module.getReadAction());
			if(!module.getWriteAction().isEmpty())
			oldModule.get().setWriteAction(module.getWriteAction());
			moduleRepository.save(oldModule.get());
		} else {
			throw new NotFoundException("Role Not found with Id " + moduleId);
		}
	}

}
