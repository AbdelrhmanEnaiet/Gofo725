public class Player extends User {

	/**
	 * 
	 * @param name the user's username
	 * @param e the user's email
	 * @param pass the user's password
	 * @param aType the user's account type
	 */
    public Player(String name, String e, String pass, String aType) {
        super(name, e, pass, aType);
    }
    /**
     * copy the player's information to another player object
     * @param u a user object
     */
    public Player(User u) {
        super(u);
    }


}
