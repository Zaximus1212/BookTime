package com.devmountain.specaptsone.BookTime.controller;

import com.devmountain.specaptsone.BookTime.DTO.CompletionDTO;
import com.devmountain.specaptsone.BookTime.service.CompletionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/completion")
public class CompletionController {
    @Autowired
    private CompletionService completionService;

    @PostMapping("/complete/{userId}/{bookId}")
    public void addCompletion(@RequestBody CompletionDTO completionDTO, @PathVariable Long userId,
                              @PathVariable Long bookId) {
        completionService.addCompletion(completionDTO, userId, bookId);
    }

    @GetMapping("/{completionId}")
    public Optional<CompletionDTO> getCompletionById(@PathVariable Long completionId) {
        return completionService.getCompletionById(completionId);
    }

    @GetMapping("/user/{userId}")
    public List<CompletionDTO> getAllCompletionByUserId(@PathVariable Long userId) {
        return completionService.getAllCompletionByUserId(userId);
    }

    @PutMapping("/update")
    public void updateCompletionById(CompletionDTO completionDTO) {
        System.out.println(completionDTO);
        completionService.updateCompletionById(completionDTO);
    }

    @DeleteMapping("/delete/{completionId}")
    public void deleteCompletionById(@PathVariable Long completionId) {
        completionService.deleteCompletionById(completionId);
    }
}
