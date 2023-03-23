package cc.noxiuam.titanic.client.network.cosmetic;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Cosmetic {

    private String name;
    private String description;

    private boolean equipped;

    private String type;
    private String location;

}
