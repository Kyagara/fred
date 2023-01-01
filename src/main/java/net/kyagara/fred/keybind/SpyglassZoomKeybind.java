// https://github.com/ricksouth/serilum-mc-mods/tree/master/sources-fabric/Vanilla%20Zoom%20(Fabric)

package net.kyagara.fred.keybind;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SpyglassItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class SpyglassZoomKeybind {
    private static boolean zoom = false;
    private static ItemStack previousStack = null;

    public static float spyglassFOV = 0.1F;

    public static void CheckForZoomKeybind(MinecraftClient client) {
        boolean forceRelease = false;

        if (zoom) {
            client.options.useKey.setPressed(true);

            if (!(client.player.getStackInHand(Hand.OFF_HAND).getItem() instanceof SpyglassItem)) {
                forceRelease = true;
            }
        }

        if (ModKeybinds.ZOOM_KEYBIND.isPressed() && !forceRelease) {
            if (!zoom) {
                zoom = true;
                previousStack = client.player.getStackInHand(Hand.OFF_HAND).copy();

                ItemStack stack = new ItemStack(Items.SPYGLASS);
                client.player.setStackInHand(Hand.OFF_HAND, stack);
            }
        } else {
            if (zoom) {
                zoom = false;
                client.options.useKey.setPressed(false);

                if (previousStack != null) {
                    client.player.setStackInHand(Hand.OFF_HAND, previousStack.copy());
                    previousStack = null;
                }
            }
        }
    }

    public static TypedActionResult<ItemStack> onUse(PlayerEntity player, World world, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);

        if (zoom) {
            if (!(stack.getItem() instanceof SpyglassItem)) {
                return TypedActionResult.fail(stack);
            }
        }

        return TypedActionResult.pass(stack);
    }

    public static ActionResult onEntityInteract(PlayerEntity player, World world, Hand hand,
            Entity entity, EntityHitResult hitResult) {

        if (zoom) {
            return ActionResult.FAIL;
        }

        return ActionResult.PASS;
    }
}