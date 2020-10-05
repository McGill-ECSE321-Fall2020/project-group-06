package ca.mcgill.ecse321.artgallery.model;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public abstract class Role{
   private User user;
   
   @ManyToOne(optional=false)
   public User getUser() {
      return this.user;
   }
   
   public void setUser(User user) {
      this.user = user;
   }
   
   }
