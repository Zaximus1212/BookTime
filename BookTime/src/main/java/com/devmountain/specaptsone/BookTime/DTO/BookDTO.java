package com.devmountain.specaptsone.BookTime.DTO;

import com.devmountain.specaptsone.BookTime.entity.Book;
import com.devmountain.specaptsone.BookTime.entity.Completion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class BookDTO implements Serializable {
    private Long id;
    private String title;
    private String author;
    private List<CompletionDTO> completionDTOs = new ArrayList<>();

    public BookDTO(Book book) {
        if (book.getId() != null) {
            this.id = book.getId();
        }
        if (book.getTitle() != null) {
            this.title = book.getTitle();
        }
        if (book.getAuthor() != null) {
            this.author = book.getAuthor();
        }

    }
}
