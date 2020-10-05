import javax.persistence.Entity;

@Entity
public enum TypeOfArtwork{
   private TypeOfArtwork sculpture;

public void setSculpture(TypeOfArtwork value) {
    this.sculpture = value;
}
public TypeOfArtwork getSculpture() {
    return this.sculpture;
}
private TypeOfArtwork painting;

public void setPainting(TypeOfArtwork value) {
    this.painting = value;
}
public TypeOfArtwork getPainting() {
    return this.painting;
}
private TypeOfArtwork picture;

public void setPicture(TypeOfArtwork value) {
    this.picture = value;
}
public TypeOfArtwork getPicture() {
    return this.picture;
}
private TypeOfArtwork other;

public void setOther(TypeOfArtwork value) {
    this.other = value;
}
public TypeOfArtwork getOther() {
    return this.other;
}
}
