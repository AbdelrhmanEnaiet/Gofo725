import java.util.Comparator;
import java.util.Date;

public class Booking {
    Date startTime;
    int playersCount;
    int forPlayground; // id of the playground that accepted the booking
    String bookingCreator; // name of the player who created the booking
    int bookingID;

    public Booking(Date startTime, int playersCount, int forPlayground, String bookingCreator, int bookingID) {
        this.startTime = startTime;
        this.playersCount = playersCount;
        this.forPlayground = forPlayground;
        this.bookingCreator = bookingCreator;
        this.bookingID = bookingID;
    }

    public void print() {
        System.out.println("The booking starts at" + startTime);
        System.out.println("Player " + bookingCreator + " has booked the playground " + forPlayground + " for " +
                playersCount + " people.");
    }

    public void displayDate() {
        System.out.print(startTime.toString());
    }

    Date getStartTime() {
        return startTime;
    }

    int getPlayerCount() {
        return playersCount;
    }

    int getPlaygroundID() {
        return forPlayground;
    }

    String getbookingCreator() {
        return bookingCreator;
    }

    int getBookingID() {
        return bookingID;
    }


}

class sortByDate implements Comparator<Booking> {
    public int compare(Booking a, Booking b) {
        return a.getStartTime().compareTo(b.getStartTime());
    }
}

class sortBookingByID implements Comparator<Booking> {
    public int compare(Booking a, Booking b) {
        return a.getBookingID() - b.getBookingID();
    }
}
