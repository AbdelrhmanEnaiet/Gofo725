import java.util.Comparator;
import java.util.Date;

public class Booking {
    Date startTime;
    int playersCount;
    int forPlayground; // id of the playground that accepted the booking
    String bookingCreator; // name of the player who created the booking
    int bookingID;

    /**
     * Inputs and book a playground at a certain time for a player
     * @param startTime The hour to be booked
     * @param playersCount The number of players will play
     * @param forPlayground The playground to be booked
     * @param bookingCreator The player's username
     * @param bookingID The id of the booking
     */
    public Booking(Date startTime, int playersCount, int forPlayground, String bookingCreator, int bookingID) {
        this.startTime = startTime;
        this.playersCount = playersCount;
        this.forPlayground = forPlayground;
        this.bookingCreator = bookingCreator;
        this.bookingID = bookingID;
    }
    
    /**
     * Display the current booking information
     */
    public void print() {
        System.out.println("The booking starts at" + startTime);
        System.out.println("Player " + bookingCreator + " has booked the playground " + forPlayground + " for " +
                playersCount + " people.");
    }
    
    /**
     * Display the date of the booking
     */
    public void displayDate() {
        System.out.print(startTime.toString());
    }
/**
 * Return the start time of the booking
 * @return The date of the booking
 */
    Date getStartTime() {
        return startTime;
    }
    /**
     * 
     * @return The number of players playing
     */
    int getPlayerCount() {
        return playersCount;
    }
    
    /**
     * 
     * @return The playground that is booked
     */
    int getPlaygroundID() {
        return forPlayground;
    }
    
    /**
     * 
     * @return The name of the player who booked the playground
     */
    String getbookingCreator() {
        return bookingCreator;
    }
    
    /**
     * 
     * @return Returns the booking id
     */
    int getBookingID() {
        return bookingID;
    }


}

/**
 * Compare between Bookings by their starting time
 * @author Mohamed
 *
 */
class sortByDate implements Comparator<Booking> {
    public int compare(Booking a, Booking b) {
        return a.getStartTime().compareTo(b.getStartTime());
    }
}
/**
 * Sort the bookings through their IDs
 * @author Mohamed
 *
 */
class sortBookingByID implements Comparator<Booking> {
    public int compare(Booking a, Booking b) {
        return a.getBookingID() - b.getBookingID();
    }
}
