package net.kyagara.fred.mixin.client.accessor;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil.Key;

@Environment(EnvType.CLIENT)
@Mixin(KeyBinding.class)
public abstract interface KeyBindingAccessor {
    @Accessor("boundKey")
    public Key getBoundKey();
}