package ca.mcgill.ecse321.artgallery.dto;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;

import ca.mcgill.ecse321.artgallery.dao.ArtGalleryRepository;
import ca.mcgill.ecse321.artgallery.dao.ArtistRepository;
import ca.mcgill.ecse321.artgallery.dao.TransactionRepository;
import ca.mcgill.ecse321.artgallery.model.Transaction;
import ca.mcgill.ecse321.artgallery.model.Transaction.DeliveryType;
import ca.mcgill.ecse321.artgallery.services.TransactionService;

public class TransactionDto {
	private Date dateOfTransaction;
	private int customerId;
	private int artistId;
	private int artworkId;
	private int artGalleryId;
	private int id;
	private double commisionCut;
	private DeliveryType deliveryType;
	public Date getDateOfTransaction() {
		return dateOfTransaction;
	}
	public void setDateOfTransaction(Date dateOfTransaction) {
		this.dateOfTransaction = dateOfTransaction;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getArtistId() {
		return artistId;
	}
	public void setArtistId(int artistId) {
		this.artistId = artistId;
	}
	public int getArtworkId() {
		return artworkId;
	}
	public void setArtworkId(int artworkId) {
		this.artworkId = artworkId;
	}
	public int getArtGalleryId() {
		return artGalleryId;
	}
	public void setArtGalleryId(int artGalleryId) {
		this.artGalleryId = artGalleryId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getCommisionCut() {
		return commisionCut;
	}
	public void setCommisionCut(double commisionCut) {
		this.commisionCut = commisionCut;
	}
	public DeliveryType getDeliveryType() {
		return deliveryType;
	}
	public void setDeliveryType(DeliveryType deliveryType) {
		this.deliveryType = deliveryType;
	}
	public static TransactionDto convertToDTO(Transaction transaction) {
		TransactionDto transactionDTO= new TransactionDto();
		transactionDTO.setArtGalleryId(transaction.getArtGallery().getId());
		transactionDTO.setArtistId(transaction.getArtist().getId());
		transactionDTO.setArtworkId(transaction.getArtwork().getId());
		transactionDTO.setCommisionCut(transaction.getCommisionCut());
		transactionDTO.setCustomerId(transaction.getCustomer().getId());
		transactionDTO.setDateOfTransaction(transaction.getDateOfTransaction());
		transactionDTO.setDeliveryType(transaction.getDeliveryType());
		transactionDTO.setArtGalleryId(transaction.getId());
		return transactionDTO;
	}
	public static Transaction convertToDomainObject(TransactionDto transactionDto) {
		TransactionService transactionService = new TransactionService();
		Transaction transaction=transactionService.getTransactionById(transactionDto.getId());
		return transaction;
	}
}
