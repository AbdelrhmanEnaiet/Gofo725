import java.util.Date;

public class Request extends Booking {
    public Request(Date startTime, int playersCount, int forPlayground, int bookingCreator) {
        super(startTime, playersCount, forPlayground, bookingCreator);
    }
}
