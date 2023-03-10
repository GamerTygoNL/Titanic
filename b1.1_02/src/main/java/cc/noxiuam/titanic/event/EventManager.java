package cc.noxiuam.titanic.event;

import cc.noxiuam.titanic.client.util.Logger;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

public class EventManager {

    private final ConcurrentHashMap<Class<?>, CopyOnWriteArrayList<Consumer>> activeEvents = new ConcurrentHashMap<>();

    private final Logger logger = new Logger("Events");

    /**
     * Adds an event to the runningEvents map.
     *
     * @param event The event to add.
     * @param consumer The method to use with the method.
     */
    public <T extends AbstractEvent> void addEvent(Class<T> event, Consumer<T> consumer) {
        this.activeEvents.computeIfAbsent(event, ignored -> new CopyOnWriteArrayList<>()).add(consumer);
    }

    /**
     * In short, this removes an event from the active events.
     */
    public <T extends AbstractEvent> boolean removeEvent(final Class<T> clazz, final Consumer<T> consumer) {
        final CopyOnWriteArrayList<Consumer> list = this.activeEvents.get(clazz);
        return list != null && list.remove(consumer);
    }

    /**
     * Handles through the event's consumer.
     *
     * @param event The event to handle.
     */
    public void handleEvent(AbstractEvent event) {
        try {
            for (Class<?> clazz = event.getClass(); clazz != null && clazz != AbstractEvent.class; clazz = ((Class<?>) clazz).getSuperclass()) {
                CopyOnWriteArrayList<Consumer> events = this.activeEvents.get(clazz);

                if (events != null) {
                    events.forEach(consumer -> consumer.accept(event));
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

}
