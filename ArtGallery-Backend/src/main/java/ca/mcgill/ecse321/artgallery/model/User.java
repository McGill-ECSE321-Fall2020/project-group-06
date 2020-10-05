package ca.mcgill.ecse321.artgallery.model;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class User{
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
   
   private String username;
   
   public void setUsername(String value) {
      this.username = value;
   }
   
   public String getUsername() {
      return this.username;
   }
   
   private String email;
   
   public void setEmail(String value) {
      this.email = value;
   }
   
   public String getEmail() {
      return this.email;
   }
   
   private int id;

public void setId(int value) {
    this.id = value;
}
@Id
public int getId() {
    return this.id;
}
   private String description;
   
   public void setDescription(String value) {
      this.description = value;
   }
   
   public String getDescription() {
      return this.description;
   }
   
   private Set<Role> role;
   
   @OneToMany(mappedBy="user" )
   public Set<Role> getRole() {
      return this.role;
   }
   
   public void setRole(Set<Role> roles) {
      this.role = roles;
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
