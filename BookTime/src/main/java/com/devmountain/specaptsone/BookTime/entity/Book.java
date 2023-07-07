package com.devmountain.specaptsone.BookTime.entity;

import com.devmountain.specaptsone.BookTime.DTO.BookDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference(value = "book-completion")
    private List<Completion> completions = new ArrayList<>();

    public Book(BookDTO bookDTO) {
        if (bookDTO.getTitle() != null) {
            this.title = bookDTO.getTitle();
        }
        if (bookDTO.getAuthor() != null) {
            this.author = bookDTO.getAuthor();
        }
//        if (bookDTO.getCompletions() != null) {
//            this.completions = bookDTO.getCompletions();
//        }
    }
}
