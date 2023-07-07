package com.devmountain.specaptsone.BookTime.repository;

import com.devmountain.specaptsone.BookTime.entity.Book;
import com.devmountain.specaptsone.BookTime.entity.Completion;
import com.devmountain.specaptsone.BookTime.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
