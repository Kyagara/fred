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

	@RangeConstraint(min = 0, max = 10000)
	public int xpForXPBottle = 10;

	@SectionHeader("chatCategory")

	public boolean clearChatOnLeave = false;

	public boolean enableChatMessageSound = true;

	@RangeConstraint(min = 0.0F, max = 2.0F)
	public float chatMessageVolume = 0.15F;

	@RangeConstraint(min = -5.0F, max = 5.0F)
	public float chatMessagePitch = 3.0F;

	public boolean enableChatTypingSound = true;

	@RangeConstraint(min = 0.0F, max = 2.0F)
	public float chatTypingVolume = 0.10F;

	@RangeConstraint(min = -5.0F, max = 5.0F)
	public float chatTypingPitch = 5.0F;

	@SectionHeader("itemCategory")

	@RestartRequired
	public boolean enableTheRockBlock = false;

	@RestartRequired
	public boolean enableTrumpet = false;

	@SectionHeader("audioCategory")

	@RangeConstraint(min = 0.0F, max = 100000)
	public int musicMinDelay = 6000;

	@RangeConstraint(min = 0.0F, max = 100000)
	public int musicMaxDelay = 9000;
}
