public class Player extends User {

	/**
	 * Register a new player
	 * @param name The user's username
	 * @param e The user's email
	 * @param pass The user's password
	 * @param aType The user's account type
	 */
    public Player(String name, String e, String pass, String aType) {
        super(name, e, pass, aType);
    }
    /**
     * Copy the player's information to another player object
     * @param u A user object
     */
    public Player(User u) {
        super(u);
    }


}
