package cc.noxiuam.titanic.event;

import cc.noxiuam.titanic.client.util.Logger;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

public class EventManager {

    private final ConcurrentHashMap<Class<?>, CopyOnWriteArrayList<Consumer>> runningEvents = new ConcurrentHashMap<>();

    private final Logger logger = new Logger("Events");

    /**
     * Adds an event to the runningEvents map.
     *
     * @param event The event to add.
     * @param consumer The method to use with the method.
     */
    public <T extends AbstractEvent> void addEvent(Class<T> event, Consumer<T> consumer) {
        this.runningEvents.computeIfAbsent(event, ignored -> new CopyOnWriteArrayList<>()).add(consumer);
    }

    /**
     * Handles through the event's method.
     *
     * @param event The event to handle.
     */
    public void handleEvent(AbstractEvent event) {
        try {
            for (Class<?> clazz = event.getClass(); clazz != null && clazz != AbstractEvent.class; clazz = ((Class<?>) clazz).getSuperclass()) {
                CopyOnWriteArrayList<Consumer> events = this.runningEvents.get(clazz);

                if (events != null) {
                    events.forEach(consumer -> consumer.accept(event));
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

}
