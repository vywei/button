package logic;

public interface Observer {
    void update();

    void update(User user, String type);
}
