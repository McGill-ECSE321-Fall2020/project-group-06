package ca.mcgill.ecse321.artgallery.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Artwork {
	private String name;

	public enum TypeOfArtwork {
		Sculpture, Painting, Photography, Other
	}

	public void setName(String value) {
		this.name = value;
	}

	private String url;

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return this.url;
	}

	public String getName() {
		return this.name;
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

	// ManyToOne
	@OneToOne(optional = false)
	@JsonIgnoreProperties("artwork")
	public Artist getArtist() {
		return this.artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	private Set<Transaction> transaction;

	@OneToMany(mappedBy = "artwork")
	@JsonIgnoreProperties({ "artwork", "artist", "customer", "artGallery" })
	public Set<Transaction> getTransaction() {
		return this.transaction;
	}

	public void setTransaction(Set<Transaction> transactions) {
		this.transaction = transactions;
	}

	private ArtGallery artGallery;

	// ManytoOne
	@OneToOne(optional = true)
	@JsonIgnoreProperties("artwork")
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

	@Override
	public boolean equals(Object obj) {
		if (!obj.getClass().getSimpleName().equalsIgnoreCase("Artwork")) {
			return false;
		}
		Artwork artwork = (Artwork) obj;
		if (artwork.getArtGallery().getId() != this.getId()) {
			return false;
		}
		if (artwork.getArtist().getId() != this.getId()) {
			return false;
		}
		if (!artwork.getDescription().equalsIgnoreCase(this.getDescription())) {
			return false;
		}
		if (artwork.getId() != this.getId()) {
			return false;
		}
		if (!artwork.getName().equalsIgnoreCase(this.getName())) {
			return false;
		}
		if (artwork.getPicture() == null && this.getPicture() == null) {

		} else if ((artwork.getPicture() == null && this.getPicture() != null)
				|| (artwork.getPicture() != null && this.getPicture() == null)
				|| artwork.getPicture().size() != (this.getPicture().size())) {
			return false;
		}
		if (artwork.getPrice() != this.getPrice()) {
			return false;
		}
		if (artwork.getTransaction() == null && this.getTransaction() == null) {

		} else if ((artwork.getTransaction() == null && this.getTransaction() != null)
				|| (artwork.getTransaction() != null && this.getTransaction() == null)
				|| artwork.getTransaction().size() != this.getTransaction().size()) {
			return false;
		}
		if (!artwork.getTypeOfArtwork().toString().equalsIgnoreCase(this.getTypeOfArtwork().toString())) {
			return false;
		}
		return true;
	}
}
