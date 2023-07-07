package BookTime.DTO;

import com.devmountain.specaptsone.BookTime.entity.Completion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompletionDTO implements Serializable {
    private Long id;
    private double time;
    private UserDTO userDTO;
    private BookDTO bookDTO;

    public CompletionDTO(Completion completion) {
        if(completion.getId() != null) {
            this.id = completion.getId();
        }
        if(completion.getTime() != 0) {
            this.time = completion.getTime();
        }
    }
}
