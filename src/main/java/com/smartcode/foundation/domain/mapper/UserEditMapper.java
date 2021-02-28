package com.smartcode.foundation.domain.mapper;

import com.smartcode.foundation.domain.dto.CreateUserRequest;
import com.smartcode.foundation.domain.dto.UpdateUserRequest;
import com.smartcode.foundation.domain.model.Role;
import com.smartcode.foundation.domain.model.User;
import org.mapstruct.*;

import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;
import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring")
public abstract class UserEditMapper {

    @Mapping(target = "authorities", ignore = true)
    public abstract User create(CreateUserRequest request);

    @BeanMapping(nullValueCheckStrategy = ALWAYS, nullValuePropertyMappingStrategy = IGNORE)
    @Mapping(target = "authorities", ignore = true)
    public abstract void update(UpdateUserRequest request, @MappingTarget User user);

    @AfterMapping
    protected void afterCreate(CreateUserRequest request, @MappingTarget User user) {
        if (request.getAuthorities() != null) {
            user.setRoles(request.getAuthorities().stream().map(role -> new Role(role)).collect(Collectors.toSet()));
        }
    }

    @AfterMapping
    protected void afterUpdate(UpdateUserRequest request, @MappingTarget User user) {
        if (request.getAuthorities() != null) {
            user.setRoles(request.getAuthorities().stream().map(Role::new).collect(Collectors.toSet()));
        }
    }

}
