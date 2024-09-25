package domain.user;

public class User {
    private final String id;
    private final String pass;
    public String getId() {
        return id;
    }
    public String getPass() {
        return pass;
    }
    public User(String id, String pass) {
        this.id = id;
        this.pass = pass;
    }
}
