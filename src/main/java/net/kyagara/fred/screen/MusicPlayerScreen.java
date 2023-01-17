package net.kyagara.fred.screen;

import io.wispforest.owo.ui.base.BaseOwoScreen;
import io.wispforest.owo.ui.component.Components;
import io.wispforest.owo.ui.container.Containers;
import io.wispforest.owo.ui.container.FlowLayout;
import io.wispforest.owo.ui.container.HorizontalFlowLayout;
import io.wispforest.owo.ui.container.VerticalFlowLayout;
import io.wispforest.owo.ui.core.*;
import net.kyagara.fred.keybind.MusicControlKeybind;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MusicPlayerScreen extends BaseOwoScreen<FlowLayout> {
	private final ArrayList<Identifier> songs;
	private final ArrayList<Identifier> categories;

	public MusicPlayerScreen(ArrayList<Identifier> songs, ArrayList<Identifier> categories) {
		this.songs = songs;
		this.categories = categories;
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

		HorizontalFlowLayout contentPanel = Containers.horizontalFlow(Sizing.content(), Sizing.content());
		contentPanel.verticalAlignment(VerticalAlignment.CENTER);

		VerticalFlowLayout songsContainer = Containers.verticalFlow(Sizing.content(), Sizing.content());

		for (Identifier song : songs) {
			songsContainer.child((Component) Components.button(Text.translatable(song.toString()), button -> MusicControlKeybind.PlayMusic(client, song)));
		}

		VerticalFlowLayout categoriesContainer = Containers.verticalFlow(Sizing.content(), Sizing.content());

		for (Identifier category : categories) {
			categoriesContainer.child((Component) Components.button(Text.translatable(category.toString()), button -> MusicControlKeybind.PlayMusic(client, category)));
		}

		VerticalFlowLayout songsPanel = Containers.verticalFlow(Sizing.content(), Sizing.content());
		songsPanel.child(Containers.verticalScroll(Sizing.fill(40), Sizing.fill(60), songsContainer)).padding(Insets.of(5)).surface(Surface.flat(0x77000000).and(Surface.outline(0x77000000)));

		VerticalFlowLayout categoriesPanel = Containers.verticalFlow(Sizing.content(), Sizing.content());
		categoriesPanel.child(Containers.verticalScroll(Sizing.fill(40), Sizing.fill(60), categoriesContainer)).padding(Insets.of(5)).surface(Surface.flat(0x77000000).and(Surface.outline(0x77000000)));

		contentPanel.child(songsPanel);
		contentPanel.child(categoriesPanel);

		mainPanel.child(contentPanel);
		rootComponent.child(mainPanel);
	}
}