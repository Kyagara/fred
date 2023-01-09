package net.kyagara.fred.screen;

import io.wispforest.owo.ui.base.BaseOwoScreen;
import io.wispforest.owo.ui.component.Components;
import io.wispforest.owo.ui.container.Containers;
import io.wispforest.owo.ui.container.FlowLayout;
import io.wispforest.owo.ui.container.VerticalFlowLayout;
import io.wispforest.owo.ui.core.*;
import net.kyagara.fred.keybind.MusicControlKeybind;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MusicPlayerScreen extends BaseOwoScreen<FlowLayout> {
	private final ArrayList<Identifier> musics;

	public MusicPlayerScreen(ArrayList<Identifier> musics) {
		this.musics = musics;
	}

	@Override
	protected @NotNull OwoUIAdapter<FlowLayout> createAdapter() {
		return OwoUIAdapter.create(this, Containers::verticalFlow);
	}

	@Override
	protected void build(FlowLayout rootComponent) {
		rootComponent.surface(Surface.VANILLA_TRANSLUCENT).horizontalAlignment(HorizontalAlignment.CENTER).verticalAlignment(VerticalAlignment.CENTER);

		VerticalFlowLayout mainPanel = Containers.verticalFlow(Sizing.content(), Sizing.content());
		mainPanel.surface(Surface.DARK_PANEL).padding(Insets.of(5)).horizontalAlignment(HorizontalAlignment.CENTER);
		mainPanel.child(Components.label(Text.translatable("text.screen.fred.music_player")).shadow(true).margins(Insets.of(5).withBottom(10)));

		VerticalFlowLayout categoriesContainer = Containers.verticalFlow(Sizing.content(), Sizing.content());

		for (Identifier music : musics) {
			categoriesContainer.child(Components.button(Text.translatable(music.toString()), button -> MusicControlKeybind.PlayMusic(client, music)));
		}

		VerticalFlowLayout categoriesPanel = Containers.verticalFlow(Sizing.content(), Sizing.content());
		categoriesPanel.child(Containers.verticalScroll(Sizing.fill(40), Sizing.fill(60), categoriesContainer)).padding(Insets.of(5)).surface(Surface.flat(0x77000000).and(Surface.outline(0x77000000)));

		mainPanel.child(categoriesPanel);
		rootComponent.child(mainPanel);
	}
}