package ca.mcgill.ecse321.artgallery.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;

import java.util.Set;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Artist extends User{

private Set<Artwork> artwork;
   
   @OneToMany(mappedBy="artist")
   @JsonIgnoreProperties("artist")
   public Set<Artwork> getArtwork() {
      return this.artwork;
   }
   
   public void setArtwork(Set<Artwork> artworks) {
      this.artwork = artworks;
   }
   
   private Set<Transaction> transaction;
   
   @OneToMany(mappedBy="artist")
	@JsonIgnoreProperties({"artwork", "artist", "customer", "artGallery"})
   public Set<Transaction> getTransaction() {
      return this.transaction;
   }
   
   public void setTransaction(Set<Transaction> transactions) {
      this.transaction = transactions;
   }
   
   private String bankAccountNumber;
   
   public void setBankAccountNumber(String value) {
      this.bankAccountNumber = value;
   }
   
   public String getBankAccountNumber() {
      return this.bankAccountNumber;
   }
   
   }
