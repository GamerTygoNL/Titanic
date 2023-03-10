package cc.noxiuam.titanic.client.network.profile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class Profile {

    private String username;
    private boolean hasCape;
    private String rank;

}
