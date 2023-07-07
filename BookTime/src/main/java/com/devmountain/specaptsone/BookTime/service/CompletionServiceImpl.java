package com.devmountain.specaptsone.BookTime.service;

import com.devmountain.specaptsone.BookTime.DTO.BookDTO;
import com.devmountain.specaptsone.BookTime.DTO.CompletionDTO;
import com.devmountain.specaptsone.BookTime.DTO.UserDTO;
import com.devmountain.specaptsone.BookTime.entity.Book;
import com.devmountain.specaptsone.BookTime.entity.Completion;
import com.devmountain.specaptsone.BookTime.entity.User;
import com.devmountain.specaptsone.BookTime.repository.BookRepository;
import com.devmountain.specaptsone.BookTime.repository.CompletionRepository;
import com.devmountain.specaptsone.BookTime.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompletionServiceImpl implements CompletionService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CompletionRepository completionRepository;

    @Override
    @Transactional
    public void addCompletion(CompletionDTO completionDTO, Long userId, Long bookId) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        Optional<User> userOptional = userRepository.findById(userId);
        Completion completion = new Completion(completionDTO);
        bookOptional.ifPresent(completion::setBook);
        userOptional.ifPresent(completion::setUser);
        completionRepository.saveAndFlush(completion);

    }

    @Override
    public Optional<CompletionDTO> getCompletionById(Long completionId) {
        Optional<Completion> completionOptional = completionRepository.findById(completionId);
        Long bookId = completionOptional.get().getBook().getId();
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        Long userId = completionOptional.get().getUser().getId();
        Optional<User> userOptional = userRepository.findById(userId);
        if (completionOptional.isPresent()) {
            if (bookOptional.isPresent()) {
                if (userOptional.isPresent()) {
                    Optional<CompletionDTO> finishedCompletion = Optional.of(new CompletionDTO(completionOptional.get()));
                    finishedCompletion.get().setBookDTO(new BookDTO(bookOptional.get()));
                    finishedCompletion.get().setUserDTO(new UserDTO(userOptional.get()));
                    return finishedCompletion;
                }
                return Optional.empty();
            }
            return Optional.empty();
        }
        return Optional.empty();
    }

    @Override
    public List<CompletionDTO> getAllCompletionByUserId(Long userId) {
        Optional<Completion> completionOptional = completionRepository.findById(userId);
        if (completionOptional.isPresent()) {
            List<Completion> completionList = completionRepository.findAllByUserEquals(completionOptional.get());
            return completionList.stream().map(completion -> new CompletionDTO(completion)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    @Transactional
    public void updateCompletionById(CompletionDTO completionDTO) {
        System.out.println(completionDTO);
        Optional<Completion> completionOptional = completionRepository.findById(completionDTO.getId());
        completionOptional.ifPresent(completion -> {
            completion.setTime(completionDTO.getTime());
            completionRepository.saveAndFlush(completion);
        });
    }

    @Override
    @Transactional
    public void deleteCompletionById(Long completionId) {
        Optional<Completion> completionOptional = completionRepository.findById(completionId);
        completionOptional.ifPresent(completion -> completionRepository.delete(completion));
    }

}

