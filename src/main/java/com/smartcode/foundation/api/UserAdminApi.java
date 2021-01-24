package com.smartcode.foundation.api;

import com.smartcode.foundation.domain.dto.*;
import com.smartcode.foundation.domain.model.Role;
import com.smartcode.foundation.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@Tag(name = "UserAdmin")
@RestController @RequestMapping(path = "api/admin/user")
@RolesAllowed(Role.USER_ADMIN)
@RequiredArgsConstructor
public class UserAdminApi {

    private final UserService userService;

    @PostMapping
    public UserView create(@RequestBody @Valid CreateUserRequest request) {
        return userService.create(request);
    }

    @PutMapping("{id}")
    public UserView update(@PathVariable String id, @RequestBody @Valid UpdateUserRequest request) {
        return userService.update(Long.parseLong(id), request);
    }

    @DeleteMapping("{id}")
    public UserView delete(@PathVariable String id) {
        return userService.delete(Long.parseLong(id));
    }

    @GetMapping("{id}")
    public UserView get(@PathVariable String id) {
        return userService.getUser(Long.parseLong(id));
    }

    @PostMapping("search")
    public ListResponse<UserView> search(@RequestBody SearchRequest<SearchUsersQuery> request) {
        return new ListResponse<>(userService.searchUsers(request.getPage(), request.getQuery()));
    }

}
