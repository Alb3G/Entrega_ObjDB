import lombok.Getter;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DbConn {
    @Getter
    private static final EntityManagerFactory entityManagerFactory;

    static {
        entityManagerFactory = Persistence.createEntityManagerFactory("data.odb");
    }
}
