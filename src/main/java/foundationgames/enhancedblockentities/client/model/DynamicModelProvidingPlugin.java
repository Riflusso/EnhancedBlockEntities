package foundationgames.enhancedblockentities.client.model;

import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelModifier;
import net.minecraft.client.render.model.UnbakedModel;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class DynamicModelProvidingPlugin implements ModelLoadingPlugin, ModelModifier.OnLoad {
    private final Supplier<DynamicUnbakedModel> model;
    private final Identifier id;

    public DynamicModelProvidingPlugin(Identifier id, Supplier<DynamicUnbakedModel> model) {
        this.model = model;
        this.id = id;
    }

    @Override
    public void initialize(ModelLoadingPlugin.Context ctx) {
        ctx.modifyModelOnLoad().register(this);
    }

    @Override
    public @Nullable UnbakedModel modifyModelOnLoad(@Nullable UnbakedModel model, ModelModifier.OnLoad.Context context) {
        if (context.id().equals(this.id)) return this.model.get();
        return model;
    }
}
