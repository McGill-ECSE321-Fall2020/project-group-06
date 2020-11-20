package ca.mcgill.ecse321.artgallery.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Artist extends User {

	private Set<Artwork> artwork;

	@OneToMany(mappedBy = "artist")
	@JsonIgnoreProperties("artist")
	public Set<Artwork> getArtwork() {
		return this.artwork;
	}

	public void setArtwork(Set<Artwork> artworks) {
		this.artwork = artworks;
	}

	private Set<Transaction> transaction;

	@OneToMany(mappedBy = "artist")
	@JsonIgnoreProperties({ "artwork", "artist", "customer", "artGallery" })
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

	@Override
	public boolean equals(Object obj) {
		if (!obj.getClass().getSimpleName().equalsIgnoreCase("Artist")) {
			return false;
		}
		Artist artist = (Artist) obj;
		if (artist.getArtwork() == null && this.getArtwork() == null) {

		} else if ((artist.getArtwork() == null && this.getArtwork() != null)
				|| (artist.getArtwork() != null && this.getArtwork() == null)
				|| artist.getArtwork().size() != (this.getArtwork().size())) {
			return false;
		}
		if (!artist.getBankAccountNumber().equalsIgnoreCase(this.getBankAccountNumber())) {
			return false;
		}
		if (!artist.getDescription().equalsIgnoreCase(this.getDescription())) {
			return false;
		}
		if (!artist.getEmail().equalsIgnoreCase(this.getEmail())) {
			return false;
		}
		if (!artist.getFirstName().equalsIgnoreCase(this.getFirstName())) {
			return false;
		}
		if (artist.getId() != this.getId()) {
			return false;
		}
		if (!artist.getLastName().equalsIgnoreCase(this.getLastName())) {
			return false;
		}
		if (!artist.getPassword().equalsIgnoreCase(this.getPassword())) {
			return false;
		}
		if (!artist.getPhoneNumber().equalsIgnoreCase(this.getPhoneNumber())) {
			return false;
		}
		if (artist.getPicture().getId() != artist.getPicture().getId()) {
			return false;
		}
		if (artist.getTransaction() == null && this.getTransaction() == null) {

		} else if ((artist.getTransaction() == null && this.getTransaction() != null)
				|| (artist.getTransaction() != null && this.getTransaction() == null)
				|| artist.getTransaction().size() != (this.getTransaction().size())) {
			return false;
		}
		if (!artist.getUsername().equalsIgnoreCase(this.getUsername())) {
			return false;
		}
		return true;
	}

}
