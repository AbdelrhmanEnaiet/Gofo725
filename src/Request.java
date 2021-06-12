import java.util.Comparator;
import java.util.Date;

public class Request {
    Date startTime;
    int playersCount;
    int forPlayground;
    String bookingCreator;
    int requestID;

    /**
     * Create a new player request and set it's attributes
     * @param startTime The time of the booking
     * @param playersCount The number of the players going to play
     * @param forPlayground The playground to be booked
     * @param bookingCreator The player name
     * @param requestID The request id
     */
    public Request(Date startTime, int playersCount, int forPlayground, String bookingCreator, int requestID) {
        this.startTime = startTime;
        this.playersCount = playersCount;
        this.forPlayground = forPlayground;
        this.bookingCreator = bookingCreator;
        this.requestID = requestID;
    }

    /**
     * 
     * @return The booking date
     */
    Date getStartTime() {
        return startTime;
    }

    /**
     * 
     * @return The number of players are going to play
     */
    int getPlayerCount() {
        return playersCount;
    }

    /**
     * 
     * @return The requested playground for booking id
     */
    int getPlaygroundID() {
        return forPlayground;
    }

    /**
     * 
     * @return The booking request creator name
     */
    String getbookingCreator() {
        return bookingCreator;
    }

    /**
     * 
     * @return The request id
     */
    int getRequestID() {
        return requestID;
    }

    /**
     * Display the request information
     */
    void printRequest() {
        System.out.println("Request #" + requestID + " :The player " + bookingCreator +
                " request to book playground " + forPlayground +
                " for " + playersCount + " people at " + startTime.toString());
    }

}

/**
 * Compare two requests by their ids
 * @author Mohamed
 *
 */
class sortByID implements Comparator<Request> {
    public int compare(Request a, Request b) {

        return a.getRequestID() - b.getRequestID();
    }

}


