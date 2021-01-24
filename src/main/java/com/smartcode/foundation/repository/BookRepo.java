package com.smartcode.foundation.repository;


import com.smartcode.foundation.domain.dto.Page;
import com.smartcode.foundation.domain.dto.SearchBooksQuery;
import com.smartcode.foundation.domain.exception.NotFoundException;
import com.smartcode.foundation.domain.model.Book;
import lombok.RequiredArgsConstructor;
import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


@Repository
public interface BookRepo extends CrudRepository<Book, Long>, BookRepoCustom {

    default Book getById(Long id) {
        return findById(id).orElseThrow(() -> new NotFoundException(Book.class, id));
    }

    List<Book> findAllById(Iterable<Long> ids);

}

interface BookRepoCustom {

    List<Book> searchBooks(Page page, SearchBooksQuery query);

}

@RequiredArgsConstructor
class BookRepoCustomImpl implements BookRepoCustom {

    @Autowired
    EntityManager em;

    @Override
    public List<Book> searchBooks(Page page, SearchBooksQuery query) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Book> q = cb.createQuery(Book.class);
        Root<Book> book = q.from(Book.class);
        List<Criteria> criteriaList = new ArrayList<>();
        if (!StringUtils.isEmpty(query.getId())) {
            q.where(cb.equal(book.get("Id"),query.getId()));
        }
        if (!StringUtils.isEmpty(query.getCreatorId())) {
            q.where(cb.equal(book.get("creatorId"),query.getCreatorId()));
        }
        if (query.getCreatedAtStart() != null) {
            q.where(cb.greaterThanOrEqualTo(book.get("Id"),query.getCreatedAtStart()));
        }
        if (query.getCreatedAtEnd() != null) {
            q.where(cb.lessThan(book.get("createdAt"),query.getCreatedAtEnd()));
        }
//        if (!StringUtils.isEmpty(query.getTitle())) {
//            q.where(cb.r(book.get("createdAt"),query.getCreatedAtEnd()));
//            criteriaList.add(Criteria.where("title").regex(query.getTitle(), "i"));
//        }
//        if (!CollectionUtils.isEmpty(query.getGenres())) {
//            criteriaList.add(Criteria.where("genres").all(query.getGenres()));
//        }
//        if (!StringUtils.isEmpty(query.getIsbn13())) {
//            criteriaList.add(Criteria.where("isbn13").is(query.getIsbn13()));
//        }
//        if (!StringUtils.isEmpty(query.getIsbn10())) {
//            criteriaList.add(Criteria.where("isbn10").is(query.getIsbn10()));
//        }
//        if (!StringUtils.isEmpty(query.getPublisher())) {
//            criteriaList.add(Criteria.where("publisher").regex(query.getPublisher(), "i"));
//        }
//        if (query.getPublishDateStart() != null) {
//            criteriaList.add(Criteria.where("publishDate").gte(query.getPublishDateStart()));
//        }
//        if (query.getPublishDateEnd() != null) {
//            criteriaList.add(Criteria.where("publishDate").lt(query.getPublishDateEnd()));
//        }
//        if (!criteriaList.isEmpty()) {
//            Criteria bookCriteria = new Criteria().andOperator(criteriaList.toArray(new Criteria[0]));
//            operations.add(match(bookCriteria));
//        }
//
//        criteriaList = new ArrayList<>();
//        if (!StringUtils.isEmpty(query.getAuthorId())) {
//            criteriaList.add(Criteria.where("author._id").is(new ObjectId(query.getAuthorId())));
//        }
//        if (!StringUtils.isEmpty(query.getAuthorFullName())) {
//            criteriaList.add(Criteria.where("author.fullName").regex(query.getAuthorFullName(), "i"));
//        }
//        if (!criteriaList.isEmpty()) {
//            Criteria authorCriteria = new Criteria().andOperator(criteriaList.toArray(new Criteria[0]));
//            operations.add(lookup("authors", "authorIds", "_id", "author"));
//            operations.add(unwind("author", false));
//            operations.add(match(authorCriteria));
//        }
//
//        operations.add(sort(Sort.Direction.DESC, "createdAt"));
//        operations.add(skip((page.getNumber() - 1) * page.getLimit()));
//        operations.add(limit(page.getLimit()));

//        TypedAggregation<Book> aggregation = newAggregation(Book.class, operations);
//        System.out.println(aggregation.toString());
//        AggregationResults<Book> results = mongoTemplate.aggregate(aggregation, Book.class);
        return em.createQuery(q).getResultList();
    }


}

