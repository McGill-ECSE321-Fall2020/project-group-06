import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public class Customer extends Role{
   private Set<Transaction> transaction;
   
   @OneToMany(mappedBy="customer" )
   public Set<Transaction> getTransaction() {
      return this.transaction;
   }
   
   public void setTransaction(Set<Transaction> transactions) {
      this.transaction = transactions;
   }
   
   private long creditCardNumber;

public void setCreditCardNumber(long value) {
    this.creditCardNumber = value;
}
public long getCreditCardNumber() {
    return this.creditCardNumber;
}
   private Set<Artwork> artwork;
   
   @OneToMany
   public Set<Artwork> getArtwork() {
      return this.artwork;
   }
   
   public void setArtwork(Set<Artwork> artworks) {
      this.artwork = artworks;
   }
   
   }
