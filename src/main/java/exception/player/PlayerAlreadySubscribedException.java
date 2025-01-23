package exception.player;

public class PlayerAlreadySubscribedException extends Exception{
    public PlayerAlreadySubscribedException(String message) {
        super(message);
    }
}
