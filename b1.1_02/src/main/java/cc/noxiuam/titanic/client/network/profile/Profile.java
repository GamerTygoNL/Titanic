package cc.noxiuam.titanic.client.network.profile;

import cc.noxiuam.titanic.client.network.cosmetic.Cosmetic;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class Profile {

    private String username;
    private Cosmetic cosmetic;

}
