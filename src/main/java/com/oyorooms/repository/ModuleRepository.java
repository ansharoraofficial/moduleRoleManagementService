package com.oyorooms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oyorooms.entity.Module;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {

	List<Module> findByRoleEntityId(Long roleId);

}
