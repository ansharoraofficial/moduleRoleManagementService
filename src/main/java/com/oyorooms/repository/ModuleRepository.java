package com.oyorooms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.oyorooms.entity.Module;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {

	List<Module> findByRoleEntityId(Long roleId);

	@Query(value = "select distinct m.read_action from module m where module_name = ?1", nativeQuery = true)
	String findReadActionByModuleName(String moduleName);

	@Query(value = "select distinct m.write_action from module m where module_name = ?1", nativeQuery = true)
	String findWriteActionByModuleName(String moduleName);

	@Query("select m.module_name from module m where m.read_action = :action or m.write_action = :action")
	String getModuleByAction(@Param("action") String action);

}
