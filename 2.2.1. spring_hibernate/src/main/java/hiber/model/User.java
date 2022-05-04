package hiber.model;

import javax.persistence.*;


@Entity
@Table(name = "user")
public class User {

//   @OneToOne
//   @JoinColumn(name = "fk_ad_id", referencedColumnName = "ad_id")
//   private Car car;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "name")
   private String firstName;

   @Column(name = "last_name")
   private String lastName;

   @Column(name = "email")
   private String email;

   @OneToOne(cascade = CascadeType.ALL) // каскад надо, чтобы поддерживалась связь
   @JoinColumn(name = "car_id", referencedColumnName = "id") // столбец с id авто
   private Car car;

   public User() {}

   public User(String firstName, String lastName, String email) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
   }

   public User(String firstName, String lastName, String email, Car car) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
      this.car = car;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public Car getCar() {      return car;   }

   @Override
   public String toString() {
      return "User: " + firstName + ", car: " + car.getModel() + ", series: " + car.getSeries();
   }

}
