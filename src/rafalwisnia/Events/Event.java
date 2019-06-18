package rafalwisnia.Events;

/**
 * Klasa reprezentujaca zdarzenia.
 * Kazdy obiekt tej klasy ma odpowiedni typ, ktory jest identyfikowany przez inne obiekty i odpwowiednio interpretowany
 */
public class Event {
    public enum Type{
        CheckCoin,
        Reset,
        StartGhost1,
        StartGhost2,
        StartGhost3,
        StartGhost4,
        Dead
    }
    private Type type;
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
