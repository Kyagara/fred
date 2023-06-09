package net.kyagara.fred;

import io.wispforest.owo.config.annotation.*;
import io.wispforest.owo.ui.core.Color;

import java.util.List;

@SuppressWarnings("unused")
@Modmenu(modId = "fred")
@Config(name = "fred", wrapperName = "FredConfig")
public class FredConfigModel {
	@SectionHeader("miscellaneousCategory")

	public boolean enableCustomWindowTitle = true;

	public String customWindowTitle = "Minecraft";

	@SectionHeader("mechanicCategory")

	public boolean enableInventoryMovement = true;

	public boolean disableInventoryBackground = false;

	public boolean enableZoom = true;

	public boolean enableChangingSpyglassFOV = true;

	public boolean enableXPBottleMechanic = true;

	@RangeConstraint(min = 0, max = 1000)
	public int xpForXPBottle = 10;

	@SectionHeader("chatCategory")

	public boolean enableLinkItemInChat = true;

	public boolean enableJoinChatMessage = true;

	public boolean clearChatOnLeave = false;

	public boolean enableChatSessionSeparator = true;

	public String chatSessionSeparator = "------------------------------------";

	public Color chatSessionSeparatorColor = Color.ofRgb(0x55FFFF);

	public boolean enableChatMessageSound = true;

	@RangeConstraint(min = 0.0F, max = 2.0F)
	public float chatMessageVolume = 0.15F;

	@RangeConstraint(min = 0.0F, max = 5.0F)
	public float chatMessagePitch = 3.0F;

	@SectionHeader("toastCategory")

	public boolean disableAdvancementToasts = true;

	public boolean disableRecipeToasts = true;

	public boolean disableTutorialToasts = true;

	public boolean disableSystemToasts = true;

	@SectionHeader("itemCategory")

	@RestartRequired
	public boolean enableTheRockBlock = false;

	@RestartRequired
	public boolean enableReiFumoBlock = false;

	@RestartRequired
	public boolean enableTrumpet = false;

	@SectionHeader("audioCategory")

	@RestartRequired
	public boolean enableMyMovieSFX = false;

	@RangeConstraint(min = 0.0F, max = 24000)
	public int musicMinDelay = 7000;

	@RangeConstraint(min = 0.0F, max = 24000)
	public int musicMaxDelay = 9000;

	@SectionHeader("commandCategory")

	@RestartRequired
	public boolean enableRollCommand = false;

	@RestartRequired
	public boolean enableQuotesCommand = false;

	@RestartRequired
	public boolean enableMagicBallCommand = false;

	public List<String> magicBallAnswersList = List.of("It is certain.", "It is decidedly so.", "Without a doubt.", "Yes definitely.", "As I see it, yes.", "Most likely.", "Yes.", "Reply hazy, try again.", "Ask again later.", "I'm gonna leave you with that one.", "Better not tell you now.", "Cannot predict now.", "I guess so?", "Concentrate and ask again.", "Don't count on it.", "Oh hell no.", "lmao no.", "No.", "My reply is no.", "My sources say no.", "Very doubtful.");

	public List<String> quotesList = List.of("MACHINE! TRANS. RIGHTS. NOW.", "How can this be? Bested by this - this FUCKING GOPRO", "Machine, I'm dying. Please stop beatboxing.", "B-But council, the machine said a curse word in my CHRISTIAN MINECRAFT SERVER!", "gabriel gaming", "YOU ARE POGGERS. YOU ARE POGCHAMP. YOU ARE NOT CRINGE.", "MACHINE. I'M GOING TO POST CRINGE IN GENERAL.", "IMPOSSIBLE. HERSHEY. UNSPEAKABLE, HERSHEY. HERSHEY.", "MAY YOUR VOWS BE MANY, AND YOUR DAYS FEW.");
}