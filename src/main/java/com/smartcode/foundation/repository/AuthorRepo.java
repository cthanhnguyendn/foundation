package com.smartcode.foundation.repository;

import com.smartcode.foundation.domain.dto.Page;
import com.smartcode.foundation.domain.dto.SearchAuthorsQuery;
import com.smartcode.foundation.domain.exception.NotFoundException;
import com.smartcode.foundation.domain.model.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import java.util.List;

@Repository
public interface AuthorRepo extends CrudRepository<Author, Long>, AuthorRepoCustom {

    default Author getById(Long id) {
        return findById(id).orElseThrow(() -> new NotFoundException(Author.class, id));
    }

    List<Author> findAllById(Iterable<Long> ids);

}

interface AuthorRepoCustom {

    List<Author> searchAuthors(Page page, SearchAuthorsQuery query);

}

@RequiredArgsConstructor
class AuthorRepoCustomImpl implements AuthorRepoCustom {

    @Autowired
    EntityManager em;

    @Override
    public List<Author> searchAuthors(Page page, SearchAuthorsQuery query) {
        return em.createQuery(em.getCriteriaBuilder().createQuery(Author.class)).getResultList();
    }
}