package cc.noxiuam.titanic.client.module.impl.normal;

import cc.noxiuam.titanic.client.module.AbstractModule;
import cc.noxiuam.titanic.client.module.data.setting.impl.BooleanSetting;
import cc.noxiuam.titanic.client.module.data.setting.impl.KeybindSetting;
import cc.noxiuam.titanic.client.util.chat.ChatColor;
import cc.noxiuam.titanic.event.impl.SuccessfulScreenshotEvent;
import cc.noxiuam.titanic.event.impl.keyboard.KeyDownEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.src.ScreenShotHelper;
import org.lwjgl.input.Keyboard;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ScreenshotModule extends AbstractModule {

    private final KeybindSetting screenshotKeybind;
    private final BooleanSetting copyAutomatically;

    public ScreenshotModule() {
        super("screenShots", "Screenshots", false);

        this.initSettings(
                this.screenshotKeybind = new KeybindSetting("screenshotKeybind", "Screenshot Keybind", Keyboard.KEY_F2),
                this.copyAutomatically = new BooleanSetting("copyAutomatically", "Copy Automatically", false)
        );

        this.addEvent(SuccessfulScreenshotEvent.class, this::onSuccessfulScreenshot);
        this.addEvent(KeyDownEvent.class, this::onKeyPress);
    }

    private void onKeyPress(KeyDownEvent event) {
        if (event.getKey() == this.screenshotKeybind.value()) {
            this.mc.ingameGUI.addChatMessage(ScreenShotHelper.saveScreenshot(
                    Minecraft.getMinecraftDir(),
                    this.mc.displayWidth,
                    this.mc.displayHeight
            ));
            this.mc.isTakingScreenshot = true;
        } else {
            this.mc.isTakingScreenshot = false;
        }
    }

    private void onSuccessfulScreenshot(SuccessfulScreenshotEvent event) {
        if (!this.copyAutomatically.value()) {
            return;
        }

        String fileName = event.getScreenshotName();

        File screenshot = new File(Minecraft.getMinecraftDir() + File.separator + "screenshots" + File.separator + fileName);
        List<File> listOfFiles = new ArrayList<>();
        listOfFiles.add(screenshot);

        this.mc.ingameGUI.addChatMessage("[Titanic] " + ChatColor.BLUE + "Copied screenshot.");

        FileList fileList = new FileList(listOfFiles);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(fileList, (clipboard, contents) -> System.out.println("Lost ownership"));
    }

    private static class FileList implements Transferable {

        private final List<File> files;

        public FileList(List<File> files) {
            this.files = files;
        }

        @Override
        public DataFlavor[] getTransferDataFlavors() {
            return new DataFlavor[]{ DataFlavor.javaFileListFlavor };
        }

        @Override
        public boolean isDataFlavorSupported(DataFlavor flavor) {
            return DataFlavor.javaFileListFlavor.equals(flavor);
        }

        @Override
        public Object getTransferData(DataFlavor flavor) {
            return this.files;
        }
    }

}
