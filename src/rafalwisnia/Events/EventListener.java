package rafalwisnia.Events;

/**
 * Interfejs wykorzystywany w innych klasach do nasluchiwania wydarzen
 */
public interface EventListener {
    void onEvent(Event event);
}
