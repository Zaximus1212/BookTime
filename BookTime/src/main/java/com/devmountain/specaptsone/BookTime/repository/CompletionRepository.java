package com.devmountain.specaptsone.BookTime.repository;

import com.devmountain.specaptsone.BookTime.entity.Book;
import com.devmountain.specaptsone.BookTime.entity.Completion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompletionRepository extends JpaRepository<Completion, Long> {
    List<Completion> findAllByUserEquals(Completion completion);

    List<Completion> findAllByBookEquals(Book book);
}
