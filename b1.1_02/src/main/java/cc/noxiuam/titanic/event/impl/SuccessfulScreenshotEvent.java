package cc.noxiuam.titanic.event.impl;

import cc.noxiuam.titanic.event.AbstractEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SuccessfulScreenshotEvent extends AbstractEvent {

    private String screenshotName;

}
