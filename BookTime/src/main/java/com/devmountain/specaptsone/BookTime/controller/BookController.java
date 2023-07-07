package com.devmountain.specaptsone.BookTime.controller;

import com.devmountain.specaptsone.BookTime.DTO.BookDTO;
import com.devmountain.specaptsone.BookTime.DTO.CompletionDTO;
import com.devmountain.specaptsone.BookTime.entity.Book;
import com.devmountain.specaptsone.BookTime.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping("/add")
    public List<String> addBook(@RequestBody BookDTO bookDTO) {
        System.out.println(bookDTO);
        return bookService.addBook(bookDTO);
    }

    @GetMapping("/{bookId}")
    public Optional<BookDTO> getBookById(@PathVariable Long bookId) {
        return bookService.getBookById(bookId);
    }

    @GetMapping("/all")
    public List<BookDTO> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PutMapping("/update")
    public void updateBookById(BookDTO bookDTO) {
        System.out.println(bookDTO);
        bookService.updateBookById(bookDTO);
    }

    @DeleteMapping("/delete/{bookId}")
    public void deleteBookById(@PathVariable Long bookId) {
        bookService.deleteBookById(bookId);
    }

}
