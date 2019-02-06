package com.oyorooms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oyorooms.entity.RoleEntity;
import com.oyorooms.errors.NotFoundException;
import com.oyorooms.model.request.UpdatedRoleEntity;
import com.oyorooms.repository.RoleEntityRepository;

@Service
public class RoleEntityService {

	@Autowired
	private RoleEntityRepository roleEntityRepository;

	@Autowired
	private ModuleService moduleService;

	public void addRole(List<UpdatedRoleEntity> roleEntity) {
		for (int i = 0; i < roleEntity.size(); i++) {
			RoleEntity role = new RoleEntity();
			role.setRoleName(roleEntity.get(i).getRoleName());
			role.setDescription(roleEntity.get(i).getDescription());

			roleEntityRepository.save(role);
			// System.out.println(role);
			moduleService.addModule(role.getId(), roleEntity.get(i).getModule());
		}

		// moduleService.addModule(roleEntity.getId(), roleEntity.getModule());

		// role.setModule(roleEntity.getModule());

		// moduleService.addModule(roleId, module)
	}

	public List<RoleEntity> getAllRoles() {
		List<RoleEntity> roleEntity = new ArrayList<>();
		roleEntity = roleEntityRepository.findAll();
		return roleEntity;
	}

	public RoleEntity getRoleById(Long id) {
		Optional<RoleEntity> roleId = roleEntityRepository.findById(id);
		if (roleId.isPresent()) {
			return roleId.get();
		} else {
			throw new NotFoundException("Role Not found with Id " + id);
		}

	}

	public void updateRoleEntity(Long id, @Valid UpdatedRoleEntity roleEntity) {

		Optional<RoleEntity> role = roleEntityRepository.findById(id);
		if (role.isPresent()) {
			role.get().setRoleName(roleEntity.getRoleName());
			role.get().setDescription(roleEntity.getDescription());
			role.get().setModule(roleEntity.getModule());
			roleEntityRepository.save(role.get());
		} else {
			throw new NotFoundException("Role Not found with Id " + id);
		}
	}

	public String deleteRole(Long id) {

		return roleEntityRepository.findById(id).map(role -> {
			roleEntityRepository.delete(role);
			return "Deleted Successfully";
		}).orElseThrow(() -> new NotFoundException("Role Not Found With Id " + id));
	}

}
