package ca.mcgill.ecse321.artgallery.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Transaction {
	/**
	 * <pre>
	 *           1..1     1..1
	 * Transaction ------------------------> Date
	 *           &lt;       dateOfTransaction
	 * </pre>
	 */
	private Date dateOfTransaction;

	public enum DeliveryType {
		PickedUp, Delivered
	}

	public void setDateOfTransaction(Date value) {
		this.dateOfTransaction = value;
	}

	public Date getDateOfTransaction() {
		return this.dateOfTransaction;
	}

	private Customer customer;

	@ManyToOne(optional = false)
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	private Artist artist;

	@ManyToOne(optional = false)
	public Artist getArtist() {
		return this.artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	private Artwork artwork;

	@ManyToOne(optional = false)
	public Artwork getArtwork() {
		return this.artwork;
	}

	public void setArtwork(Artwork artwork) {
		this.artwork = artwork;
	}

	private ArtGallery artGallery;

	@ManyToOne(optional = false)
	public ArtGallery getArtGallery() {
		return this.artGallery;
	}

	public void setArtGallery(ArtGallery artGallery) {
		this.artGallery = artGallery;
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

	private double commisionCut;

	public void setCommisionCut(double value) {
		this.commisionCut = value;
	}

	public double getCommisionCut() {
		return this.commisionCut;
	}

	private DeliveryType deliveryType;

	public void setDeliveryType(DeliveryType value) {
		this.deliveryType = value;
	}

	public DeliveryType getDeliveryType() {
		return this.deliveryType;
	}

	@Override
	public boolean equals(Object obj) {
		if (!obj.getClass().getSimpleName().equalsIgnoreCase("Transaction")) {
			return false;
		}
		Transaction transaction = (Transaction) obj;
		if (transaction.getArtGallery().getId() != this.getArtGallery().getId()) {
			return false;
		}
		if(transaction.getArtist().getId()!= this.getArtist().getId()) {
			return false;
		}
		if(transaction.getArtwork().getId()!= this.getArtwork().getId()) {
			return false;
		}
		if(transaction.getCommisionCut()!=this.getCommisionCut()) {
			return false;
		}
		if(transaction.getCustomer().getId()!=this.getCustomer().getId()) {
			return false;
		}
		if(!transaction.getDateOfTransaction().equals(this.getDateOfTransaction())) {
			return false;
		}
		if(!transaction.getDeliveryType().toString().equalsIgnoreCase
				(this.getDeliveryType().toString())) {
			return false;
		}
		if(transaction.getId()!=this.getId()) {
			return false;
		}
		return true;
	}

}
