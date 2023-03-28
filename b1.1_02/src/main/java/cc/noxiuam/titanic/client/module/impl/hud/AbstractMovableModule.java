package cc.noxiuam.titanic.client.module.impl.hud;

import cc.noxiuam.titanic.client.module.AbstractModule;
import cc.noxiuam.titanic.client.module.data.setting.impl.MultiOptionSetting;
import cc.noxiuam.titanic.client.util.chat.ChatColor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

@Setter
@Getter
@Accessors(fluent = true)
public abstract class AbstractMovableModule extends AbstractModule {

    private final Map<String, ChatColor> textColors = new LinkedHashMap<>();

    private float x;
    private float y;

    public float defaultX = 0.0f;
    public float defaultY = 0.0f;

    private float width;
    private float height;

    private final MultiOptionSetting color;

    public AbstractMovableModule(String id, String name, boolean enabledByDefault) {
        super(id, name, enabledByDefault);

        this.textColors.put("White", ChatColor.WHITE);
        this.textColors.put("Aqua", ChatColor.AQUA);
        this.textColors.put("Red", ChatColor.RED);
        this.textColors.put("Gold", ChatColor.GOLD);
        this.textColors.put("Green", ChatColor.GREEN);
        this.textColors.put("Purple", ChatColor.LIGHT_PURPLE);
        this.textColors.put("Yellow", ChatColor.YELLOW);
        this.textColors.put("Blue", ChatColor.DARK_BLUE);
        this.textColors.put("Gray", ChatColor.GRAY);
        this.textColors.put("Dark Red", ChatColor.DARK_RED);
        this.textColors.put("Dark Green", ChatColor.DARK_GREEN);
        this.textColors.put("Dark Purple", ChatColor.DARK_PURPLE);
        this.textColors.put("Dark Blue", ChatColor.DARK_BLUE);
        this.textColors.put("Dark Aqua", ChatColor.DARK_AQUA);
        this.textColors.put("Dark Gray", ChatColor.DARK_GRAY);

        this.color = new MultiOptionSetting(
                "color",
                "Text Color",
                "White",
                "White", "Aqua", "Red", "Gold",
                "Green", "Purple", "Yellow", "Blue",
                "Gray", "Dark Red", "Dark Green", "Dark Purple",
                "Dark Blue", "Dark Aqua", "Dark Gray", "Chroma"
        );
    }

    public void setPosition(float newX, float newY) {
        this.x = newX;
        this.y = newY;
    }

    public void setSize(float newWidth, float newHeight) {
        this.width = newWidth;
        this.height = newHeight;
    }

    public boolean mouseInside(float mouseX, float mouseY) {
        return mouseX > this.x
                && mouseX < this.x + this.width
                && mouseY > this.y
                && mouseY < this.y + this.height;
    }

    public String getPrefixedTextColor() {
        return (this.color().value().equalsIgnoreCase("Chroma") ? "" : this.getTextColor().toString());
    }

    private ChatColor getTextColor() {
        return this.textColors.get(this.color.value());
    }

    protected int getChromaColor() {
        float hue = (float) System.nanoTime() / 1.0E10f % 1.0f;
        return Color.HSBtoRGB(hue, 1.0f, 1.0f) & 0xFFFFFF;
    }

}
