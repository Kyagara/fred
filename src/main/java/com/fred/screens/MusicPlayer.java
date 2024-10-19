package com.fred.screens;

import com.fred.keybinds.MusicControl;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.Selectable;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.*;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.Collections;
import java.util.List;
public class MusicPlayer extends Screen {
	private final List<Identifier> songs;

	private TextFieldWidget searchField;
	private ScrollableSongList songList;
	private GridWidget grid;

	private static final int widgetWidth = 250;
	private static final int searchFieldHeight = 20;

	public MusicPlayer(List<Identifier> songs) {
		super(Text.of("Music Player"));
		this.songs = songs;
	}

	@Override
	protected void init() {
		grid = new GridWidget();
		grid.getMainPositioner().margin(5);

		int containerWidth = widgetWidth + 20;
		int containerHeight = (int) ((double) this.height / 2.5) + searchFieldHeight + 20;
		int centerX = (this.width - containerWidth) / 2;
		int centerY = (this.height - containerHeight) / 2;

		grid.setPosition(centerX, centerY);

		// Search field
		searchField = new TextFieldWidget(this.textRenderer, 0, 0, widgetWidth, searchFieldHeight, Text.of(""));
		searchField.setChangedListener(this::onSearchChanged);
		searchField.setSuggestion("Search");

		this.setFocused(searchField);

		// Song list
		songList = new ScrollableSongList(this.client, widgetWidth, (int) ((double) this.height / 2.5), 0, 20);
		songList.setEntries(songs);

		grid.add(searchField, 1, 1);
		grid.add(songList, 2, 1);
		grid.refreshPositions();
		grid.forEachChild(this::addDrawableChild);
	}

	@Override
	public void render(DrawContext context, int mouseX, int mouseY, float delta) {
		int gridX = grid.getX();
		int gridY = grid.getY();

		context.fill(gridX, gridY, gridX + grid.getWidth(), gridY + grid.getHeight(), 0xFF2D2D2D);

		this.children().forEach(element -> {
			if (element instanceof Drawable drawable) {
				drawable.render(context, mouseX, mouseY, delta);
			}
		});
	}

	private void onSearchChanged(String searchText) {
		if (searchText.isEmpty()) {
			songList.setEntries(songs);
			searchField.setSuggestion("Search");
			return;
		}

		searchField.setSuggestion("");

		List<Identifier> filtered = songs.stream()
				.filter(song -> song.toString().toLowerCase().contains(searchText.toLowerCase()))
				.toList();

		songList.setEntries(filtered);
	}

	private static class ScrollableSongList extends ElementListWidget<ScrollableSongList.SongEntry> {
		public ScrollableSongList(MinecraftClient client, int width, int height, int y, int itemHeight) {
			super(client, width, height, y, itemHeight);
			this.setRenderHeader(false, 0);
		}

		public void setEntries(List<Identifier> songs) {
			this.clearEntries();

			if (songs.isEmpty()) {
				return;
			}

			for (Identifier song : songs) {
				this.addEntry(new SongEntry(song));
			}
		}

		class SongEntry extends ElementListWidget.Entry<SongEntry> {
			private final ButtonWidget button;

			public SongEntry(Identifier song) {
				this.button = ButtonWidget.builder(Text.translatable(song.toString()), button -> {
					MusicControl.PlayMusic(client, song);
				}).dimensions(0, 0, 215, 20).build();
			}

			@Override
			public List<? extends Selectable> selectableChildren() {
				return Collections.singletonList(button);
			}

			@Override
			public List<? extends Element> children() {
				return Collections.singletonList(button);
			}

			@Override
			public void render(DrawContext context, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean hovered, float tickDelta) {
				button.setPosition(x, y);
				button.render(context, mouseX, mouseY, tickDelta);
			}
		}
	}
}
