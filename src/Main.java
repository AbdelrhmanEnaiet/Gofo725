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
        boolean loop=true;
        
        //This while loop is for testing the register and Login methods 
    	while(loop) {
    	System.out.println("Welcome to Gofo!!");
    	System.out.println("1- Register a new account");
    	System.out.println("2- Login");
    	System.out.println("3- Exit");
    	 option=0;
    	option=scanner.nextInt();
    	scanner.nextLine();
    	switch(option) {
    	
    	case 1:
	    	{
	    		if(addUser()) {// summon a boolean method (in the Main class) to add user and check if operation is success
	    			
	    			System.out.println("Registered Successfuly");
	    			loop=false;// turn false to exit the loop if the registration is done
	    			
	    		}else {//failed operation is due an existing user with the same email
	    			System.out.println("Error User Exists");	
	    		}
	    		break;
	    	}
    	case 2:
    		{    			
    			if(login()) {//boolean method (in the Main class) to handle the login process
    				System.out.println("Login is Success");
    				loop=false;
    			}else {
    				System.out.println("Wrong password");
    			}
    			break;
    		}
    	case 3:
	    	{
	    		System.exit(0);
	    	}
	    	default:
	    		System.out.println("Choose a valid number");
    		
    			
	    	}
    }
        
        
        
        
        
        
        
        
        
        
        
        newPGsHandler.playgroundHandler =playgroundHandler;
        //A user Signed in and stored as the current user

        //#############################################
        //##           temp user for testing         ##
       // Owner o1 = new Owner("jack","jack@gmail.com","1234","Owner");
        //userHandler.setCurrentUser(o1);
        //##        To be removed After Sign in      ##
        //#############################################

        currentUser = userHandler.getCurrentUser(); //for Easy access

        //greeting the user and confirming his account type
        menu.printFramed("System Starting...");
        System.out.println("Hello "+ currentUser.getUserName()+ ",you Are a "+ currentUser.getAccountType());
        System.out.println("What do you want to do?");
        while (true)
        {
            if (currentUser.getAccountType().equalsIgnoreCase("Owner"))
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
    
/**
 * Method handle the operation of creating and adding a new user
 * @return true if the registration is done 
 */
public static boolean addUser() {
	String name, email, pass, aType;
	System.out.println("Enter your infromations folllowing this format");
	System.out.println("username email password type(player/owner)");
	name=scanner.next();
	email=scanner.next();
	pass=scanner.next();
	aType=scanner.next();
	
	if(!userHandler.checkIfExists(email)) {
	userHandler.adduser(name, email, pass, aType);
	
	return true;}
	return false;
	
}

/**
 * Method handle the operation of checking the existence and the validation of the user's email and password
 * @return
 */
public static boolean login() {
	
	String email;
	String pass;
	System.out.print("email: ");
	email=scanner.nextLine();
	System.out.print("password: ");
	pass=scanner.nextLine();
	if(userHandler.checkIfExists(email)) {
		if(userHandler.signIn( email, pass)) {
			return true;	
		}	
		System.out.println("Wrong password");
		return false;
	}
	System.out.println("No Existing account with that email");
	return false;
}
    
}




