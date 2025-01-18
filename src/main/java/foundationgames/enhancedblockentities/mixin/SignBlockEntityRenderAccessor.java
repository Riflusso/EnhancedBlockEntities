package foundationgames.enhancedblockentities.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.client.render.block.entity.SignBlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(SignBlockEntityRenderer.class)
public interface SignBlockEntityRenderAccessor {
    @Invoker("applyTransforms")
    void enhanced_bes$applyTransforms(MatrixStack matrices, float rotationDegrees, BlockState state);
}
