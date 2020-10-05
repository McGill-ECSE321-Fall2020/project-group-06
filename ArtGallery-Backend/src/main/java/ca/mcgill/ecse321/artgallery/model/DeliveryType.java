import javax.persistence.Entity;

@Entity
public enum DeliveryType{
   private DeliveryType pickedUp;

public void setPickedUp(DeliveryType value) {
    this.pickedUp = value;
}
public DeliveryType getPickedUp() {
    return this.pickedUp;
}
private DeliveryType deliveredToHouse;

public void setDeliveredToHouse(DeliveryType value) {
    this.deliveredToHouse = value;
}
public DeliveryType getDeliveredToHouse() {
    return this.deliveredToHouse;
}
}
