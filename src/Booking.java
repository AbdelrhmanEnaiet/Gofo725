import java.util.Date;

public class Booking {
    Date startTime;
    int playersCount;
    int forPlayground; // id of the playground that accepted the booking
    int bookingCreator; // id of the player who created the booking

    public Booking(Date startTime, int playersCount, int forPlayground, int bookingCreator) {
        this.startTime = startTime;
        this.playersCount = playersCount;
        this.forPlayground = forPlayground;
        this.bookingCreator = bookingCreator;
    }

    public void print() {
        System.out.println("The booking starts at" + startTime);
        System.out.println("Player " + bookingCreator + " has booked the playground " + forPlayground + " for " +
                playersCount + " people.");
    }

}
