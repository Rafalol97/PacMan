package rafalwisnia.Events;

public class Event {
    public enum Type{
        CheckCoin,
        Reset,
        StartGhost1,
        StartGhost2,
        StartGhost3,
        StartGhost4,
    }
    private Type type;
    boolean handled;
    private int x;
    private int y;
    public Event(Type type,int x,int y){
        this.type= type;
        this.x=x;
        this.y=y;

    }
    public Type getType(){
        return type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
