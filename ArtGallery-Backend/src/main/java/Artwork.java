import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;

@Entity
public class Artwork{
   private String name;

public void setName(String value) {
    this.name = value;
}
public String getName() {
    return this.name;
}
private int id;

public void setId(int value) {
    this.id = value;
}
@Id
public int getId() {
    return this.id;
}
private double price;

public void setPrice(double value) {
    this.price = value;
}
public double getPrice() {
    return this.price;
}
private String description;

public void setDescription(String value) {
    this.description = value;
}
public String getDescription() {
    return this.description;
}
   private Set<Picture> picture;
   
   @OneToMany(mappedBy="artwork" )
   public Set<Picture> getPicture() {
      return this.picture;
   }
   
   public void setPicture(Set<Picture> pictures) {
      this.picture = pictures;
   }
   
   private Artist artist;
   
   @ManyToOne(optional=false)
   public Artist getArtist() {
      return this.artist;
   }
   
   public void setArtist(Artist artist) {
      this.artist = artist;
   }
   
   private Store store;
   
   @ManyToOne
   public Store getStore() {
      return this.store;
   }
   
   public void setStore(Store store) {
      this.store = store;
   }
   
   private Set<Transaction> transaction;
   
   @OneToMany(mappedBy="artwork" )
   public Set<Transaction> getTransaction() {
      return this.transaction;
   }
   
   public void setTransaction(Set<Transaction> transactions) {
      this.transaction = transactions;
   }
   
   }
