package foundationgames.enhancedblockentities.config.gui.option;

import foundationgames.enhancedblockentities.ReloadType;
import foundationgames.enhancedblockentities.config.EBEConfig;
import foundationgames.enhancedblockentities.util.GuiUtil;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Properties;

public final class EBEOption {
    private static final Text NEWLINE = Text.of("\n");
    private static final String OPTION_VALUE = "options.generic_value";
    private static final String DIVIDER = "text.ebe.option_value_division";
    private static final String OVERRIDDEN = "warning.ebe.overridden";

    public final String key;
    public final boolean hasValueComments;
    public final Text comment;
    public final ReloadType reloadType;
    public final TextPalette palette;
    public final @Nullable EBEConfig.Override override;

    private final List<String> values;
    private final int defaultValue;

    private int selected;
    private Tooltip tooltip = null;
    private Text text = null;

    public EBEOption(String key, List<String> values, ConfigView config, boolean hasValueComments, TextPalette palette, ReloadType reloadType) {
        this.key = key;
        this.values = values;
        this.defaultValue = MathHelper.clamp(values.indexOf(config.configValues.getProperty(key)), 0, values.size());
        this.override = config.overrides.get(key);
        this.selected = this.defaultValue;
        this.hasValueComments = hasValueComments;
        this.palette = palette;
        this.reloadType = reloadType;

        String commentKey = I18n.translate(String.format("option.ebe.%s.comment", key));
        comment = GuiUtil.shorten(commentKey, 20);
    }

    public String getValue() {
        return values.get(selected);
    }

    public String getOptionKey() {
        return String.format("option.ebe.%s", key);
    }

    public String getValueKey() {
        return String.format("value.ebe.%s", getValue());
    }

    public Text getText() {
        var option = Text.translatable(this.getOptionKey()).styled(style -> style.withColor(isDefault() ? 0xFFFFFF : 0xFFDA5E));
        var value = Text.translatable(this.getValueKey()).styled(style -> style.withColor(this.palette.getColor((float)this.selected / this.values.size())));

        if (text == null) text = option.append(Text.translatable(DIVIDER).append(value));
        return text;
    }

    public Tooltip getTooltip() {
        if (tooltip == null) {
            if (override != null) {
                var text = Text.translatable(OVERRIDDEN, override.modResponsible().getMetadata().getId())
                        .formatted(Formatting.RED, Formatting.UNDERLINE);
                if (override.reason() != null) {
                    text.append(NEWLINE).append(override.reason());
                }

                tooltip = Tooltip.of(text);
            }
            else if (hasValueComments) tooltip = Tooltip.of(Text.translatable(String.format("option.ebe.%s.valueComment.%s", key, getValue())).append(NEWLINE).append(comment.copyContentOnly()));
            else tooltip = Tooltip.of(comment.copyContentOnly());
        }
        return tooltip;
    }

    public void next() {
        selected++;
        if (selected >= values.size()) selected = 0;
        tooltip = null;
        text = null;
    }

    public boolean isDefault() {
        return selected == defaultValue;
    }

    public record ConfigView(Properties configValues, Map<String, EBEConfig.Override> overrides) {}
}
