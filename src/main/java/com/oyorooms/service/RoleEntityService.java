package com.oyorooms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oyorooms.entity.RoleEntity;
import com.oyorooms.errors.NotFoundException;
import com.oyorooms.model.dto.RoleEntityDTO;
import com.oyorooms.model.request.UpdatedRoleEntity;
import com.oyorooms.repository.RoleEntityRepository;

import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Service
public class RoleEntityService {

	@Autowired
	private RoleEntityRepository roleEntityRepository;

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
		/*
		 * MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		 * mapperFactory.classMap(RoleEntity.class, RoleEntityDTO.class).byDefault();
		 * MapperFacade mapper = mapperFactory.getMapperFacade();
		 */
		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		BoundMapperFacade<RoleEntity, RoleEntityDTO> boundMapper = mapperFactory.getMapperFacade(RoleEntity.class,
				RoleEntityDTO.class);
		for (RoleEntity role : roleEntity) {
			RoleEntityDTO roleDTO = boundMapper.map(role);
			/*
			 * RoleEntityDTO role = new RoleEntityDTO(iterator.getId(),
			 * iterator.getRoleName(), iterator.getDescription(), iterator.getModule());
			 */
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
