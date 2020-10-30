package ca.mcgill.esce321.artgallery.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.artgallery.dao.TransactionRepository;
import ca.mcgill.ecse321.artgallery.model.Transaction;
import ca.mcgill.ecse321.artgallery.services.TransactionService;

@ExtendWith(MockitoExtension.class)
public class TestTransactionService {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionService transactionService;

    private static final int TRANSACTION_KEY = 9999;
    private static final String NONEXISTING_KEY = "NotATransaction";

    @BeforeEach
    public void setMockOutput() {
        lenient().when(transactionRepository.findTransactionById(any())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(TRANSACTION_KEY)) {
                Transaction transaction = new Transaction();
                transaction.setId(TRANSACTION_KEY);
                return transaction;
            } else {
                return null;
            }
        });
        // Whenever anything is saved, just return the parameter object
        Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };
        lenient().when(transactionRepository.save(any(Transaction.class))).thenAnswer(returnParameterAsAnswer);
    }

    // test public boolean saveTransaction(int customerId, int artistId, int
    // artworkId, int artGalleryId)

    // test public boolean updateTransaction(Transaction transaction)

    // test public boolean removeTransaction(int transactionId)

    // test public Transaction getTransactionById(int id)

    // TODO ADD MORE TESTS FOR TRANSACTION IF NEEDED

}
