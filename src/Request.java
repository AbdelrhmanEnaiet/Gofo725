import java.util.Comparator;
import java.util.Date;

public class Request {
    Date startTime;
    int playersCount;
    int forPlayground;
    String bookingCreator;
    int requestID;

    public Request(Date startTime, int playersCount, int forPlayground, String bookingCreator, int requestID) {
        this.startTime = startTime;
        this.playersCount = playersCount;
        this.forPlayground = forPlayground;
        this.bookingCreator = bookingCreator;
        this.requestID = requestID;
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

    int getRequestID() {
        return requestID;
    }

    void printRequest() {
        System.out.println("Request #" + requestID + " :The player " + bookingCreator +
                " request to book playground " + forPlayground +
                " for " + playersCount + " people at " + startTime.toString());
    }

}

class sortByID implements Comparator<Request> {
    public int compare(Request a, Request b) {

        return a.getRequestID() - b.getRequestID();
    }

}


