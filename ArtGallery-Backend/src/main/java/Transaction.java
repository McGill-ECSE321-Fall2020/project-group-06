import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Transaction{
   private int price;

public void setPrice(int value) {
    this.price = value;
}
public int getPrice() {
    return this.price;
}
private String dateOfTransaction;

public void setDateOfTransaction(String value) {
    this.dateOfTransaction = value;
}
public String getDateOfTransaction() {
    return this.dateOfTransaction;
}
private boolean isBeingDelivered;

public void setIsBeingDelivered(boolean value) {
    this.isBeingDelivered = value;
}
public boolean isIsBeingDelivered() {
    return this.isBeingDelivered;
}
   private Customer customer;
   
   @ManyToOne(optional=false)
   public Customer getCustomer() {
      return this.customer;
   }
   
   public void setCustomer(Customer customer) {
      this.customer = customer;
   }
   
   private Artist artist;
   
   @ManyToOne(optional=false)
   public Artist getArtist() {
      return this.artist;
   }
   
   public void setArtist(Artist artist) {
      this.artist = artist;
   }
   
   private Artwork artwork;
   
   @ManyToOne(optional=false)
   public Artwork getArtwork() {
      return this.artwork;
   }
   
   public void setArtwork(Artwork artwork) {
      this.artwork = artwork;
   }
   
   private ArtGallery artGallery;
   
   @ManyToOne(optional=false)
   public ArtGallery getArtGallery() {
      return this.artGallery;
   }
   
   public void setArtGallery(ArtGallery artGallery) {
      this.artGallery = artGallery;
   }
   
   }
