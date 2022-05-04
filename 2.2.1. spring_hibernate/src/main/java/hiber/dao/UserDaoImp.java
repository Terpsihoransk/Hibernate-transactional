package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getUserByCar(String model, int series) {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User where car.model = :model and car.series = :series");
      query.setParameter("model",model); // это пришлось списать, ниже мой неработающий вариант запросов
      query.setParameter("series",series);
      return query.setMaxResults(1).getSingleResult();
   }

}

/*    @Override
   public findUserByCar(){
      Query query = sessionFactory.getCurrentSession().createQuery("SELECT FROM User WHERE car=:car");
      query.setParameter("car", car);
      return (User) query.getSingleResult();
   }

 типизированный запрос (без приведения типов через TypedQuery
   public Car findUserByCar(Long id) {
    TypedQuery<UserEntity> typedQuery
      = getEntityManager().createQuery("SELECT FROM User WHERE model=:model", Car.class);
    typedQuery.setParameter("model", model);
    return typedQuery.getSingleResult();
}
 */



//   @Override
//   public void createCarTable(Car car) {
//      sessionFactory.getCurrentSession().save(car);
//   }

   // нужна аннотация @Transactional
//   @Override
//   @SuppressWarnings("unchecked")
//   public List<Car> listCar()() {
//      Query query = sessionFactory.getCurrentSession().createQuery("from Car a where a.id = :id");
//      query.setParameter("id", id);
//      return query.getResultList();
//   }

   // нужен метод, hql достаёт юзера с машиной по модели и серии
   // типизация. юзера ищем по Car. не executeUpdate , а getSingleResult()
   // использовать модель и серию, то в запросе ставьте and
   // обычный from и where. Кто-то через INNER JOIN (см.статью про hql)



