import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Picture{
   private int id;

public void setId(int value) {
    this.id = value;
}
@Id
public int getId() {
    return this.id;
}
   private Artwork artwork;
   
   @ManyToOne(optional=false)
   public Artwork getArtwork() {
      return this.artwork;
   }
   
   public void setArtwork(Artwork artwork) {
      this.artwork = artwork;
   }
   
   }
