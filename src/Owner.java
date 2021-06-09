public class Owner extends User {
    // comment
    public Owner(String name, String e, String pass, String aType) //constructor
    {
        super(name, e, pass, aType);
    }

    public Owner(User u) {
        super(u);
    }


    public boolean addNewPlayground(Playground playground) {
        return newPGsHandler.addRequest(this, playground);
    }

}
