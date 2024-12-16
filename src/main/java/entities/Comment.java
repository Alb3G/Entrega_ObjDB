package entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class Comment implements Serializable {

    private static final long SERIAL_VERSION_UID = 1L;
    @Id
    @GeneratedValue
    private Long id;
    private String content;
    private Integer rating;
    @ManyToOne
    private User user;

    public void addUser(User u) {
        u.getComments().add(this);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", rating=" + rating +
                ", user=" + user.getName() +
                '}';
    }
}
