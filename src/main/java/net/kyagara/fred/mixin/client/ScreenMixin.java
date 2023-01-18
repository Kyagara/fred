// https://github.com/apace100/show-me-what-you-got

package net.kyagara.fred.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.kyagara.fred.Fred;
import net.kyagara.fred.tooltip.HorizontalLayoutTooltipComponent;
import net.kyagara.fred.tooltip.ItemStackTooltipComponent;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.AbstractInventoryScreen;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.client.gui.tooltip.TooltipPositioner;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.HoverEvent.ItemStackContent;
import net.minecraft.text.Style;
import org.jetbrains.annotations.Nullable;
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
	@Shadow
	@Nullable
	protected MinecraftClient client;
	@Unique
	private ItemStack stackToRender;

	@Inject(method = "renderBackground*", at = @At("HEAD"), cancellable = true)
	public void renderBackground(MatrixStack matrices, CallbackInfo ci) {
		if (Fred.CONFIG.disableInventoryBackground()) {
			if (client != null && client.currentScreen != null && client.currentScreen instanceof AbstractInventoryScreen) {

				ci.cancel();
			}
		}
	}

	@Inject(method = "renderTextHoverEffect", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/Screen;renderTooltip(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/item/ItemStack;II)V"))
	private void renderTextHoverEffect(MatrixStack matrices, Style style, int x, int y, CallbackInfo ci) {
		HoverEvent hoverEvent = style.getHoverEvent();

		if (hoverEvent != null) {
			ItemStackContent itemStackContent = hoverEvent.getValue(HoverEvent.Action.SHOW_ITEM);

			if (itemStackContent != null) {
				stackToRender = itemStackContent.asStack();
			}
		}
	}

	@Inject(method = "renderTooltipFromComponents", at = @At("HEAD"))
	private void renderTooltipFromComponents(MatrixStack matrices, List<TooltipComponent> components, int x, int y, TooltipPositioner positioner, CallbackInfo ci) {
		if (stackToRender == null || stackToRender.isEmpty() || components.size() == 0) {
			return;
		}

		TooltipComponent originalComponent = components.get(0);
		TooltipComponent stackComponent = new ItemStackTooltipComponent(stackToRender);
		TooltipComponent combinedComponent;

		if (textRenderer.isRightToLeft()) {
			combinedComponent = new HorizontalLayoutTooltipComponent(List.of(originalComponent, stackComponent), 3);
		} else {
			combinedComponent = new HorizontalLayoutTooltipComponent(List.of(stackComponent, originalComponent), 3);
		}

		components.set(0, combinedComponent);
		stackToRender = null;
	}
}