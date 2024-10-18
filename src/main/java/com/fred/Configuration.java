package com.fred;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public final class Configuration {
	private static String customWindowTitle = "Minecraft";

	private static boolean enableInventoryMovement = true;

	private static boolean enableChangingSpyglassFOV = true;

	private static boolean enableXPBottleMechanic = true;
	private static int xpForXPBottle = 10;

	private static boolean clearChatOnLeave = false;

	private static boolean enableChatMessageSound = true;
	private static float chatMessageVolume = 0.15F;
	private static float chatMessagePitch = 3.0F;

	private static boolean enableChatTypingSound = true;
	private static float chatTypingVolume = 0.10F;
	private static float chatTypingPitch = 5.0F;

	private static boolean enableTheRockBlock = false;
	private static boolean enableTrumpet = false;

	private static int musicMinDelay = 6000;
	private static int musicMaxDelay = 9000;

	public static void load(String configPath) {
		File config = new File(configPath);
		if (!config.exists()) {
			createDefaultConfig(configPath);
		}

		try (FileReader reader = new FileReader(config)) {
			JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();

			customWindowTitle = json.has("customWindowTitle") ? json.get("customWindowTitle").getAsString() : customWindowTitle;
			enableInventoryMovement = json.has("enableInventoryMovement") ? json.get("enableInventoryMovement").getAsBoolean() : enableInventoryMovement;
			enableChangingSpyglassFOV = json.has("enableChangingSpyglassFOV") ? json.get("enableChangingSpyglassFOV").getAsBoolean() : enableChangingSpyglassFOV;
			enableXPBottleMechanic = json.has("enableXPBottleMechanic") ? json.get("enableXPBottleMechanic").getAsBoolean() : enableXPBottleMechanic;
			xpForXPBottle = json.has("xpForXPBottle") ? json.get("xpForXPBottle").getAsInt() : xpForXPBottle;
			clearChatOnLeave = json.has("clearChatOnLeave") ? json.get("clearChatOnLeave").getAsBoolean() : clearChatOnLeave;
			enableChatMessageSound = json.has("enableChatMessageSound") ? json.get("enableChatMessageSound").getAsBoolean() : enableChatMessageSound;
			chatMessageVolume = json.has("chatMessageVolume") ? json.get("chatMessageVolume").getAsFloat() : chatMessageVolume;
			chatMessagePitch = json.has("chatMessagePitch") ? json.get("chatMessagePitch").getAsFloat() : chatMessagePitch;
			enableChatTypingSound = json.has("enableChatTypingSound") ? json.get("enableChatTypingSound").getAsBoolean() : enableChatTypingSound;
			chatTypingVolume = json.has("chatTypingVolume") ? json.get("chatTypingVolume").getAsFloat() : chatTypingVolume;
			chatTypingPitch = json.has("chatTypingPitch") ? json.get("chatTypingPitch").getAsFloat() : chatTypingPitch;
			enableTheRockBlock = json.has("enableTheRockBlock") ? json.get("enableTheRockBlock").getAsBoolean() : enableTheRockBlock;
			enableTrumpet = json.has("enableTrumpet") ? json.get("enableTrumpet").getAsBoolean() : enableTrumpet;
			musicMinDelay = json.has("musicMinDelay") ? json.get("musicMinDelay").getAsInt() : musicMinDelay;
			musicMaxDelay = json.has("musicMaxDelay") ? json.get("musicMaxDelay").getAsInt() : musicMaxDelay;

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static void createDefaultConfig(String configPath) {
		JsonObject json = new JsonObject();
		json.addProperty("customWindowTitle", customWindowTitle);
		json.addProperty("enableInventoryMovement", enableInventoryMovement);
		json.addProperty("enableChangingSpyglassFOV", enableChangingSpyglassFOV);
		json.addProperty("enableXPBottleMechanic", enableXPBottleMechanic);
		json.addProperty("xpForXPBottle", xpForXPBottle);
		json.addProperty("clearChatOnLeave", clearChatOnLeave);
		json.addProperty("enableChatMessageSound", enableChatMessageSound);
		json.addProperty("chatMessageVolume", chatMessageVolume);
		json.addProperty("chatMessagePitch", chatMessagePitch);
		json.addProperty("enableChatTypingSound", enableChatTypingSound);
		json.addProperty("chatTypingVolume", chatTypingVolume);
		json.addProperty("chatTypingPitch", chatTypingPitch);
		json.addProperty("enableTheRockBlock", enableTheRockBlock);
		json.addProperty("enableTrumpet", enableTrumpet);
		json.addProperty("musicMinDelay", musicMinDelay);
		json.addProperty("musicMaxDelay", musicMaxDelay);

		try (FileWriter writer = new FileWriter(configPath)) {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			gson.toJson(json, writer);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static String customWindowTitle() {
		return customWindowTitle;
	}

	public static boolean enableInventoryMovement() {
		return enableInventoryMovement;
	}

	public static boolean enableChangingSpyglassFOV() {
		return enableChangingSpyglassFOV;
	}

	public static boolean enableXPBottleMechanic() {
		return enableXPBottleMechanic;
	}

	public static int xpForXPBottle() {
		return xpForXPBottle;
	}

	public static boolean clearChatOnLeave() {
		return clearChatOnLeave;
	}

	public static boolean enableChatMessageSound() {
		return enableChatMessageSound;
	}

	public static float chatMessageVolume() {
		return chatMessageVolume;
	}

	public static float chatMessagePitch() {
		return chatMessagePitch;
	}

	public static boolean enableChatTypingSound() {
		return enableChatTypingSound;
	}

	public static float chatTypingVolume() {
		return chatTypingVolume;
	}

	public static float chatTypingPitch() {
		return chatTypingPitch;
	}

	public static boolean enableTheRockBlock() {
		return enableTheRockBlock;
	}

	public static boolean enableTrumpet() {
		return enableTrumpet;
	}

	public static int musicMinDelay() {
		return musicMinDelay;
	}

	public static int musicMaxDelay() {
		return musicMaxDelay;
	}
}
