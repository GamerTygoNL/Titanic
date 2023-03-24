package cc.noxiuam.titanic.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NoCosmeticFoundException extends RuntimeException {

    private String targetName;

    @Override
    public String getMessage() {
        return "No cosmetic with name \"" + targetName + "\" was found.";
    }

}
