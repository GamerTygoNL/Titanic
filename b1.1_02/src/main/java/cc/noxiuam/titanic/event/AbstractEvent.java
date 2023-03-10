package cc.noxiuam.titanic.event;

import lombok.Getter;

@Getter
public abstract class AbstractEvent {

    private boolean cancelled;

    public void cancel() {
        this.cancelled = true;
    }

}
