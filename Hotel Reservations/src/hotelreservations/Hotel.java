package hotelreservations;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
  private static List<Reservation> hotelReservation = new ArrayList<Reservation>();
  private static final int maxRooms = 50;
  
  /**
   * 
   * @param person.
   * @return.
   */
  public int reserveRoom(String person) {
    int roomNumber = Reservation.getCurrentIndex();
    try {
      while (hotelReservation.get(roomNumber).getRoomNumber() != -1 
          && roomNumber < hotelReservation.size()) {
        
        roomNumber = Reservation.getCurrentIndex();
      }
      if (roomNumber >= hotelReservation.size()) {
        if (roomNumber >= maxRooms) {
          return -1;
        }
        roomNumber = Reservation.getCurrentIndex();
      }
      hotelReservation.set(roomNumber, new Reservation(person, roomNumber));
      
    } catch (Exception e) {
      hotelReservation.add(new Reservation(person, roomNumber));
    }
    
    return roomNumber;
  }
  
  /**
   * 
   * @param person.
   * @param roomNumber.
   * @return.
   */
  public boolean reserveRoom(String person, int roomNumber) {
    
    if (roomNumber >= maxRooms) {
      return false;
    }
    
    try {
      if (hotelReservation.get(roomNumber).getRoomNumber() == -1) {
        hotelReservation.add(roomNumber, new Reservation(person, roomNumber));
        return true;
      }
    } catch (Exception e) {
      insertNull(roomNumber);
      
      hotelReservation.add(roomNumber, new Reservation(person, roomNumber));
      return true;
    }
    return false;
  }
  
  /**
   * 
   * @param roomNumber.
   */
  public void insertNull(int roomNumber) {
    for (int it = 0; it < roomNumber; it++) {
      try {
        hotelReservation.get(it);
      } catch (Exception e) {
        hotelReservation.add(it, new Reservation("", -1));
      }
    }
  }
  
  /**
   * 
   * @param person.
   */
  public void cancelReservations(String person) {

    for (Reservation reservation : hotelReservation) {
      if (reservation.getCustomerName().equals(person)) {
        hotelReservation.set(hotelReservation.indexOf(reservation), new Reservation("", -1));
      }
    }
  }
  
  /**
   * ...
   */
  public void printReservations() {
    int count = 0;
    for (Reservation reservation : hotelReservation) {
      
      if (!(reservation.getRoomNumber() == -1)) {
        count++;
        System.out.printf("\tcustomer Name: %s\n\tRoom Number  : %s\n\tindex num: %s\n", 
            reservation.getCustomerName(), reservation.getRoomNumber(), 
            hotelReservation.indexOf(reservation));
      }
      
    }
    System.out.println("Total Reserved rooms( out of max rooms " + maxRooms + " ): " 
        + (maxRooms - (maxRooms - count)));
    System.out.println("Total free rooms( out of max rooms " + maxRooms + " ): " 
        + (maxRooms - count));
  }
  
}
