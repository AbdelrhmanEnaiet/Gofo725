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
        int[][] slots = new int[7][12];
        for (int i = 0; i < 7; i++)  //imitating the constructor
        {
            if (i != 5)   //if not friday
                for (int j = 0; j < 12; j++) {
                    if (j > 3 && j < 10) //between 8AM and 8PM
                        slots[i][j] = 0;
                    else
                        slots[i][j] = 1;
                }
            else
                for (int j = 0; j < 12; j++)
                    slots[i][j] = 1;
        }

        for (int d = 1; d < 8; d++)
        {
            if (d == 6) continue; //exclude friday
            System.out.println("For how long the playground be available at " + getDayNameFromInt(d) + " ?");
            if (d == 1) System.out.println("Enter hours available after 8Am");
            int h = scanner.nextInt() / 2;
            if (h < 0 || h > 5) continue;
            for (int i = 3; i < 10; i++)
            {
                if (h > 0)
                    slots[d - 1][i] = 0;
                else
                    slots[d - 1][i] = 1;
                h--;
            }
        }
        Playground temp = new Playground(pId, name, address, maxTeamSize, currentUser.getUserName());
        temp.setavailableSlots(slots);
        System.out.println("from menu:" + temp.getFreeTimes());
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

    public String getDayNameFromInt(int dayNum) {
        switch (dayNum) {
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
