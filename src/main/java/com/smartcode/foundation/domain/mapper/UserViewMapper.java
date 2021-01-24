package com.smartcode.foundation.domain.mapper;

import com.smartcode.foundation.domain.dto.UserView;
import com.smartcode.foundation.domain.model.User;
import com.smartcode.foundation.repository.UserRepo;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring")
public abstract class UserViewMapper {

    @Autowired
    private UserRepo userRepo;
    @BeanMapping(nullValueCheckStrategy = ALWAYS, nullValuePropertyMappingStrategy = IGNORE)
    public abstract UserView toUserView(User user);
    @BeanMapping(nullValueCheckStrategy = ALWAYS, nullValuePropertyMappingStrategy = IGNORE)
    public abstract List<UserView> toUserView(List<User> users);

    public UserView toUserViewById(Long id) {
        if (id == null) {
            return null;
        }
        return toUserView(userRepo.getById(id));
    }

}
