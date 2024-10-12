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

    private final Option<java.lang.String> customWindowTitle = this.optionForKey(this.keys.customWindowTitle);
    private final Option<java.lang.Boolean> enableInventoryMovement = this.optionForKey(this.keys.enableInventoryMovement);
    private final Option<java.lang.Boolean> enableZoom = this.optionForKey(this.keys.enableZoom);
    private final Option<java.lang.Boolean> enableChangingSpyglassFOV = this.optionForKey(this.keys.enableChangingSpyglassFOV);
    private final Option<java.lang.Boolean> enableXPBottleMechanic = this.optionForKey(this.keys.enableXPBottleMechanic);
    private final Option<java.lang.Integer> xpForXPBottle = this.optionForKey(this.keys.xpForXPBottle);
    private final Option<java.lang.Boolean> enableJoinChatMessage = this.optionForKey(this.keys.enableJoinChatMessage);
    private final Option<java.lang.Boolean> clearChatOnLeave = this.optionForKey(this.keys.clearChatOnLeave);
    private final Option<java.lang.Boolean> enableChatMessageSound = this.optionForKey(this.keys.enableChatMessageSound);
    private final Option<java.lang.Float> chatMessageVolume = this.optionForKey(this.keys.chatMessageVolume);
    private final Option<java.lang.Float> chatMessagePitch = this.optionForKey(this.keys.chatMessagePitch);
    private final Option<java.lang.Boolean> enableTheRockBlock = this.optionForKey(this.keys.enableTheRockBlock);
    private final Option<java.lang.Boolean> enableTrumpet = this.optionForKey(this.keys.enableTrumpet);
    private final Option<java.lang.Integer> musicMinDelay = this.optionForKey(this.keys.musicMinDelay);
    private final Option<java.lang.Integer> musicMaxDelay = this.optionForKey(this.keys.musicMaxDelay);

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

    public boolean clearChatOnLeave() {
        return clearChatOnLeave.value();
    }

    public void clearChatOnLeave(boolean value) {
        clearChatOnLeave.set(value);
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

    public boolean enableTheRockBlock() {
        return enableTheRockBlock.value();
    }

    public void enableTheRockBlock(boolean value) {
        enableTheRockBlock.set(value);
    }

    public boolean enableTrumpet() {
        return enableTrumpet.value();
    }

    public void enableTrumpet(boolean value) {
        enableTrumpet.set(value);
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


    public static class Keys {
        public final Option.Key customWindowTitle = new Option.Key("customWindowTitle");
        public final Option.Key enableInventoryMovement = new Option.Key("enableInventoryMovement");
        public final Option.Key enableZoom = new Option.Key("enableZoom");
        public final Option.Key enableChangingSpyglassFOV = new Option.Key("enableChangingSpyglassFOV");
        public final Option.Key enableXPBottleMechanic = new Option.Key("enableXPBottleMechanic");
        public final Option.Key xpForXPBottle = new Option.Key("xpForXPBottle");
        public final Option.Key clearChatOnLeave = new Option.Key("clearChatOnLeave");
        public final Option.Key enableChatMessageSound = new Option.Key("enableChatMessageSound");
        public final Option.Key chatMessageVolume = new Option.Key("chatMessageVolume");
        public final Option.Key chatMessagePitch = new Option.Key("chatMessagePitch");
        public final Option.Key enableTheRockBlock = new Option.Key("enableTheRockBlock");
        public final Option.Key enableTrumpet = new Option.Key("enableTrumpet");
        public final Option.Key musicMinDelay = new Option.Key("musicMinDelay");
        public final Option.Key musicMaxDelay = new Option.Key("musicMaxDelay");
    }
}

