import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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

@ManyToMany
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

private Set<Transaction> transaction;

@OneToMany(mappedBy="artwork" )
public Set<Transaction> getTransaction() {
   return this.transaction;
}

public void setTransaction(Set<Transaction> transactions) {
   this.transaction = transactions;
}

private ArtGallery artGallery;

@ManyToOne(optional=false)
public ArtGallery getArtGallery() {
   return this.artGallery;
}

public void setArtGallery(ArtGallery artGallery) {
   this.artGallery = artGallery;
}

private boolean isInStore;

public void setIsInStore(boolean value) {
   this.isInStore = value;
}

public boolean isIsInStore() {
   return this.isInStore;
}

private boolean forSale;

public void setForSale(boolean value) {
   this.forSale = value;
}

public boolean isForSale() {
   return this.forSale;
}

private TypeOfArtwork typeOfArtwork;

public void setTypeOfArtwork(TypeOfArtwork value) {
    this.typeOfArtwork = value;
}
public TypeOfArtwork getTypeOfArtwork() {
    return this.typeOfArtwork;
}
}
