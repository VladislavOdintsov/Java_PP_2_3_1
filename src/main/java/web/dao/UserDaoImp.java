package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao{


    @PersistenceContext
    private EntityManager entityManager;

//    @Autowired
//    public UserDaoImp(EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }

//    List<User> users;
//    {
//        users = new ArrayList<>();
//        users.add(new User("Dima", "Diman", "dima@"));
//        users.add(new User("Egor", "Egorov", "egor@"));
//    }
    @Override
    public List<User> allUsers() {
//        return users;
        return entityManager.createQuery("select user from User user", User.class).getResultList();
    }

    @Override
    public void addUser(User user) {
//        users.add(user);
        entityManager.persist(user);
    }

    @Override
    public void changeUser(long id, User updatedUser) {
        User userToUpdate = getUserById(id);
        userToUpdate.setFirstName(updatedUser.getFirstName());
        userToUpdate.setLastName(updatedUser.getLastName());
        userToUpdate.setEmail(updatedUser.getEmail());
        entityManager.merge(userToUpdate);
    }

    @Override
    public void deleteUser(long id) {
        entityManager.remove(getUserById(id));
    }

    @Override
    public User getUserById(long id) {
        return entityManager.find(User.class, id);
    }
}
