package net.kyagara.fred;

import io.wispforest.owo.config.ConfigWrapper;
import io.wispforest.owo.config.Option;
import io.wispforest.owo.util.Observable;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class FredConfig extends ConfigWrapper<net.kyagara.fred.FredConfigModel> {

    private final Option<java.lang.Boolean> enableCustomWindowTitle = this.optionForKey(new Option.Key("enableCustomWindowTitle"));
    private final Option<java.lang.String> customWindowTitle = this.optionForKey(new Option.Key("customWindowTitle"));
    private final Option<java.lang.Boolean> enableInventoryMovement = this.optionForKey(new Option.Key("enableInventoryMovement"));
    private final Option<java.lang.Boolean> disableInventoryBackground = this.optionForKey(new Option.Key("disableInventoryBackground"));
    private final Option<java.lang.Boolean> enableZoom = this.optionForKey(new Option.Key("enableZoom"));
    private final Option<java.lang.Boolean> enableChangingSpyglassFOV = this.optionForKey(new Option.Key("enableChangingSpyglassFOV"));
    private final Option<java.lang.Boolean> enableXPBottleMechanic = this.optionForKey(new Option.Key("enableXPBottleMechanic"));
    private final Option<java.lang.Integer> xpForXPBottle = this.optionForKey(new Option.Key("xpForXPBottle"));
    private final Option<java.lang.Boolean> enableLinkItemInChat = this.optionForKey(new Option.Key("enableLinkItemInChat"));
    private final Option<java.lang.Boolean> enableJoinChatMessage = this.optionForKey(new Option.Key("enableJoinChatMessage"));
    private final Option<java.lang.Boolean> clearChatOnLeave = this.optionForKey(new Option.Key("clearChatOnLeave"));
    private final Option<java.lang.Boolean> enableChatSessionSeparator = this.optionForKey(new Option.Key("enableChatSessionSeparator"));
    private final Option<java.lang.String> chatSessionSeparator = this.optionForKey(new Option.Key("chatSessionSeparator"));
    private final Option<java.lang.String> chatSessionSeparatorColor = this.optionForKey(new Option.Key("chatSessionSeparatorColor"));
    private final Option<java.lang.Boolean> enableChatMessageSound = this.optionForKey(new Option.Key("enableChatMessageSound"));
    private final Option<java.lang.Float> chatMessageVolume = this.optionForKey(new Option.Key("chatMessageVolume"));
    private final Option<java.lang.Float> chatMessagePitch = this.optionForKey(new Option.Key("chatMessagePitch"));
    private final Option<java.lang.Boolean> disableAdvancementToasts = this.optionForKey(new Option.Key("disableAdvancementToasts"));
    private final Option<java.lang.Boolean> disableRecipeToasts = this.optionForKey(new Option.Key("disableRecipeToasts"));
    private final Option<java.lang.Boolean> disableTutorialToasts = this.optionForKey(new Option.Key("disableTutorialToasts"));
    private final Option<java.lang.Boolean> disableSystemToasts = this.optionForKey(new Option.Key("disableSystemToasts"));
    private final Option<java.lang.Boolean> enableTheRockBlock = this.optionForKey(new Option.Key("enableTheRockBlock"));
    private final Option<java.lang.Boolean> enableReiFumoBlock = this.optionForKey(new Option.Key("enableReiFumoBlock"));
    private final Option<java.lang.Boolean> enableTrumpet = this.optionForKey(new Option.Key("enableTrumpet"));
    private final Option<java.lang.Boolean> enableMyMovieSFX = this.optionForKey(new Option.Key("enableMyMovieSFX"));
    private final Option<java.lang.Integer> musicMinDelay = this.optionForKey(new Option.Key("musicMinDelay"));
    private final Option<java.lang.Integer> musicMaxDelay = this.optionForKey(new Option.Key("musicMaxDelay"));
    private final Option<java.lang.Boolean> enableRollCommand = this.optionForKey(new Option.Key("enableRollCommand"));
    private final Option<java.lang.Boolean> enableQuotesCommand = this.optionForKey(new Option.Key("enableQuotesCommand"));
    private final Option<java.lang.Boolean> enableMagicBallCommand = this.optionForKey(new Option.Key("enableMagicBallCommand"));
    private final Option<java.util.List<java.lang.String>> magicBallAnswersList = this.optionForKey(new Option.Key("magicBallAnswersList"));
    private final Option<java.util.List<java.lang.String>> quotesList = this.optionForKey(new Option.Key("quotesList"));

    private FredConfig() {
        super(net.kyagara.fred.FredConfigModel.class);
    }

    public static FredConfig createAndLoad() {
        var wrapper = new FredConfig();
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

    public java.lang.String chatSessionSeparatorColor() {
        return chatSessionSeparatorColor.value();
    }

    public void chatSessionSeparatorColor(java.lang.String value) {
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




}

