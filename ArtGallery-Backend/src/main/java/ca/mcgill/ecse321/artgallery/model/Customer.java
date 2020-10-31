package ca.mcgill.ecse321.artgallery.model;
import javax.persistence.Entity;
import javax.persistence.Id;

import java.util.Set;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Customer extends User{

private Set<Transaction> transaction;
   
   @OneToMany(mappedBy="customer")
	@JsonIgnoreProperties({"artwork", "artist", "customer", "artGallery"})
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
   @JsonIgnoreProperties("artwork")
   public Set<Artwork> getArtwork() {
      return this.artwork;
   }
   
   public void setArtwork(Set<Artwork> artworks) {
      this.artwork = artworks;
   }
   @Override
	public boolean equals(Object obj) {
	   if (!obj.getClass().getSimpleName().equalsIgnoreCase("Customer")) {
			return false;
		}
	   Customer customer=(Customer)obj;
	   if(customer.getArtwork()==null&&this.getArtwork()==null) {
		   
	   }
	   else if((customer.getArtwork()==null&&this.getArtwork()!=null)||
			   (customer.getArtwork()!=null&&this.getArtwork()==null)||
			   customer.getArtwork().size()!=(this.getArtwork().size())) {
		   return false;
	   }
	   if(!customer.getDescription().equalsIgnoreCase(this.getDescription())) {
		   return false;
	   }
	   if(!customer.getEmail().equalsIgnoreCase(this.getEmail())) {
		   return false;
	   }
	   if(!customer.getFirstName().equalsIgnoreCase(this.getFirstName())) {
		   return false;
	   }
	   if(customer.getId()!=this.getId()) {
		   return false;
	   }
	   if(!customer.getLastName().equalsIgnoreCase(this.getLastName())) {
		   return false;
	   }
	   if(!customer.getPassword().equalsIgnoreCase(this.getPassword())) {
		   return false;
	   }
	   if(!customer.getPhoneNumber().equalsIgnoreCase(this.getPhoneNumber())) {
		   return false;
	   }
	   if(customer.getPicture().getId()!=customer.getPicture().getId()) {
		   return false;
	   }
	   if(customer.getTransaction()==null&&this.getTransaction()==null) {
		   
	   }
	   else if((customer.getTransaction()==null&&this.getTransaction()!=null)||
			   (customer.getTransaction()!=null&&this.getTransaction()==null)||
			   customer.getTransaction().size()!=(this.getTransaction().size())) {
		   return false;
	   }
	   if(!customer.getUsername().equalsIgnoreCase(this.getUsername())) {
		   return false;
	   }
	   if(customer.getCreditCardNumber()!=this.getCreditCardNumber()) {
		   return false;
	   }
	   return true;
	}
   
   }
