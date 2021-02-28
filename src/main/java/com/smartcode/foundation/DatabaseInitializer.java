package com.smartcode.foundation;


import com.smartcode.foundation.domain.dto.CreateUserRequest;
import com.smartcode.foundation.domain.model.Role;
import com.smartcode.foundation.repository.RoleRepo;
import com.smartcode.foundation.service.UserService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class DatabaseInitializer implements ApplicationListener<ApplicationReadyEvent> {

    private final List<String> usernames = List.of(
            "cthanhnguyendn@gmail.com",
            "admin@gmail.com"
    );
    private final List<String> fullNames = List.of(
            "Cong Thanh",
            "Admin"
    );
    private final List<String> roles = List.of(
            Role.USER_ADMIN,
            Role.AUTHOR_ADMIN
    );
    private final String password = "Test12345_";

    private final UserService userService;
    private final RoleRepo roleRepo;

    public DatabaseInitializer(UserService userService, RoleRepo roleRepo) {
        this.userService = userService;
        this.roleRepo = roleRepo;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        for(int i = 0; i < roles.size(); i++){
            if(roleRepo.findById(roles.get(i)) != null){
                roleRepo.save(new Role(roles.get(i)));
            }
        }
        for (int i = 0; i < usernames.size(); ++i) {
            CreateUserRequest request = new CreateUserRequest();
            request.setUsername(usernames.get(i));
            request.setFullName(fullNames.get(i));
            request.setPassword(password);
            request.setRePassword(password);
            request.setAuthorities(Set.of(roles.get(i)));
            userService.upsert(request);
        }
    }

}
