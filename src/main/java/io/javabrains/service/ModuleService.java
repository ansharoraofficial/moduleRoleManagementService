package io.javabrains.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.javabrains.entity.Module;
import io.javabrains.errors.NotFoundException;
import io.javabrains.repository.ModuleRepository;
import io.javabrains.repository.RoleEntityRepository;

@Service
public class ModuleService {

	@Autowired
	private ModuleRepository moduleRepository;

	@Autowired
	private RoleEntityRepository roleEntityRepository;

	public Module addModule(Long roleId, @Valid Module module) {

		return roleEntityRepository.findById(roleId).map(role -> {
			module.setRoleEntity(role);
			return moduleRepository.save(module);
		}).orElseThrow(() -> new NotFoundException("Role Not Found"));
	}

	public List<Module> getModule(Long roleId) {

		if (!roleEntityRepository.existsById(roleId)) {
			throw new NotFoundException("Role Not Found");
		}
		return moduleRepository.findByRoleEntityId(roleId);
	}

}
