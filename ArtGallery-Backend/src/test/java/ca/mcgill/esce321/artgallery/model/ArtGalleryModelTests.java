package ca.mcgill.esce321.artgallery.model;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;

import org.junit.jupiter.api.Test;

import ca.mcgill.ecse321.artgallery.model.ArtGallery;
import ca.mcgill.ecse321.artgallery.model.Artist;
import ca.mcgill.ecse321.artgallery.model.Artwork;
import ca.mcgill.ecse321.artgallery.model.Artwork.TypeOfArtwork;
import ca.mcgill.ecse321.artgallery.model.Customer;
import ca.mcgill.ecse321.artgallery.model.Picture;
import ca.mcgill.ecse321.artgallery.model.Transaction;
import ca.mcgill.ecse321.artgallery.model.Transaction.DeliveryType;
public class ArtGalleryModelTests {

	@Test
	public void testArtGalleryEquals() {
		ArtGallery artGallery=new ArtGallery();
		artGallery.setId(5);
		artGallery.setAdress("AdressGallery");
		artGallery.setName("GalleryName");
		
		ArtGallery newArtGallery = new ArtGallery();
		newArtGallery.setAdress(artGallery.getAdress());
		newArtGallery.setName(artGallery.getName());
		newArtGallery.setId(artGallery.getId());
		assertEquals(newArtGallery,artGallery);
	}
	@Test
	public void testArtistEquals() {
		Artist artist=new Artist();
		artist.setBankAccountNumber("ArtistBank");
		artist.setDescription("ArtistDescr");
		artist.setEmail("ArtistEmail");
		artist.setFirstName("ArtistFN");
		artist.setLastName("ArtistLN");
		artist.setPassword("ArtistPass");
		artist.setPhoneNumber("ArtistPhone");
		artist.setUsername("ArtistUsername");
		artist.setId(10);
		Picture picture = new Picture();
		artist.setPicture(picture);
		
		Artist newArtist=new Artist();
		newArtist.setBankAccountNumber("ArtistBank");
		newArtist.setDescription("ArtistDescr");
		newArtist.setEmail("ArtistEmail");
		newArtist.setFirstName("ArtistFN");
		newArtist.setLastName("ArtistLN");
		newArtist.setPassword("ArtistPass");
		newArtist.setPhoneNumber("ArtistPhone");
		newArtist.setUsername("ArtistUsername");
		newArtist.setPicture(picture);
		newArtist.setId(10);
		assertEquals(artist,newArtist);
	}
	@Test
	public void testCustomerEquals() {
		Customer customer=new Customer();
		customer.setCreditCardNumber(10);
		customer.setDescription("customerDescr");
		customer.setEmail("customerEmail");
		customer.setFirstName("customerFN");
		customer.setLastName("customerLN");
		customer.setPassword("customerPass");
		customer.setPhoneNumber("customerPhone");
		customer.setUsername("customerUsername");
		customer.setId(15);
		Picture picture = new Picture();
		customer.setPicture(picture);
		
		Customer newCustomer=new Customer();
		newCustomer.setCreditCardNumber(10);
		newCustomer.setDescription("customerDescr");
		newCustomer.setEmail("customerEmail");
		newCustomer.setFirstName("customerFN");
		newCustomer.setLastName("customerLN");
		newCustomer.setPassword("customerPass");
		newCustomer.setPhoneNumber("customerPhone");
		newCustomer.setUsername("customerUsername");
		newCustomer.setId(15);
		newCustomer.setPicture(picture);
	}
	@Test
	public void testArtworkEquals() {
		Artwork artwork=new Artwork();
		ArtGallery artGallery=new ArtGallery();
		Artist artist=new Artist();
		artwork.setArtGallery(artGallery);
		artwork.setArtist(artist);
		artwork.setDescription("Artwork desc");
		artwork.setForSale(true);
		artwork.setId(20);
		artwork.setIsInStore(true);
		artwork.setName("ArtworkName");
		artwork.setPrice(100);
		artwork.setTypeOfArtwork(TypeOfArtwork.Painting);
		
		Artwork newArtwork=new Artwork();
		newArtwork.setArtGallery(artGallery);
		newArtwork.setArtist(artist);
		newArtwork.setDescription("Artwork desc");
		newArtwork.setForSale(true);
		newArtwork.setId(20);
		newArtwork.setIsInStore(true);
		newArtwork.setName("ArtworkName");
		newArtwork.setPrice(100);
		newArtwork.setTypeOfArtwork(TypeOfArtwork.Painting);
	}
	@Test
	public void testPictureEquals() {
		Picture picture = new Picture();
		Artist artist=new Artist();
		picture.setId(25);
		picture.setUrl("PictureURL");
		picture.setUser(artist);
		
		Picture newPicture=new Picture();
		newPicture.setId(25);
		newPicture.setUrl("PictureURL");
		newPicture.setUser(artist);
	}
	@Test
	public void testTransactionEquals() {
		Transaction transaction = new Transaction();
		ArtGallery artGallery=new ArtGallery();
		Artist artist=new Artist();
		Artwork artwork=new Artwork();
		Customer customer=new Customer();
		Date date=new Date(600000);
		transaction.setArtGallery(artGallery);
		transaction.setArtist(artist);
		transaction.setArtwork(artwork);
		transaction.setCommisionCut(0.50);
		transaction.setCustomer(customer);
		transaction.setDateOfTransaction(date);
		transaction.setDeliveryType(DeliveryType.PickedUp);
		transaction.setId(50000);
		
		Transaction newTransaction=new Transaction();
		newTransaction.setArtGallery(artGallery);
		newTransaction.setArtist(artist);
		newTransaction.setArtwork(artwork);
		newTransaction.setCommisionCut(0.50);
		newTransaction.setCustomer(customer);
		newTransaction.setDateOfTransaction(date);
		newTransaction.setDeliveryType(DeliveryType.PickedUp);
		newTransaction.setId(50000);
	}
}
