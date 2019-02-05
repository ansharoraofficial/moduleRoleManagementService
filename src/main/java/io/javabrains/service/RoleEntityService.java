package io.javabrains.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.javabrains.entity.RoleEntity;
import io.javabrains.errors.NotFoundException;
import io.javabrains.repository.RoleEntityRepository;

@Service
public class RoleEntityService {

	@Autowired
	private RoleEntityRepository roleEntityRepository;

	public void addRole(RoleEntity roleEntity) {
		roleEntityRepository.save(roleEntity);

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

	public RoleEntity updateRoleEntity(Long id, @Valid RoleEntity roleEntity) {

		return roleEntityRepository.findById(id).map(role -> {
			role.setRoleName(roleEntity.getRoleName());
			role.setDescription(roleEntity.getDescription());
			return roleEntityRepository.save(role);
		}).orElseThrow(() -> new NotFoundException("Role Not Found With Id " + id));
	}

	public String deleteRole(Long id) {

		return roleEntityRepository.findById(id).map(role -> {
			roleEntityRepository.delete(role);
			return "Deleted Successfully";
		}).orElseThrow(() -> new NotFoundException("Role Not Found With Id " + id));
	}

}
