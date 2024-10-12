package com.fred.screens;

import io.wispforest.owo.ui.base.BaseOwoScreen;
import io.wispforest.owo.ui.component.ButtonComponent;
import io.wispforest.owo.ui.component.Components;
import io.wispforest.owo.ui.component.DropdownComponent;
import io.wispforest.owo.ui.component.TextBoxComponent;
import io.wispforest.owo.ui.container.Containers;
import io.wispforest.owo.ui.container.FlowLayout;
import io.wispforest.owo.ui.core.*;
import com.fred.keybinds.MusicControl;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MusicPlayerScreen extends BaseOwoScreen<FlowLayout> {
	private final List<Identifier> songs;
	private final List<Identifier> categories;
	private final List<Identifier> discs;

	public MusicPlayerScreen(List<Identifier> songs, List<Identifier> categories, List<Identifier> discs) {
		this.songs = songs;
		this.categories = categories;
		this.discs = discs;
	}

	@Override
	protected @NotNull OwoUIAdapter<FlowLayout> createAdapter() {
		return OwoUIAdapter.create(this, Containers::verticalFlow);
	}

	@Override
	protected void build(FlowLayout rootComponent) {
		rootComponent.surface(Surface.VANILLA_TRANSLUCENT).horizontalAlignment(HorizontalAlignment.CENTER).verticalAlignment(VerticalAlignment.CENTER);

		// Main containers
		FlowLayout mainPanel = Containers.verticalFlow(Sizing.fill(60), Sizing.content());
		mainPanel.surface(Surface.DARK_PANEL).padding(Insets.of(8)).horizontalAlignment(HorizontalAlignment.CENTER);

		// Top of the music player
		FlowLayout optionsPanel = Containers.horizontalFlow(Sizing.fill(100), Sizing.content());
		mainPanel.child(optionsPanel.margins(Insets.of(0).withBottom(2)));

		// Container for the scrolling
		FlowLayout songsContainer = Containers.verticalFlow(Sizing.fill(100), Sizing.content());

		// Dropdown with all categories
		DropdownComponent dropdown = categoriesDropdown(songsContainer);

		ButtonComponent dropdownButton = Components.button(Text.of("Filter"), button -> {
			if (dropdown.hasParent()) return;

			rootComponent.child(dropdown.positioning(Positioning.absolute(button.x(), button.y() + button.height())));
		});

		dropdown.mouseLeave().subscribe(() -> dropdown.closeWhenNotHovered(true));

		// Search function
		TextBoxComponent searchField = Components.textBox(Sizing.fill(60));

		String searchText = I18n.translate("text.screen.fred.music_player.search_field");

		searchField.setSuggestion(searchText);

		searchField.onChanged().subscribe(search -> {
			searchField.setSuggestion(search.isEmpty() ? searchText : "");

			// If search is empty, returns to the 'All songs' category
			if (search.isBlank() || search.isEmpty()) {
				songsContainer.clearChildren();
				updateList(songsContainer, songs);

				return;
			}

			String searchLower = search.toLowerCase();

			List<Identifier> result = songs.stream().filter(item -> I18n.translate(item.toString()).toLowerCase().contains(searchLower)).toList();

			songsContainer.clearChildren();
			updateList(songsContainer, result);
		});

		optionsPanel.child(searchField.margins(Insets.of(4)));
		optionsPanel.child(dropdownButton.margins(Insets.of(4)));

		updateList(songsContainer, songs);

		FlowLayout songsPanel = Containers.verticalFlow(Sizing.content(), Sizing.content());
		songsPanel.child(Containers.verticalScroll(Sizing.content(), Sizing.fill(60), songsContainer)).padding(Insets.of(5)).surface(Surface.flat(0x77000000).and(Surface.outline(0x77000000)));

		mainPanel.child(songsPanel);
		rootComponent.child(mainPanel);
	}

	DropdownComponent categoriesDropdown(final FlowLayout songsContainer) {
		DropdownComponent dropdown = Components.dropdown(Sizing.content());

		dropdown.button(Text.of("All songs"), button -> {
			songsContainer.clearChildren();
			updateList(songsContainer, songs);
		});

		dropdown.button(Text.of("Discs"), button -> {
			songsContainer.clearChildren();
			updateList(songsContainer, discs);
		});

		dropdown.button(Text.of("Categories"), button -> {
			songsContainer.clearChildren();
			updateList(songsContainer, categories);
		});

		return dropdown;
	}

	void updateList(final FlowLayout songsContainer, final List<Identifier> songsList) {
		for (Identifier song : songsList) {
			songsContainer.child(Components.button(Text.translatable(song.toString()), button -> {
				if (client == null) {
					return;
				}

				MusicControl.PlayMusic(client, song);
			}));
		}
	}
}