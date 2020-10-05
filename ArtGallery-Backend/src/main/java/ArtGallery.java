import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public class ArtGallery{
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
public int getId() {
    return this.id;
}
   private Set<Transaction> transaction;
   
   @OneToMany(mappedBy="artGallery" )
   public Set<Transaction> getTransaction() {
      return this.transaction;
   }
   
   public void setTransaction(Set<Transaction> transactions) {
      this.transaction = transactions;
   }
   
   }
