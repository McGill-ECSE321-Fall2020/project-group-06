package ca.mcgill.ecse321.artgallery.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Picture {
   private int id;

   public void setId(int value) {
      this.id = value;
   }

   @Id
   public int getId() {
      return this.id;
   }

   private Set<Artwork> favorites;

   @ManyToMany(mappedBy = "picture")
   public Set<Artwork> getFavorites() {
      return this.favorites;
   }

   public void setFavorites(Set<Artwork> favoritess) {
      this.favorites = favoritess;
   }

   private User user;

   @OneToOne(mappedBy = "picture", optional = true)
   public User getUser() {
      return this.user;
   }

   public void setUser(User user) {
      this.user = user;
   }

}
