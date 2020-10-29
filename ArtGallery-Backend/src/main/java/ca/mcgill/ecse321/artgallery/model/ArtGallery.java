package ca.mcgill.ecse321.artgallery.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class ArtGallery {
   private String name;

   public void setName(String value) {
      this.name = value;
   }

   public String getName() {
      return this.name;
   }

   private String adress;

   public void setAdress(String value) {
      this.adress = value;
   }

   public String getAdress() {
      return this.adress;
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

   private Set<Transaction> transaction;

   @OneToMany(mappedBy = "artGallery")
   @JsonIgnoreProperties({"artwork", "artist", "customer", "artGallery"})
   public Set<Transaction> getTransaction() {
      return this.transaction;
   }

   public void setTransaction(Set<Transaction> transactions) {
      this.transaction = transactions;
   }

   private Set<Artwork> artwork;

   @OneToMany(mappedBy = "artGallery")
   public Set<Artwork> getArtwork() {
      return this.artwork;
   }

   public void setArtwork(Set<Artwork> artworks) {
      this.artwork = artworks;
   }

}
