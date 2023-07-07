package BookTime.service;

import com.devmountain.specaptsone.BookTime.DTO.CompletionDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CompletionService {
    @Transactional
    void addCompletion(CompletionDTO completionDTO, Long bookId, Long userId);

    Optional<CompletionDTO> getCompletionById(Long completionId);

//    List<CompletionDTO> getAllCompletionByUserId(Long userId);

    List<CompletionDTO> getAllCompletionByBookId(Long bookId);

    @Transactional
    void updateCompletionById(CompletionDTO completionDTO);

    @Transactional
    void deleteCompletionById(Long completionId);
}
