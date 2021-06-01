import java.util.Scanner;

public class Main
{
    public static Scanner scanner = new Scanner(System.in);
    public static Menu menu= new Menu ();
    public static UserHandler userHandler = new UserHandler();
    public static NewPGsHandler newPGsHandler = new NewPGsHandler();



    public static void main(String[] args)
    {
        int option=0;
        //A user Signed in and stored as the current user
        User currentUser = userHandler.getCurrentUser();

        //greeting the user and confirming his account type
        System.out.println("Hello "+ currentUser.getUserName()+ ",you Are a "+ currentUser.getAccountType());
        System.out.println("What do you want to do?");
        if (currentUser.getAccountType()=="Owner")
        {
            System.out.println("1- View My Playgrounds");
            System.out.println("2- View My Requests");
            System.out.println("2- View My Bookings");

            option = scanner.nextInt();
            switch (option)
            {
                case 1: //View My Playgrounds
                {
                    //function
                    break;
                }
                case 2:
                {
                    newPGsHandler.viewRequestsOf(currentUser.userName);
                    System.out.println("Want To add new playground?");
                    System.out.println("0 = No, 1 = Yes");
                    option = scanner.nextInt();
                    if (option==1)
                    {
                         int pId;
                         String name;
                         String address;
                         int maxTeamSize;

                         System.out.println("What is the ID of the playground?");
                         pId = scanner.nextInt();
                         System.out.println("What is the Name of the playground?");
                         name = scanner.next();
                         System.out.println("Where is your playground?");
                         address = scanner.next();
                         System.out.println("How many players in the largest team possible?");
                         maxTeamSize = scanner.nextInt();

                         Playground temp = new Playground(pId,name,address,maxTeamSize, (Owner) currentUser);
                         newPGsHandler.addRequest((Owner)currentUser,temp);
                         System.out.println("Done !");
                    }

                }

            }

            menu.registerNewPlayGround();
        }



    }
}
