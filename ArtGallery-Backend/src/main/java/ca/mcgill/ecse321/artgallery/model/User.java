package ca.mcgill.ecse321.artgallery.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
// @Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@Table(name = "users")
public class User {
   private String firstName;

   public void setFirstName(String value) {
      this.firstName = value;
   }

   public String getFirstName() {
      return this.firstName;
   }

   private String lastName;

   public void setLastName(String value) {
      this.lastName = value;
   }

   public String getLastName() {
      return this.lastName;
   }

   @Column(nullable = false)
   private String username;

   public void setUsername(String value) {
      this.username = value;
   }

   @Column(nullable = false)
   public String getUsername() {
      return this.username;
   }

   @Column(nullable = false)
   private String password;

   public void setPassword(String password) {
      this.password = password;
   }

   @Column(nullable = false)
   public String getPassword() {
      return this.password;
   }

   private String email;

   public void setEmail(String value) {
      this.email = value;
   }

   public String getEmail() {
      return this.email;
   }

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private int userId;

   public void setId(int value) {
      this.userId = value;
   }

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   public int getId() {
      return this.userId;
   }

   private String description;

   public void setDescription(String value) {
      this.description = value;
   }

   public String getDescription() {
      return this.description;
   }

   private Picture picture;

   @OneToOne
   public Picture getPicture() {
      return this.picture;
   }

   public void setPicture(Picture picture) {
      this.picture = picture;
   }

   private String phoneNumber;

   public void setPhoneNumber(String value) {
      this.phoneNumber = value;
   }

   public String getPhoneNumber() {
      return this.phoneNumber;
   }

}
