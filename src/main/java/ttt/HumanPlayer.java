package ttt;

public class HumanPlayer implements Player {

    private String mark;

    public HumanPlayer(String mark) {
        this.mark = mark;
    }

    public String getMark() {
        return mark;
    }

    public int getLocation(Input inputFeed, GameEngine game) {
        String location = inputFeed.get();
            return (Integer.parseInt(location) - 1);
    }
}