package net.machinemuse.powersuits.network;

import net.machinemuse.powersuits.client.gui.tinker.MuseGui;
import net.machinemuse.numina.network.MusePackager;
import net.machinemuse.numina.network.MusePacket;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.DataInputStream;

/**
 * Author: MachineMuse (Claire Semple)
 * Created: 12:28 PM, 5/6/13
 *
 * Ported to Java by lehjr on 11/14/16.
 */
public class MusePacketInventoryRefresh extends MusePacket {
    EntityPlayer player;
    int slot;
    ItemStack stack;

    public MusePacketInventoryRefresh(EntityPlayer player, int slot, ItemStack stack) {
        this.player = player;
        this.slot = slot;
        this.stack = stack;
    }

    @Override
    public MusePackager packager() {
        return getPackagerInstance();
    }

    @Override
    public void write() {
        writeInt(slot);
        writeItemStack(stack);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void handleClient(EntityPlayer player) {
        IInventory inventory = player.inventory;
        inventory.setInventorySlotContents(slot, stack);
        ((MuseGui)(Minecraft.getMinecraft().currentScreen)).refresh();
    }

    private static MusePacketInventoryRefreshPackager PACKAGERINSTANCE;
    public static MusePacketInventoryRefreshPackager getPackagerInstance() {
        if (PACKAGERINSTANCE == null) {
            synchronized (MusePacketInventoryRefreshPackager.class) {
                if (PACKAGERINSTANCE == null) {
                    PACKAGERINSTANCE = new MusePacketInventoryRefreshPackager();
                }
            }
        }
        return PACKAGERINSTANCE;
    }

    public static class MusePacketInventoryRefreshPackager extends MusePackager {
        @Override
        public MusePacket read(DataInputStream datain, EntityPlayer player) {
            int itemSlot = readInt(datain);
            ItemStack stack = readItemStack(datain);
            return new MusePacketInventoryRefresh(player, itemSlot, stack);
        }
    }
}
