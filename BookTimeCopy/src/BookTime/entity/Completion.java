package BookTime.entity;

import com.devmountain.specaptsone.BookTime.DTO.CompletionDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Completion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private double time; //in minutes

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    @JsonBackReference
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    @JsonBackReference(value="book-completion")
    private Book book;

    public Completion(CompletionDTO completionDTO) {

        if(completionDTO.getTime() != 0) {
            this.time = completionDTO.getTime();
        }
    }
}
