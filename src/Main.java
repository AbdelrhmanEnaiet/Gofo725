import java.util.Scanner;

public class Main
{
    public static Scanner scanner = new Scanner(System.in);
    public static Menu menu= new Menu ();
    public static UserHandler userHandler = new UserHandler();
    public static User currentUser;
    public static NewPGsHandler newPGsHandler = new NewPGsHandler();
    public static PlaygroundHandler playgroundHandler = new PlaygroundHandler();


    public static void main(String[] args)
    {
        int option=0;
        newPGsHandler.playgroundHandler =playgroundHandler;
        //A user Signed in and stored as the current user

        //#############################################
        //##           temp user for testing         ##
        Owner o1 = new Owner("jack","jack@gmail.com","1234","Owner");
        userHandler.setCurrentUser(o1);
        //##        To be removed After Sign in      ##
        //#############################################

        currentUser = userHandler.getCurrentUser(); //for Easy access

        //greeting the user and confirming his account type
        menu.printFramed("System Starting...");
        System.out.println("Hello "+ currentUser.getUserName()+ ",you Are a "+ currentUser.getAccountType());
        System.out.println("What do you want to do?");
        while (true)
        {
            if (currentUser.getAccountType()=="Owner")
            {
                System.out.println("1- View My Playgrounds");
                System.out.println("2- View My Requests");
                System.out.println("3- View My Bookings");
                System.out.println("4- Exit");

                option = scanner.nextInt();
                switch (option)
                {
                    case 1: //View My Playgrounds
                    {
                        //function
                        break;
                    }
                    case 2: //View My Requests
                           //FULLY WORKING pls DO NOT EDIT
                    {
                        newPGsHandler.viewRequestsOf(currentUser.userName);
                        System.out.println("Want To add new playground?");
                        System.out.println("0 = No, 1 = Yes");
                        option = scanner.nextInt();
                        if (option==1)
                        {
                            menu.registerNewPlayGround();
                        }
                        break;

                    }

                    case 3:
                    {
                        //View My Bookings
                        break;
                    }

                    case 4:
                    {
                        System.exit(0);
                    }

                }

            }
            if (currentUser.getAccountType()=="Admin")
            {
                currentUser = null;
                System.out.println("1- View Reports");
                System.out.println("2- Manage Playgrounds");
                System.out.println("3- View new Playground Requests");
                option = scanner.nextInt();
                switch (option)
                {
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
                        System.out.println("Leave empty to show all Requests");
                        String own = scanner.next();
                        newPGsHandler.viewRequestsOf(own);
                        System.out.println("Do you want to accept requests?");
                        System.out.println("0 = NO, 1 = Yes");
                        option = scanner.nextInt();
                        if (option == 1)
                        {
                            System.out.println("Accept request number...?");
                            option = scanner.nextInt();
                            Admin.acceptRequest(option);
                        }

                    }
                }
            }
            if (currentUser.getAccountType()=="Player")
            {
                //functions
            }
        }


    }
}
