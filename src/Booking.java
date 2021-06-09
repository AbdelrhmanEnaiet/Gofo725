import java.util.Date;

public class Booking {
    Date startTime;
    int playersCount;
    int forPlayground; // id of the playground that accepted the booking
    int bookingCreator; // name of the player who created the booking
    int bookingID;

    public Booking(Date startTime, int playersCount, int forPlayground, int bookingCreator, int bookingID) {
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

    Date getStartTime() {
        return startTime;
    }

    int getPlayerCount() {
        return playersCount;
    }

    int getPlaygroundID() {
        return forPlayground;
    }

    int getbookingCreator() {
        return bookingCreator;
    }

    int getBookingID() {
        return bookingID;
    }

}
