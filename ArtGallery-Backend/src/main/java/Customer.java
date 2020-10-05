import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public class Customer extends Role{
   private Set<Transaction> transaction;
   
   @OneToMany(mappedBy="customer" )
   public Set<Transaction> getTransaction() {
      return this.transaction;
   }
   
   public void setTransaction(Set<Transaction> transactions) {
      this.transaction = transactions;
   }
   
   }
