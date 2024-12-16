package entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class User implements Serializable {
    private static final long SERIAL_VERSION_UID = 1L;
    @Id
    @GeneratedValue
    private Long id;
    private String email;
    private String name;
    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Comment> comments = new ArrayList<>();

    public void addComment(Comment c) {
        c.setUser(this);
        comments.add(c);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", comments=" + comments +
                '}';
    }
}
