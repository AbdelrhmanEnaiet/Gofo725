import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;



public class UserHandler
{
    private static User currentUser;
    public static ArrayList<User> users=new ArrayList<>();
    private static String InnerFile;
    private static File file;
    /**
     * create a new file and a text file inside it to save the users data in.
     * and load any old users if the file already exists
     */
    public UserHandler()
    {
        currentUser=null;
        
        
    	//check if there is a file for the desired destination
    	file=new File("GofoData");
    	//make directory if new and set path to credentials
    	if(file.mkdir()) {InnerFile=file.getAbsolutePath()+"\\credentials.txt";

    	file=new File(InnerFile);//set the new file path to create the credentials
    	try {
			file.createNewFile();//create the txt file
		} catch (IOException e) {
			e.printStackTrace();
		}}
    	//if it exist then set path to credentials
    	else{InnerFile=file.getAbsolutePath()+"\\credentials.txt";
    		
    	file=new File(InnerFile);//set the existing file path
    	loadUsers();//load all the exist
    		
    		  
    	}
    }
    
   

    public static User getUserByName(String name)// made it static to return the object without error as it does not have a privilege to edit it 
    {User temp = null;
    
        for (int i=0; i<users.size(); i++)
        {
            if ( users.get(i).getUserName().equals(name) )
                {temp=(User) users.get(i);
                 return temp;}
        }
        
        return null;
    }

    public User getCurrentUser()
    {
        return currentUser;
    }

    public void setCurrentUser(User currentUser)
    {
        this.currentUser = currentUser;
    }
    
    
    
    
    
    /**
     *check if the user is registered before and return true if existing 
     * @param username the user name to be searched for in the user object
     * @return true if the email exist
     */
    public boolean checkIfExists(String username) {
    	
    	for (int i=0; i<users.size(); i++)
        {
            if ( users.get(i).getUserName().equals(username) )
                return true;
        }
    	
    	return false;
    	
    }
    /**
     * this method register a new user
     * @param name user's name
     * @param e user's email
     * @param pass user's password
     * @param aType user's type (owner/player)
     */
    public void adduser(String name, String e, String pass, String aType) {
    	
    	if(aType.equalsIgnoreCase("owner")) {
    		currentUser=new Owner(name, e, pass, aType);
    		
    	}else {
    		currentUser=new Player(name, e, pass, aType);
    	}
    	
    	insertUser();
    	users.add(currentUser);
    }
    /**
     * this method check if the email and password given are valid and set that user as the current user 
     * @param userName user's Name
     * @param pass user's password
     * @return true if signIn is successful 
     */
    public boolean signIn(String userName, String pass) {
    	
    	
    	for (int i=0; i<users.size(); i++)
        {
            if ( users.get(i).getUserName().equals(userName) && users.get(i).checkPassword(pass) ) {
            	currentUser=users.get(i);
            	return true;
            }
                
        }
    	return false;
    	
    	
    }
    
    /**
     * add the new user to the database file
     */
    public void insertUser() {
	    try {
	    	/*initialise an object from FileWriter class
	    	 and add the  file's absolute path in the first slot and true in second slot to allow appending 
	    	 and preserve any old data  */
	    	FileWriter myWriter = new FileWriter(file,true);  
			myWriter.write(currentUser.getUserName()+" ");//write into file the username
			myWriter.write(currentUser.getEmail()+" ");//write into file the email
			myWriter.write(currentUser.getPassword()+" ");//write into file the password
			myWriter.write(currentUser.getAccountType());//write into file account type
			myWriter.write(System.lineSeparator());// this indicate the end of the line 
			//close the file writer to save the data in the file if it is left the data will not be saved
			myWriter.close();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	    

    }
    /**
     * this method loads all the users data and set them in User class objects
     * then adds them to the users arraylist
     */
    public void loadUsers() {
    	
    	 
		try {
			Scanner fileReader;//initialise a new scanner to read from file
			fileReader = new Scanner(file);
			//this checks if there is a next line in file then it returns true and stay in loop
			while(fileReader.hasNextLine())
	         {
	             String line = fileReader.nextLine();//read the new line in the file
	             String[] txtInFile = line.split(" ");//split the string into a string list
	             if(txtInFile.length==4) {//check if the file is empty to avoid out of bound error in the list
	            	//set the attributes using the indexes of the string list
	            	 //and check if the loaded user is owner or player to load correctly
	            	 if(txtInFile[3].equalsIgnoreCase("owner")) {
	            		 currentUser=new Owner(txtInFile[0],txtInFile[1],txtInFile[2],txtInFile[3]);	 
	            	 }
	            	 else if(txtInFile[3].equalsIgnoreCase("admin")) {
	            		 currentUser=new Admin(txtInFile[0],txtInFile[1],txtInFile[2],txtInFile[3]);
	            	 }
	            	 else{currentUser=new Player(txtInFile[0],txtInFile[1],txtInFile[2],txtInFile[3]);}
	            	 
		             users.add(currentUser);//adds loaded user into users arraylist
	             }
	             
	         }
			currentUser=null;//set the currentUser to null after finishing
			fileReader.close();//close the scanner as it is not going to be used again
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
        
         

    }
    
    
}
