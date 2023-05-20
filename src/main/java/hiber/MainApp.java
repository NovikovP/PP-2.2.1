package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);



      User user1 = new User ("User1", "Lastname1", "user1@mail.ru");
      User user2 = new User ("User2", "Lastname2", "user2@mail.ru");
      User user3 = new User ("User3", "Lastname3", "user3@mail.ru");
      User user4 = new User ("User4", "Lastname4", "user4@mail.ru");

      Car car1 = new Car("BMW", 5);
      Car car2 = new Car("AUDI", 6);
      Car car3 = new Car("OPEL", 72);
      Car car4 = new Car("FORD", 2);

      userService.add(user1.setCars(car1).setUser(user1));
      userService.add(user2.setCars(car2).setUser(user2));
      userService.add(user3.setCars(car3).setUser(user3));
      userService.add(user4.setCars(car4).setUser(user4));


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());

         System.out.println();
      }

      System.out.println(userService.getUser("BMW", 5).toString());
      context.close();
   }
}
