package BookTime.entity;

import com.devmountain.specaptsone.BookTime.DTO.UserDTO;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @Column
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER/*, cascade = {CascadeType.MERGE, CascadeType.PERSIST}*/)
    @JsonManagedReference
    private List<Completion> completions = new ArrayList<>();

    public User (UserDTO userDTO) {
        if(userDTO.getUsername() != null){
            this.username=userDTO.getUsername();
        }
        if(userDTO.getPassword() != null){
            this.password=userDTO.getPassword();
        }

    }
}
