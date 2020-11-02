package ca.mcgill.ecse321.artgallery.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Picture {

   private String url;

   public void setUrl(String url) {
      this.url = url;
   }

   public String getUrl() {
      return this.url;
   }

   private int id;

   public void setId(int value) {
      this.id = value;
   }

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
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

   // Reverted optional to true due to data integrity error
   @OneToOne(mappedBy = "picture", optional = true)
   public User getUser() {
      return this.user;
   }

   public void setUser(User user) {
      this.user = user;
   }
   @Override
	public boolean equals(Object obj) {
		if (!obj.getClass().getSimpleName().equalsIgnoreCase("Picture")) {
			return false;
		}
		Picture picture=(Picture)(obj);
		if(picture.getFavorites()==null&&this.getFavorites()==null) {
			
		}
		else if((picture.getFavorites()==null&&this.getFavorites()!=null)||
				(picture.getFavorites()!=null&&this.getFavorites()==null)||
				picture.getFavorites().size()!=this.getFavorites().size()) {
			return false;
		}
		if(picture.getId()!=this.getId()) {
			return false;
		}
		if(picture.getUrl().equalsIgnoreCase(this.getUrl())) {
			return false;
		}
		if(picture.getUser().getId()!=this.getUser().getId()) {
			return false;
		}
		return true;
	}

}
