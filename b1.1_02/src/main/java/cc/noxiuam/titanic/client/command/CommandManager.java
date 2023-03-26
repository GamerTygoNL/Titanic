package cc.noxiuam.titanic.client.command;

import cc.noxiuam.titanic.Ref;
import cc.noxiuam.titanic.client.module.impl.normal.chat.ChatEditorModule;
import cc.noxiuam.titanic.client.util.chat.ChatColor;
import cc.noxiuam.titanic.event.impl.chat.ChatSendEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandManager {

    // All commands that need to be registered.
    private final List<Command> commands = new ArrayList<>();

    public CommandManager() {
        Ref.getEventManager().addEvent(ChatSendEvent.class, this::onChatMessage);
    }

    private void onChatMessage(ChatSendEvent event) {
        String msg = event.getMessage();
        String[] args = msg.split(" ");

        ChatEditorModule chatEditorModule = Ref.getModuleManager().getChatEditorModule();

        if (msg.length() != 0) {
            chatEditorModule.getChatMessageHistory().add(msg);
            chatEditorModule.chatMessageIndex = chatEditorModule.getChatMessageHistory().size();
        }

        if (args.length > 0 && args[0].startsWith(".")) {
            this.findAndExecuteCommmand(args);
            event.cancel();
        }
    }

    /**
     * Finds the command and executes it with the provided arguments.
     *
     * @param args Arguments from the player.
     */
    public void findAndExecuteCommmand(String[] args) {
        new Thread(() -> {
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            String label = args[0].replace(".", "");

            for (Command command : this.commands) {
                if (command.getName().equalsIgnoreCase(label)) {
                    command.execute(label, Arrays.copyOfRange(args, 1, args.length));
                    return;
                }
            }

            Ref.getMinecraft().ingameGUI.addChatMessage(ChatColor.RED + "Unknown command: \"." + label + "\"");
        }).start();
    }

}
