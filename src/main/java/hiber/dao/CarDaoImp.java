package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class CarDaoImp implements CarDao {
    private final SessionFactory sessionFactory;

    public CarDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Car car) {
        sessionFactory.getCurrentSession().save(car);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Car> listCars() {
        TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery("from Car");
        return query.getResultList();
    }

//    @Override
//    public Optional<User> getUserByCar(String model, int series) {//left outer join fetch User
//        String hql = "From Car c left outer join fetch c.user where c.model = :model and c.series = :series";
//        Car query = sessionFactory.getCurrentSession()
//                .createQuery(hql, Car.class)
//                .setParameter("model", model)
//                .setParameter("series", series)
//                .uniqueResult();
//        return query.getUser();//"").setParameter("model",2).getResult();
//    }

    @Override
    public Optional<User> getUserByCar(String model, int series) {
        String hql = "from User user where user.cars.model = :model and user.cars.series = :series";
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("model", model).setParameter("series", series);
        return query.getResultStream().findAny();
    }
}
