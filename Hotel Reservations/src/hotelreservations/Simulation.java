package hotelreservations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Simulation {
  /**
   * 
   * @param args.
   */
  public static void main(String[] args) {
    BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
    Hotel hotel = new Hotel();
    int key = 0;
    //List<String> name = new ArrayList<String>();
    do {
      System.out.println("\nWelcome to Hotel Reservations.\n");
      System.out.println("\t1) Reserve a Room.\n\t2) Reserve a Specific Room.");
      System.out.println("\t3) Cancel Reservation.\n\t4) print All Reservations.");
      
      try {
        key = Integer.parseInt(keyboard.readLine());
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      switch (key) {
        case 1:
          System.out.println("Reserve a room.\n");
          try {
            System.out.print("Enter customer name: ");
            String person = keyboard.readLine().toString();
            int roomNumber = hotel.reserveRoom(person);
            System.out.println("Room Number: " + roomNumber + " is reserved for :" + person);
          } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
          break;
        case 2:
          System.out.println("Reserve a specific room.\n");
          try {
            System.out.print("Enter customer name: ");
            String person = keyboard.readLine().toString();
            System.out.print("\nEnter the room Number: ");
            int roomNumber = Integer.parseInt(keyboard.readLine());
            boolean result = hotel.reserveRoom(person, roomNumber);
            System.out.println("Room reservation done: " + result);
          } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
          break;
        case 3:
          System.out.println("Cancel reservation.\n");
          try {
            System.out.print("Enter customer name: ");
            String person = keyboard.readLine().toString();
            hotel.cancelReservations(person);
          } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
          break;
        case 4:
          System.out.println("print all reservation.\n");
          hotel.printReservations();
          break;
        default:
          System.out.println("Bye, Thank you.\n");
          key = -1;
          break;
      }
    } while (key != -1);
  }
}
