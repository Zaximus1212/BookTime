package BookTime.service;

import com.devmountain.specaptsone.BookTime.DTO.BookDTO;
import com.devmountain.specaptsone.BookTime.DTO.CompletionDTO;
import com.devmountain.specaptsone.BookTime.entity.Book;
import com.devmountain.specaptsone.BookTime.entity.Completion;
import com.devmountain.specaptsone.BookTime.repository.BookRepository;
import com.devmountain.specaptsone.BookTime.repository.CompletionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CompletionRepository completionRepository;

    @Override
    @Transactional
    public List<String> addBook(BookDTO bookDTO) {
        List<String> response = new ArrayList<>();
        Book book = new Book(bookDTO);
        bookRepository.saveAndFlush(book);
        response.add("Book added successfully.");
        return response;
    }

    @Override
    public Optional<BookDTO> getBookById(Long bookId) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if (bookOptional.isPresent()) {
            List<Completion> completions = completionRepository.findAllByBookIdEquals(bookOptional.get().getId());
            List<CompletionDTO> tempDTO = completions.stream().map(completion -> new CompletionDTO(completion)).collect(Collectors.toList());
            Optional<BookDTO> timeIncBookDTO = Optional.of(new BookDTO(bookOptional.get()));
            timeIncBookDTO.get().setCompletionDTOs(tempDTO);
            return timeIncBookDTO;
        }
        return Optional.empty();
    }

    @Override
    public List<BookDTO> getAllBooks() {   //so far this method has not had functionality to receive completion times added (CompletionDTO)
        List<Book> bookList = bookRepository.findAll();
        return bookList.stream().map(BookDTO::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateBookById(BookDTO bookDTO) {
        System.out.println(bookDTO);
        Optional<Book> bookOptional = bookRepository.findById(bookDTO.getId());
        bookOptional.ifPresent(book -> {
            book.setTitle(bookDTO.getTitle());
            bookRepository.saveAndFlush(book);
        });
    }

    @Override
    @Transactional
    public void deleteBookById(Long bookId) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        bookOptional.ifPresent(book -> bookRepository.delete(book));
    }


}
