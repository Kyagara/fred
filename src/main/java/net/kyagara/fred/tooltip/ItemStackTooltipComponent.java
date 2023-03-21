// https://github.com/apace100/show-me-what-you-got

package net.kyagara.fred.tooltip;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;

public class ItemStackTooltipComponent implements TooltipComponent {
	private final ItemStack stack;

	public ItemStackTooltipComponent(ItemStack stack) {
		this.stack = stack;
	}

	@Override
	public int getWidth(TextRenderer textRenderer) {
		return 18;
	}

	@Override
	public int getHeight() {
		return 18;
	}

	@Override
	public void drawItems(TextRenderer textRenderer, int x, int y, MatrixStack matrices, ItemRenderer itemRenderer) {
		itemRenderer.renderInGui(matrices, stack, x, y);

		String countLabel = stack.getCount() == 1 ? "" : Integer.toString(stack.getCount());

		itemRenderer.renderGuiItemOverlay(matrices, textRenderer, stack, x, y, countLabel);
	}
}