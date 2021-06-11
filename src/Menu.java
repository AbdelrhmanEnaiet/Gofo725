public class Menu extends Main {
    //testing commit
    //ADD here functions used in main, sorted for each account type
    public boolean registerNewPlayGround() //for owner
    {
        printFramed("Register a New playground");

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

        Playground temp = new Playground(pId, name, address, maxTeamSize, currentUser.getUserName());
        if (newPGsHandler.addRequest((Owner) currentUser, temp))
            System.out.println("Done !\nYour Request is sent and waiting approval");
        else
            System.out.println("Error");

        return true;
    }


    public void printFramed(String text)   //To print a string in a frame (for titles)
    {
        int txtLength = text.length();

        for (int i = 0; i < txtLength + 8; i++)
            System.out.print("#");
        System.out.println();
        System.out.println("##  " + text + "  ##");
        for (int i = 0; i < txtLength + 8; i++)
            System.out.print("#");
        System.out.println();
    }
}
