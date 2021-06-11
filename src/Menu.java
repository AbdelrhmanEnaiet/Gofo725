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

        System.out.println("Now let's set available times:");
        System.out.println();
        int[][] slots = new int [7][12];
        for (int d=1; d<8;d++)
        {
            if (d==6) continue; //exclude friday
            System.out.println("For how long the playground be available at "+ getDayNameFromInt(d)+" ?");
            if(d==1) System.out.println("Enter hours available after 8Am");
            int h=scanner.nextInt()/2;
            if (h<1||h>6) continue;
            for (int i=8;i<21;i++)
                if (h>0)
                    continue;
                else slots[d][i]=1;
                h--;
        }
        Playground temp = new Playground(pId, name, address, maxTeamSize, currentUser.getUserName());
        temp.setavailableSlots(slots);
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
    public String getDayNameFromInt(int dayNum)
    {
        switch (dayNum)
        {
            case 1:
                return "Sunday";
            case 2:
                return "Monday";
            case 3:
                return "Tuesday";
            case 4:
                return "Wednesday";
            case 5:
                return "Thursday";
            case 6:
                return "Friday";
            case 7:
                return "Saturday";
            default:
                return "ERROR";
        }
    }
}
