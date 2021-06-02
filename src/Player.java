
public class Player extends User{

	public Player(String name, String e, String pass, String aType) {
		super(name, e, pass, aType);
		
	}
	
	public Player(User u){
        super(u);
    }
	

}
