import entities.Comment;
import entities.User;
import services.PersistenceService;

public class Main {
    public static void main(String[] args) {
        PersistenceService ps = new PersistenceService(DbConn.getEntityManagerFactory());
        User user1 = new User();
        user1.setEmail("user1@example.com");
        user1.setName("User One");

        User user2 = new User();
        user2.setEmail("user2@example.com");
        user2.setName("User Two");

        User user3 = new User();
        user3.setEmail("user3@example.com");
        user3.setName("User Three");

        Comment comment1 = new Comment();
        comment1.setContent("Great post!");
        comment1.setRating(10);

        Comment comment2 = new Comment();
        comment2.setContent("Very informative.");
        comment2.setRating(4);

        Comment comment3 = new Comment();
        comment3.setContent("I disagree with this.");
        comment3.setRating(2);


        // Añadimos comentarios a los usuarios
        user1.addComment(comment1);
        user1.addComment(comment2);
        user2.addComment(comment2);
        user3.addComment(comment2);
        user3.addComment(comment3);

        // Persistimos los usuarios
        ps.saveUser(user1);
        ps.saveUser(user2);
        ps.saveUser(user3);

        // Listar comentarios de usuario 1 por ejemplo
        ps.getAllCommentsByUser(ps.findById(1L)).forEach(System.out::println);

        // Añadir comentario por parte de un usuario
        ps.saveComment(ps.findById(2L), comment3);

        // Listar usuarios que tienen comentarios con nota maxima = 10
        ps.getUsersWithMaxRatingComments().forEach(System.out::println);

        // Listar users
        ps.getAll().forEach(System.out::println);
    }
}
