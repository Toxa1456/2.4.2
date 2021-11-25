package web.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


@Repository
@Transactional
public class UserDaoImp implements UserDao {

   @PersistenceContext
   private EntityManager entityManager;

   @Override
   @Transactional
   public User getUserById(long id) {
      return entityManager.find(User.class, id);

   }

   @Override
   @Transactional
   public void add(User user) {
      entityManager.persist(user);
      entityManager.flush();
   }


   @Override
   public List<User> listUsers() {
      Query query = entityManager.createQuery("select user from User user");
      return query.getResultList();
   }

   @Override
   @Transactional
   public void removedUserById(long id) {
      entityManager.remove(entityManager.find(User.class, id));

   }

   @Override
   @Transactional
   public void changeUser(User user) {
      entityManager.merge(user);
   }
}
