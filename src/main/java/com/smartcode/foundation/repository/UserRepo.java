package com.smartcode.foundation.repository;

import com.smartcode.foundation.domain.dto.Page;
import com.smartcode.foundation.domain.dto.SearchUsersQuery;
import com.smartcode.foundation.domain.exception.NotFoundException;
import com.smartcode.foundation.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository @CacheConfig(cacheNames = "users")
public interface UserRepo extends UserRepoCustom, CrudRepository<User, Long> {

    @CacheEvict(allEntries = true)
    <S extends User> List<S> saveAll(Iterable<S> entities);

    @Caching(evict = {
            @CacheEvict(key = "#p0.id"),
            @CacheEvict(key = "#p0.username")
    })
    <S extends User> S save(S entity);

    @Cacheable
    Optional<User> findById(Long id);

    @Cacheable
    default User getById(Long id) {
        Optional<User> optionalUser = findById(id);
        if (optionalUser.isEmpty()) {
            throw new NotFoundException(User.class, id);
        }
        if (!optionalUser.get().isEnabled()) {
            throw new NotFoundException(User.class, id);
        }
        return optionalUser.get();
    }

    @Cacheable
    Optional<User> findByUsername(String username);

}

interface UserRepoCustom {

    List<User> searchUsers(Page page, SearchUsersQuery query);

}

@RequiredArgsConstructor
class UserRepoCustomImpl implements UserRepoCustom {

    @Autowired
    EntityManager em;

    @Override
    public List<User> searchUsers(Page page, SearchUsersQuery query) {
        return em.createQuery(em.getCriteriaBuilder().createQuery(User.class)).getResultList();
    }
}
