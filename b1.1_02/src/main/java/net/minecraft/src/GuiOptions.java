package net.minecraft.src;

public class GuiOptions extends GuiScreen {

    protected String screenTitle;
    private GuiScreen parentScreen;
    private GameSettings options;

    public GuiOptions(GuiScreen guiscreen, GameSettings gamesettings) {
        screenTitle = "Options";
        parentScreen = guiscreen;
        options = gamesettings;
    }

    public void initGui() {
        StringTranslate stringtranslate = StringTranslate.func_20162_a();
        screenTitle = stringtranslate.func_20163_a("options.title");
        EnumOptions aenumoptions[] = EnumOptions.values();
        int i = aenumoptions.length;
        for (int j = 0; j < i; j++) {
            EnumOptions enumoptions = aenumoptions[j];
            int k = enumoptions.func_20135_c();
            if (!enumoptions.func_20136_a()) {
                controlList.add(new GuiSmallButton(enumoptions.func_20135_c(), (width / 2 - 155) + (k % 2) * 160, height / 6 + 24 * (k >> 1), enumoptions, options.getKeyBinding(enumoptions)));
            } else {
                controlList.add(new GuiSlider(enumoptions.func_20135_c(), (width / 2 - 155) + (k % 2) * 160, height / 6 + 24 * (k >> 1), enumoptions, options.getKeyBinding(enumoptions), options.func_20104_a(enumoptions)));
            }
        }

        controlList.add(new GuiButton(100, width / 2 - 100, height / 6 + 120 + 12, stringtranslate.func_20163_a("options.controls")));
        controlList.add(new GuiButton(200, width / 2 - 100, height / 6 + 168, stringtranslate.func_20163_a("gui.done")));
    }

    protected void actionPerformed(GuiButton guibutton) {
        if (!guibutton.enabled) {
            return;
        }
        if (guibutton.id < 100 && (guibutton instanceof GuiSmallButton)) {
            options.setOptionValue(((GuiSmallButton) guibutton).func_20078_a(), 1);
            guibutton.displayString = options.getKeyBinding(EnumOptions.func_20137_a(guibutton.id));
        }
        if (guibutton.id == 100) {
            mc.gameSettings.saveOptions();
            mc.displayGuiScreen(new GuiControls(this, options));
        }
        if (guibutton.id == 200) {
            mc.gameSettings.saveOptions();
            mc.displayGuiScreen(parentScreen);
        }
    }

    public void drawScreen(int i, int j, float f) {
        drawDefaultBackground();
        drawCenteredString(fontRenderer, screenTitle, width / 2, 20, 0xffffff);
        super.drawScreen(i, j, f);
    }
}
