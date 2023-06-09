package net.kyagara.fred;

import blue.endless.jankson.Jankson;
import io.wispforest.owo.config.ConfigWrapper;
import io.wispforest.owo.config.Option;
import io.wispforest.owo.util.Observable;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class FredConfig extends ConfigWrapper<net.kyagara.fred.FredConfigModel> {

    public final Keys keys = new Keys();

    private final Option<java.lang.Boolean> enableCustomWindowTitle = this.optionForKey(this.keys.enableCustomWindowTitle);
    private final Option<java.lang.String> customWindowTitle = this.optionForKey(this.keys.customWindowTitle);
    private final Option<java.lang.Boolean> enableInventoryMovement = this.optionForKey(this.keys.enableInventoryMovement);
    private final Option<java.lang.Boolean> disableInventoryBackground = this.optionForKey(this.keys.disableInventoryBackground);
    private final Option<java.lang.Boolean> enableZoom = this.optionForKey(this.keys.enableZoom);
    private final Option<java.lang.Boolean> enableChangingSpyglassFOV = this.optionForKey(this.keys.enableChangingSpyglassFOV);
    private final Option<java.lang.Boolean> enableXPBottleMechanic = this.optionForKey(this.keys.enableXPBottleMechanic);
    private final Option<java.lang.Integer> xpForXPBottle = this.optionForKey(this.keys.xpForXPBottle);
    private final Option<java.lang.Boolean> enableLinkItemInChat = this.optionForKey(this.keys.enableLinkItemInChat);
    private final Option<java.lang.Boolean> enableJoinChatMessage = this.optionForKey(this.keys.enableJoinChatMessage);
    private final Option<java.lang.Boolean> clearChatOnLeave = this.optionForKey(this.keys.clearChatOnLeave);
    private final Option<java.lang.Boolean> enableChatSessionSeparator = this.optionForKey(this.keys.enableChatSessionSeparator);
    private final Option<java.lang.String> chatSessionSeparator = this.optionForKey(this.keys.chatSessionSeparator);
    private final Option<io.wispforest.owo.ui.core.Color> chatSessionSeparatorColor = this.optionForKey(this.keys.chatSessionSeparatorColor);
    private final Option<java.lang.Boolean> enableChatMessageSound = this.optionForKey(this.keys.enableChatMessageSound);
    private final Option<java.lang.Float> chatMessageVolume = this.optionForKey(this.keys.chatMessageVolume);
    private final Option<java.lang.Float> chatMessagePitch = this.optionForKey(this.keys.chatMessagePitch);
    private final Option<java.lang.Boolean> disableAdvancementToasts = this.optionForKey(this.keys.disableAdvancementToasts);
    private final Option<java.lang.Boolean> disableRecipeToasts = this.optionForKey(this.keys.disableRecipeToasts);
    private final Option<java.lang.Boolean> disableTutorialToasts = this.optionForKey(this.keys.disableTutorialToasts);
    private final Option<java.lang.Boolean> disableSystemToasts = this.optionForKey(this.keys.disableSystemToasts);
    private final Option<java.lang.Boolean> enableTheRockBlock = this.optionForKey(this.keys.enableTheRockBlock);
    private final Option<java.lang.Boolean> enableReiFumoBlock = this.optionForKey(this.keys.enableReiFumoBlock);
    private final Option<java.lang.Boolean> enableTrumpet = this.optionForKey(this.keys.enableTrumpet);
    private final Option<java.lang.Boolean> enableMyMovieSFX = this.optionForKey(this.keys.enableMyMovieSFX);
    private final Option<java.lang.Integer> musicMinDelay = this.optionForKey(this.keys.musicMinDelay);
    private final Option<java.lang.Integer> musicMaxDelay = this.optionForKey(this.keys.musicMaxDelay);
    private final Option<java.lang.Boolean> enableRollCommand = this.optionForKey(this.keys.enableRollCommand);
    private final Option<java.lang.Boolean> enableQuotesCommand = this.optionForKey(this.keys.enableQuotesCommand);
    private final Option<java.lang.Boolean> enableMagicBallCommand = this.optionForKey(this.keys.enableMagicBallCommand);
    private final Option<java.util.List<java.lang.String>> magicBallAnswersList = this.optionForKey(this.keys.magicBallAnswersList);
    private final Option<java.util.List<java.lang.String>> quotesList = this.optionForKey(this.keys.quotesList);

    private FredConfig() {
        super(net.kyagara.fred.FredConfigModel.class);
    }

    private FredConfig(Consumer<Jankson.Builder> janksonBuilder) {
        super(net.kyagara.fred.FredConfigModel.class, janksonBuilder);
    }

    public static FredConfig createAndLoad() {
        var wrapper = new FredConfig();
        wrapper.load();
        return wrapper;
    }

    public static FredConfig createAndLoad(Consumer<Jankson.Builder> janksonBuilder) {
        var wrapper = new FredConfig(janksonBuilder);
        wrapper.load();
        return wrapper;
    }

    public boolean enableCustomWindowTitle() {
        return enableCustomWindowTitle.value();
    }

    public void enableCustomWindowTitle(boolean value) {
        enableCustomWindowTitle.set(value);
    }

    public java.lang.String customWindowTitle() {
        return customWindowTitle.value();
    }

    public void customWindowTitle(java.lang.String value) {
        customWindowTitle.set(value);
    }

    public boolean enableInventoryMovement() {
        return enableInventoryMovement.value();
    }

    public void enableInventoryMovement(boolean value) {
        enableInventoryMovement.set(value);
    }

    public boolean disableInventoryBackground() {
        return disableInventoryBackground.value();
    }

    public void disableInventoryBackground(boolean value) {
        disableInventoryBackground.set(value);
    }

    public boolean enableZoom() {
        return enableZoom.value();
    }

    public void enableZoom(boolean value) {
        enableZoom.set(value);
    }

    public boolean enableChangingSpyglassFOV() {
        return enableChangingSpyglassFOV.value();
    }

    public void enableChangingSpyglassFOV(boolean value) {
        enableChangingSpyglassFOV.set(value);
    }

    public boolean enableXPBottleMechanic() {
        return enableXPBottleMechanic.value();
    }

    public void enableXPBottleMechanic(boolean value) {
        enableXPBottleMechanic.set(value);
    }

    public int xpForXPBottle() {
        return xpForXPBottle.value();
    }

    public void xpForXPBottle(int value) {
        xpForXPBottle.set(value);
    }

    public boolean enableLinkItemInChat() {
        return enableLinkItemInChat.value();
    }

    public void enableLinkItemInChat(boolean value) {
        enableLinkItemInChat.set(value);
    }

    public boolean enableJoinChatMessage() {
        return enableJoinChatMessage.value();
    }

    public void enableJoinChatMessage(boolean value) {
        enableJoinChatMessage.set(value);
    }

    public boolean clearChatOnLeave() {
        return clearChatOnLeave.value();
    }

    public void clearChatOnLeave(boolean value) {
        clearChatOnLeave.set(value);
    }

    public boolean enableChatSessionSeparator() {
        return enableChatSessionSeparator.value();
    }

    public void enableChatSessionSeparator(boolean value) {
        enableChatSessionSeparator.set(value);
    }

    public java.lang.String chatSessionSeparator() {
        return chatSessionSeparator.value();
    }

    public void chatSessionSeparator(java.lang.String value) {
        chatSessionSeparator.set(value);
    }

    public io.wispforest.owo.ui.core.Color chatSessionSeparatorColor() {
        return chatSessionSeparatorColor.value();
    }

    public void chatSessionSeparatorColor(io.wispforest.owo.ui.core.Color value) {
        chatSessionSeparatorColor.set(value);
    }

    public boolean enableChatMessageSound() {
        return enableChatMessageSound.value();
    }

    public void enableChatMessageSound(boolean value) {
        enableChatMessageSound.set(value);
    }

    public float chatMessageVolume() {
        return chatMessageVolume.value();
    }

    public void chatMessageVolume(float value) {
        chatMessageVolume.set(value);
    }

    public float chatMessagePitch() {
        return chatMessagePitch.value();
    }

    public void chatMessagePitch(float value) {
        chatMessagePitch.set(value);
    }

    public boolean disableAdvancementToasts() {
        return disableAdvancementToasts.value();
    }

    public void disableAdvancementToasts(boolean value) {
        disableAdvancementToasts.set(value);
    }

    public boolean disableRecipeToasts() {
        return disableRecipeToasts.value();
    }

    public void disableRecipeToasts(boolean value) {
        disableRecipeToasts.set(value);
    }

    public boolean disableTutorialToasts() {
        return disableTutorialToasts.value();
    }

    public void disableTutorialToasts(boolean value) {
        disableTutorialToasts.set(value);
    }

    public boolean disableSystemToasts() {
        return disableSystemToasts.value();
    }

    public void disableSystemToasts(boolean value) {
        disableSystemToasts.set(value);
    }

    public boolean enableTheRockBlock() {
        return enableTheRockBlock.value();
    }

    public void enableTheRockBlock(boolean value) {
        enableTheRockBlock.set(value);
    }

    public boolean enableReiFumoBlock() {
        return enableReiFumoBlock.value();
    }

    public void enableReiFumoBlock(boolean value) {
        enableReiFumoBlock.set(value);
    }

    public boolean enableTrumpet() {
        return enableTrumpet.value();
    }

    public void enableTrumpet(boolean value) {
        enableTrumpet.set(value);
    }

    public boolean enableMyMovieSFX() {
        return enableMyMovieSFX.value();
    }

    public void enableMyMovieSFX(boolean value) {
        enableMyMovieSFX.set(value);
    }

    public int musicMinDelay() {
        return musicMinDelay.value();
    }

    public void musicMinDelay(int value) {
        musicMinDelay.set(value);
    }

    public int musicMaxDelay() {
        return musicMaxDelay.value();
    }

    public void musicMaxDelay(int value) {
        musicMaxDelay.set(value);
    }

    public boolean enableRollCommand() {
        return enableRollCommand.value();
    }

    public void enableRollCommand(boolean value) {
        enableRollCommand.set(value);
    }

    public boolean enableQuotesCommand() {
        return enableQuotesCommand.value();
    }

    public void enableQuotesCommand(boolean value) {
        enableQuotesCommand.set(value);
    }

    public boolean enableMagicBallCommand() {
        return enableMagicBallCommand.value();
    }

    public void enableMagicBallCommand(boolean value) {
        enableMagicBallCommand.set(value);
    }

    public java.util.List<java.lang.String> magicBallAnswersList() {
        return magicBallAnswersList.value();
    }

    public void magicBallAnswersList(java.util.List<java.lang.String> value) {
        magicBallAnswersList.set(value);
    }

    public java.util.List<java.lang.String> quotesList() {
        return quotesList.value();
    }

    public void quotesList(java.util.List<java.lang.String> value) {
        quotesList.set(value);
    }


    public static class Keys {
        public final Option.Key enableCustomWindowTitle = new Option.Key("enableCustomWindowTitle");
        public final Option.Key customWindowTitle = new Option.Key("customWindowTitle");
        public final Option.Key enableInventoryMovement = new Option.Key("enableInventoryMovement");
        public final Option.Key disableInventoryBackground = new Option.Key("disableInventoryBackground");
        public final Option.Key enableZoom = new Option.Key("enableZoom");
        public final Option.Key enableChangingSpyglassFOV = new Option.Key("enableChangingSpyglassFOV");
        public final Option.Key enableXPBottleMechanic = new Option.Key("enableXPBottleMechanic");
        public final Option.Key xpForXPBottle = new Option.Key("xpForXPBottle");
        public final Option.Key enableLinkItemInChat = new Option.Key("enableLinkItemInChat");
        public final Option.Key enableJoinChatMessage = new Option.Key("enableJoinChatMessage");
        public final Option.Key clearChatOnLeave = new Option.Key("clearChatOnLeave");
        public final Option.Key enableChatSessionSeparator = new Option.Key("enableChatSessionSeparator");
        public final Option.Key chatSessionSeparator = new Option.Key("chatSessionSeparator");
        public final Option.Key chatSessionSeparatorColor = new Option.Key("chatSessionSeparatorColor");
        public final Option.Key enableChatMessageSound = new Option.Key("enableChatMessageSound");
        public final Option.Key chatMessageVolume = new Option.Key("chatMessageVolume");
        public final Option.Key chatMessagePitch = new Option.Key("chatMessagePitch");
        public final Option.Key disableAdvancementToasts = new Option.Key("disableAdvancementToasts");
        public final Option.Key disableRecipeToasts = new Option.Key("disableRecipeToasts");
        public final Option.Key disableTutorialToasts = new Option.Key("disableTutorialToasts");
        public final Option.Key disableSystemToasts = new Option.Key("disableSystemToasts");
        public final Option.Key enableTheRockBlock = new Option.Key("enableTheRockBlock");
        public final Option.Key enableReiFumoBlock = new Option.Key("enableReiFumoBlock");
        public final Option.Key enableTrumpet = new Option.Key("enableTrumpet");
        public final Option.Key enableMyMovieSFX = new Option.Key("enableMyMovieSFX");
        public final Option.Key musicMinDelay = new Option.Key("musicMinDelay");
        public final Option.Key musicMaxDelay = new Option.Key("musicMaxDelay");
        public final Option.Key enableRollCommand = new Option.Key("enableRollCommand");
        public final Option.Key enableQuotesCommand = new Option.Key("enableQuotesCommand");
        public final Option.Key enableMagicBallCommand = new Option.Key("enableMagicBallCommand");
        public final Option.Key magicBallAnswersList = new Option.Key("magicBallAnswersList");
        public final Option.Key quotesList = new Option.Key("quotesList");
    }
}

