package hotelreservations;

public class Reservation {
  private String customerName;
  private long roomNumber;
  private static int currentIndex;
  
  public Reservation(String customerName, long roomNumber) {
    this.customerName = customerName;
    this.roomNumber = roomNumber;
  }
  
  public String getCustomerName() {
    return customerName;
  }
  
  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }
  
  public long getRoomNumber() {
    return roomNumber;
  }
  
  public void setRoomNumber(long roomNumber) {
    this.roomNumber = roomNumber;
  }
  
  public static int getCurrentIndex() {
    return currentIndex++;
  }
}
