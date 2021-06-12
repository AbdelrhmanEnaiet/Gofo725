import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Main {
    public static Calendar calendar = Calendar.getInstance();
    public static Scanner scanner = new Scanner(System.in);
    public static Menu menu = new Menu();
    public static UserHandler userHandler = new UserHandler();
    public static User currentUser;
    public static NewPGsHandler newPGsHandler = new NewPGsHandler();
    static SimpleDateFormat dateFormater = new SimpleDateFormat("MMM/dd/yyyy/HH");
    public static PlaygroundHandler playgroundHandler = new PlaygroundHandler();
    public static RequestHandler requestHandler = new RequestHandler();

    public static void main(String[] args) {
        //testing calender
        //Calendar calendar = Calendar.getInstance();
        //calendar.setTime(new Date( 2021,5,12));
        //System.out.println("Today is " + calendar.get(Calendar.DAY_OF_WEEK));
        //################

        int option = 0;
        boolean loop = true;

        while (loop) {
            System.out.println("Welcome to Gofo!!");
            System.out.println("1- Register a new account");
            System.out.println("2- Login");
            System.out.println("3- Exit");
            option = 0;
            option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {

                case 1: {
                    if (addUser()) {// summon a boolean method (in the Main class) to add user and check if operation is success

                        System.out.println("Registered Successfully");
                        loop = false;// turn false to exit the loop if the registration is done

                    } else {//failed operation is due an existing user with the same email
                        System.out.println("Error User Exists");
                    }
                    break;
                }
                case 2: {
                    if (login()) {//boolean method (in the Main class) to handle the login process
                        System.out.println("Login is Success");
                        loop = false;
                    }
                    break;
                }
                case 3: {
                    System.exit(0);
                }
                default:
                    System.out.println("Choose a valid number");


            }
        }


        currentUser = userHandler.getCurrentUser(); //for Easy access

        //greeting the user and confirming his account type
        menu.printFramed("System Starting...");
        System.out.println("Hello " + currentUser.getUserName() + ", you Are a " + currentUser.getAccountType());
        System.out.println("What do you want to do?");

        while (true) {
            if (currentUser.getAccountType().equalsIgnoreCase("Owner")) {
                System.out.println("1- View My Playgrounds");
                System.out.println("2- View My Requests");
                System.out.println("3- View My Bookings");
                System.out.println("4- Exit");

                option = scanner.nextInt();

                switch (option) {
                    case 1: //View My Playgrounds
                    {
                        playgroundHandler.showPlaygroundsOfOwner(currentUser.getUserName());
                        System.out.println("Do you want to accept players' requests?");
                        System.out.println("0= NO  1=YES");
                        option=scanner.nextInt();
                        if (option==1)
                        {
                            System.out.println("Accept for playground NO. ....?");
                            int pID = scanner.nextInt();
                            requestHandler.showRequestsByPlayground(pID);
                            System.out.println("Accept request NO. ....?");
                            int rID = scanner.nextInt();
                            requestHandler.acceptRequest(rID);
                        }
                        break;
                    }
                    case 2: //View My Requests
                    {
                        newPGsHandler.viewRequestsOf(currentUser.getUserName());
                        System.out.println("Want To add new playground?");
                        System.out.println("0 = No, 1 = Yes");
                        option = scanner.nextInt();
                        if (option == 1) {
                            menu.registerNewPlayGround();
                        }
                        break;

                    }

                    case 3: {
                        //View My Bookings
                        break;
                    }

                    case 4: {
                        System.exit(0);
                    }

                }

            }
            if (currentUser.getAccountType().equalsIgnoreCase("Admin"))
            {
                System.out.println("1- View Reports");
                System.out.println("2- Manage Playgrounds");
                System.out.println("3- View new Playground Requests");
                System.out.println("4- Exit");
                option = scanner.nextInt();
                switch (option) {
                    case 1: //View Reports
                    {
                        //show all playgrounds and their ratings
                        break;
                    }
                    case 2: //Manage Playgrounds
                    {
                        //suspend or delete playgrounds
                        break;
                    }
                    case 3: //View new Playground Requests
                        //FULLY WORKING pls DO NOT EDIT
                    {
                        System.out.println("View Requests of who?");
                        System.out.println("Write \"all\" to show all Requests");
                        String own = scanner.next();
                        newPGsHandler.viewRequestsOf(own);
                        System.out.println("Do you want to accept requests?");
                        System.out.println("0 = NO, 1 = Yes");
                        option = scanner.nextInt();
                        if (option == 1) {
                            System.out.println("Accept request number...?");
                            option = scanner.nextInt();
                            newPGsHandler.acceptRequest(option);
                        }
                        break;
                    }
                    case 4: {
                        System.exit(0);
                    }

                }
            }
            if (currentUser.getAccountType().equalsIgnoreCase( "Player")) {
                System.out.println("1-View my requests");
                System.out.println("2-View my bookings");
                System.out.println("3-Request a playground");
                System.out.println("4-Exit");

                option = scanner.nextInt();
                switch (option) {
                    case 1: {
                        requestHandler.showRequestsByPlayer(currentUser.getUserName());
                        break;
                    }
                    case 2: {
                        playgroundHandler.getBookingsOf(currentUser.getUserName());
                        break;
                    }
                    case 3: {
                        playgroundHandler.showAllPlaygrounds();
                        System.out.println("please enter the ID of the playground you want to book");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        if (playgroundHandler.validate(id))
                        {
                            //addRequest(Date startTime, int playersCount, int forPlayground,
                            //                                  String bookingCreator)
                            System.out.println("What Time do you want to reserve?");
                            System.out.println("Enter Time as MMM/dd/yyyy/HH");
                            System.out.println("EX: Jun/13/2021/12");
                            String time = scanner.nextLine();
                            Date date;
                            try
                            {
                                date = dateFormater.parse(time);
                            } catch (ParseException e)
                            {
                                System.out.println("Date format error");
                                System.out.println("Setting to Now!");
                                date = calendar.getTime();
                            }
                            System.out.println("How many players are with you ?");
                            int pNum = scanner.nextInt();
                            String player = currentUser.getUserName();
                            requestHandler.addRequest(date,pNum,id,player);

                        } else System.out.println("invalid id");
                        break;
                    }
                    case 4: {
                        System.exit(0);
                    }

                }
            }
        }


    }

    /**
     * Method handle the operation of creating and adding a new user
     *
     * @return true if the registration is done
     */
    public static boolean addUser() {
        String name, email, pass, aType;
        System.out.println("Enter your infromations folllowing this format");
        System.out.println("username email password type(player/owner)");
        name = scanner.next();
        email = scanner.next();
        pass = scanner.next();
        aType = scanner.next();

        if (!userHandler.checkIfExists(name)) {
            userHandler.adduser(name, email, pass, aType);

            return true;
        }
        return false;

    }

    /**
     * Method handle the operation of checking the existence and the validation of the user's email and password
     *
     * @return
     */
    public static boolean login() {

        String userName;
        String pass;
        System.out.print("username: ");
        userName = scanner.nextLine();
        System.out.print("password: ");
        pass = scanner.nextLine();
        if (userHandler.checkIfExists(userName)) {
            if (userHandler.signIn(userName, pass)) {
                return true;
            }
            System.out.println("Wrong password");
            return false;
        }
        System.out.println("No Existing account with that email");
        return false;
    }

}




