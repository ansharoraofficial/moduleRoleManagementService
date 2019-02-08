package com.oyorooms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oyorooms.converter.EntityToDtoConverter;
import com.oyorooms.entity.RoleEntity;
import com.oyorooms.errors.NotFoundException;
import com.oyorooms.model.dto.RoleEntityDTO;
import com.oyorooms.model.request.UpdatedRoleEntity;
import com.oyorooms.repository.ModuleRepository;
import com.oyorooms.repository.RoleEntityRepository;
import com.oyorooms.entity.Module;

@Service
public class RoleEntityService {

	@Autowired
	private RoleEntityRepository roleEntityRepository;
	
	@Autowired
	private ModuleRepository moduleRepository;

	@Autowired
	private ModuleService moduleService;

	public void addRole(List<UpdatedRoleEntity> roleEntity) {

		// System.out.println(Entity.getRoleEntity().size());

		/*
		 * List<UpdatedRoleEntity> updatedRoleEntity = Entity.getRoleEntity(); for (int
		 * i = 0; i < updatedRoleEntity.size(); i++) { RoleEntity role = new
		 * RoleEntity(); role.setRoleName(updatedRoleEntity.get(i).getRoleName());
		 * role.setDescription(updatedRoleEntity.get(i).getDescription());
		 * roleEntityRepository.save(role); moduleService.addModule(role.getId(),
		 * role.getModule());
		 * 
		 * }
		 */

		for (UpdatedRoleEntity roleObj : roleEntity) {
			RoleEntity role = addRoleEntity(roleObj);
			// System.out.println(role.getDescription());
			moduleService.addModule(role.getId(), roleObj.getModule());
			/*
			 * RoleEntity role = new RoleEntity(); role.setRoleName(roleObj.getRoleName());
			 * role.setDescription(roleObj.getDescription());
			 * 
			 * roleEntityRepository.save(role); // System.out.println(role);
			 */

		}

		// moduleService.addModule(roleEntity.getId(), roleEntity.getModule());

		// role.setModule(roleEntity.getModule());

		// moduleService.addModule(roleId, module)
	}

	@Transactional
	private RoleEntity addRoleEntity(UpdatedRoleEntity roleObj) {
		RoleEntity role = new RoleEntity();
		role.setRoleName(roleObj.getRoleName());
		role.setDescription(roleObj.getDescription());

		return roleEntityRepository.save(role);
	}

	public List<RoleEntityDTO> getAllRoles() {
		// List<RoleEntity> roleEntity = roleEntityRepository.findAll();

		List<RoleEntityDTO> roleEntityDTO = new ArrayList<>();
		List<RoleEntity> roleEntity = roleEntityRepository.findAll();

		for (RoleEntity role : roleEntity) {
			RoleEntityDTO roleDTO = EntityToDtoConverter.map(role, RoleEntityDTO.class);

			roleEntityDTO.add(roleDTO);
		}
		return roleEntityDTO;
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
//			role.get().setRoleName(roleEntity.getRoleName());
			role.get().setDescription(roleEntity.getDescription());
//			role.get().setModule(roleEntity.getModule());
			roleEntityRepository.save(role.get());
//			moduleService.updateModule(id, roleEntity.getModule());
			
//			String oldRoleName = role.get().getRoleName();
//			String oldDescription = role.get().getDescription();
//			List<Module> oldModule = role.get().getModule();
//			
//			RoleEntity updatedRole = new RoleEntity();
//			
//			String newRoleName = roleEntity.getRoleName();
//			String newDescription = roleEntity.getDescription();
//			List<Module> newModule = roleEntity.getModule();
//			
//			if(newRoleName.isEmpty()) updatedRole.setRoleName(oldRoleName);
//			else updatedRole.setRoleName(newRoleName);
//			
//			if(newDescription.isEmpty()) updatedRole.setDescription(oldDescription);
//			else updatedRole.setDescription(newDescription);
//			
//			if(newModule.isEmpty()) updatedRole.setModule(oldModule);
//			else updatedRole.setModule(newModule);
//			
//			updatedRole = roleEntityRepository.save(updatedRole);
//			moduleService.addModule(updatedRole.getId(), updatedRole.getModule());
//			deleteRole(id);
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
