package logic;

public interface Observer {
    void update();

    void update(String type);

    void update(int amount);
}
