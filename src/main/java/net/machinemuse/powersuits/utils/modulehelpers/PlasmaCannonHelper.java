package net.machinemuse.powersuits.utils.modulehelpers;

import net.machinemuse.powersuits.api.module.ModuleManager;
import net.machinemuse.powersuits.powermodule.weapon.PlasmaCannonModule;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/**
 * Created by leon on 4/9/17.
 */
public class PlasmaCannonHelper {
    public static int getPlayerPlasma(EntityPlayer player) {
        ItemStack powerfist = player.getHeldItemMainhand();
        if (powerfist != null && player.isHandActive() && ModuleManager.INSTANCE.itemHasActiveModule(powerfist, PlasmaCannonModule.MODULE_PLASMA_CANNON)) {
            int actualCount = (-player.getItemInUseCount() + 72000);
            return (actualCount > 50 ? 50 : actualCount) * 2;
        }
        return 0;
    }

    public static int getMaxPlasma(EntityPlayer player) {
        ItemStack powerfist = player.getHeldItemMainhand();
        if (powerfist != null && player.isHandActive() && ModuleManager.INSTANCE.itemHasActiveModule(powerfist, PlasmaCannonModule.MODULE_PLASMA_CANNON)) {
            return 100;
        }
        return 0;
    }
}