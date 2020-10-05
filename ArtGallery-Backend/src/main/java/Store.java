import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.Id;

@Entity
public class Store{
   private Set<Artwork> artwork;
   
   @OneToMany(mappedBy="store" )
   public Set<Artwork> getArtwork() {
      return this.artwork;
   }
   
   public void setArtwork(Set<Artwork> artworks) {
      this.artwork = artworks;
   }
   
   private String name;

public void setName(String value) {
    this.name = value;
}
public String getName() {
    return this.name;
}
private int id;

public void setId(int value) {
    this.id = value;
}
@Id
public int getId() {
    return this.id;
}
}
