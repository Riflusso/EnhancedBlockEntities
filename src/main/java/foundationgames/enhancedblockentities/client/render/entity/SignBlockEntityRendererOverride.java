package foundationgames.enhancedblockentities.client.render.entity;

import foundationgames.enhancedblockentities.client.render.BlockEntityRendererOverride;
import foundationgames.enhancedblockentities.mixin.AbstractSignBlockEntityRenderAccessor;
import foundationgames.enhancedblockentities.mixin.SignBlockEntityRenderAccessor;
import net.minecraft.block.AbstractSignBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;

public class SignBlockEntityRendererOverride extends BlockEntityRendererOverride {
    public SignBlockEntityRendererOverride() {}

    @Override
    public void render(BlockEntityRenderer<BlockEntity> renderer, BlockEntity blockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        if (blockEntity instanceof SignBlockEntity entity) {
            var state = entity.getCachedState();
            AbstractSignBlock block = (AbstractSignBlock) state.getBlock();
            var sign = (SignBlockEntityRenderAccessor) renderer;
            var aSign = (AbstractSignBlockEntityRenderAccessor) renderer;
            sign.enhanced_bes$applyTransforms(matrices, -block.getRotationDegrees(state), state);
            aSign.enhanced_bes$renderText(entity.getPos(), entity.getFrontText(), matrices, vertexConsumers, light, entity.getTextLineHeight(), entity.getMaxTextWidth(), true);
            aSign.enhanced_bes$renderText(entity.getPos(), entity.getBackText(), matrices, vertexConsumers, light, entity.getTextLineHeight(), entity.getMaxTextWidth(), false);
        }
    }
}
