// https://github.com/apace100/show-me-what-you-got

package net.kyagara.fred.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.kyagara.fred.tooltip.HorizontalLayoutTooltipComponent;
import net.kyagara.fred.tooltip.ItemStackTooltipComponent;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.Style;
import net.minecraft.text.HoverEvent.ItemStackContent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import java.util.List;

@Environment(EnvType.CLIENT)
@Mixin(Screen.class)
public abstract class ScreenMixin {
    @Shadow
    protected TextRenderer textRenderer;

    @Unique
    private ItemStack stackToRender;

    @Inject(method = "renderTextHoverEffect", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/Screen;renderTooltip(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/item/ItemStack;II)V"))
    private void renderTextHoverEffect(MatrixStack matrices, Style style, int x, int y, CallbackInfo ci) {
        HoverEvent hoverEvent = style.getHoverEvent();
        ItemStackContent itemStackContent = hoverEvent.getValue(HoverEvent.Action.SHOW_ITEM);

        if (itemStackContent != null) {
            ItemStack stack = itemStackContent.asStack();
            this.stackToRender = stack;
        }
    }

    @Inject(method = "renderTooltipFromComponents", at = @At("HEAD"))
    private void renderTooltipFromComponents(MatrixStack matrices, List<TooltipComponent> components,
            int x, int y, CallbackInfo ci) {

        if (this.stackToRender == null || this.stackToRender.isEmpty() || components.size() == 0) {
            return;
        }

        TooltipComponent originalComponent = components.get(0);
        TooltipComponent stackComponent = new ItemStackTooltipComponent(this.stackToRender);
        TooltipComponent combinedComponent;

        if (this.textRenderer.isRightToLeft()) {
            combinedComponent = new HorizontalLayoutTooltipComponent(List.of(originalComponent, stackComponent), 3);
        } else {
            combinedComponent = new HorizontalLayoutTooltipComponent(List.of(stackComponent, originalComponent), 3);
        }

        components.set(0, combinedComponent);
        this.stackToRender = null;
    }
}