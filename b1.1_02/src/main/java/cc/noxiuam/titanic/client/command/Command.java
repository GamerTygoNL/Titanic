package cc.noxiuam.titanic.client.command;

import lombok.Data;

@Data
public abstract class Command {

    private final String name; // The name without the dot.

    /**
     * What a command should do when typed into chat.
     *
     * @param cmdLabel Command label (ex: .help)
     * @param args Command arguments (ex: .add Notch MinecraftOwner)
     */
    public abstract void execute(String cmdLabel, String[] args);

}
