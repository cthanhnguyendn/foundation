package com.smartcode.foundation.repository;

import com.smartcode.foundation.domain.model.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepo extends CrudRepository<Role, String>{}