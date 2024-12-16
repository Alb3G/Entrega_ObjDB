package services;

import entities.Comment;
import entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class PersistenceService {

    private final EntityManagerFactory entityManagerFactory;

    public PersistenceService(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    /**
     * Método para persistir un usuario.
     * @param u User
     */
    public void saveUser(User u) {
        EntityManager em;
        try {
            em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();
            em.persist(u);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<User> getAll() {
        EntityManager em;
        List<User> users;
        try {
            em = entityManagerFactory.createEntityManager();
            TypedQuery<User> q = em.createQuery("select u from User u", User.class);
            users = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            users = null;
        }

        return users;
    }

    /**
     * Metodo para Obtener todos los comentarios de un usuario.
     * @param u User
     * @return Devuelve una lista de los Comentarios del usuario
     */
    public List<Comment> getAllCommentsByUser(User u) {
        EntityManager em;
        List<Comment> comments;
        try {
            em = entityManagerFactory.createEntityManager();
            TypedQuery<Comment> q = em.createQuery("select c from Comment c join c.user u where u.id = :id", Comment.class);
            q.setParameter("id", u.getId());
            comments = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            comments = null;
        }

        return comments;
    }

    public User findById(Long id) {
        EntityManager em;
        User u;

        try {
            em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();
            u = em.find(User.class, id);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            u = null;
        }

        return u;
    }

    /**
     * Metodo para guardar un comentario de un usuario en concreto.
     * @param u User
     * @param c Comment de un usuario.
     */
    public void saveComment(User u, Comment c) {
        EntityManager em;
        try {
            em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();
            User managedUser = em.find(User.class, u.getId());
            managedUser.addComment(c);
            em.merge(managedUser);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo para recuperar los Usuarios que tengan comentarios con
     * la puntuación máxima.
     * @return La lista de usuarios.
     */
    public List<User> getUsersWithMaxRatingComments() {
        EntityManager em;
        List<User> users;
        try {
            em = entityManagerFactory.createEntityManager();
            TypedQuery<User> q = em.createQuery("select u from User u join u.comments c where c.rating = 10", User.class);
            users = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            users = null;
        }

        return users;
    }
}
