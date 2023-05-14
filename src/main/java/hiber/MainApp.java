package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      Car car1 = new Car("Ford", 11);
      Car car2 = new Car("toyota", 12);
      Car car3 = new Car("mercedes", 13);
      Car car4 = new Car("bmw", 14);

      User user1 = new User("User1", "user1", "test1@mail.ru");
      User user2 = new User("user2", "user2", "test2@mail.ru");
      User user3 = new User("user3", "user3", "test3@mail.ru");
      User user4 = new User("user4", "user4", "test4@mail.ru");

      user1.setCarUs(car1);
      user2.setCarUs(car2);
      user3.setCarUs(car3);
      user4.setCarUs(car4);
      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println("Model = " + user.getCarUs().getModel());
         System.out.println("Series = " + user.getCarUs().getSeries());
         System.out.println();
      }

      CarService carService = context.getBean(CarService.class);
      User lc = carService.getUserByCar("bmw", 14);
      System.out.println(lc);
      context.close();
   }
}