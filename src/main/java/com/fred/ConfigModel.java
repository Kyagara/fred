package com.fred;

import io.wispforest.owo.config.annotation.*;

@SuppressWarnings("unused")
@Modmenu(modId = "fred")
@Config(name = "fred", wrapperName = "FredConfig")
public class ConfigModel {
	@SectionHeader("miscellaneousCategory")

	public String customWindowTitle = "Minecraft";

	@SectionHeader("mechanicCategory")

	public boolean enableInventoryMovement = true;

	public boolean enableZoom = true;

	public boolean enableChangingSpyglassFOV = true;

	public boolean enableXPBottleMechanic = true;

	public int xpForXPBottle = 10;

	@SectionHeader("chatCategory")

	public boolean clearChatOnLeave = false;

	public boolean enableChatMessageSound = true;

	public float chatMessageVolume = 0.15F;

	public float chatMessagePitch = 3.0F;

	public boolean enableChatTypingSound = true;

	public float chatTypingVolume = 0.10F;

	public float chatTypingPitch = 5.0F;

	@SectionHeader("itemCategory")

	@RestartRequired
	public boolean enableTheRockBlock = false;

	@RestartRequired
	public boolean enableTrumpet = false;

	@SectionHeader("audioCategory")

	public int musicMinDelay = 6000;

	public int musicMaxDelay = 9000;
}
