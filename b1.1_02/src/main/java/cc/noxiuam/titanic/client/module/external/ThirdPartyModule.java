package cc.noxiuam.titanic.client.module.external;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ThirdPartyModule {

    private String name;
    private String mainClass;

    private boolean extractable;
    private boolean executable;

    private String link;

    private String modPath;
    private String modFile;

    private String executeCommand;

}
