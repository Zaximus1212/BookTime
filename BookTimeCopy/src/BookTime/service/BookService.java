package BookTime.service;

import com.devmountain.specaptsone.BookTime.DTO.BookDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface BookService {
    @Transactional
    List<String> addBook(BookDTO bookDTO);

    Optional<BookDTO> getBookById(Long bookId);

    List<BookDTO> getAllBooks();

    @Transactional
    void updateBookById(BookDTO bookDTO);

    @Transactional
    void deleteBookById(Long bookId);
}
