package foundationgames.enhancedblockentities.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.SignText;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.AbstractSignBlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(AbstractSignBlockEntityRenderer.class)
public interface AbstractSignBlockEntityRenderAccessor {
    @Invoker("applyTransforms")
    void enhanced_bes$applyTransforms(MatrixStack matrices, float rotationDegrees, BlockState state);

    @Invoker("renderText")
    void enhanced_bes$renderText(BlockPos pos, SignText signText, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int lineHeight, int lineWidth, boolean front);

    @Accessor("MAX_COLORED_TEXT_OUTLINE_RENDER_DISTANCE")
    static int enhanced_bes$getRenderDistance() {
        throw new AssertionError();
    }
}
