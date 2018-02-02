package net.machinemuse.powersuits.common.items.modules.tool;

import net.machinemuse.numina.api.item.IModularItem;
import net.machinemuse.numina.api.module.ModuleManager;
import net.machinemuse.numina.api.module.IRightClickModule;
import net.machinemuse.powersuits.client.event.MuseIcon;
import net.machinemuse.powersuits.common.items.modules.PowerModuleBase;
import net.machinemuse.numina.utils.energy.ElectricItemUtils;
import net.machinemuse.numina.utils.string.MuseCommonStrings;
import net.machinemuse.numina.utils.heat.MuseHeatUtils;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

import static net.machinemuse.powersuits.common.MPSConstants.MODULE_LUX_CAPACITOR;

public class LuxCapacitor extends PowerModuleBase implements IRightClickModule {
    public static final String ENERGY = "Lux Capacitor Energy Consumption";
//    public static final String RED =  "Lux Capacitor Red Hue";
//    public static final String GREEN = "Lux Capacitor Green Hue";
//    public static final String BLUE = "Lux Capacitor Blue Hue";

    public static final String COLOUR = "Lux Capacitor Color Index";


    public LuxCapacitor(List<IModularItem> validItems) {
        super(validItems);
        addInstallCost(new ItemStack(Items.GLOWSTONE_DUST, 1));
        addInstallCost(new ItemStack(Items.IRON_INGOT, 2));
        addBaseProperty(ENERGY, 100, "J");
//        addTradeoffProperty("Red", RED, 1, "%");
//        addTradeoffProperty("Green", GREEN, 1, "%");
//        addTradeoffProperty("Blue", BLUE, 1, "%");

        addBaseProperty("Color Index", 0);
        addTradeoffProperty("Color Index", COLOUR, 4);

    }

    @Override
    public String getCategory() {
        return MuseCommonStrings.CATEGORY_TOOL;
    }

    @Override
    public String getDataName() {
        return MODULE_LUX_CAPACITOR;
    }

    @Override
    public String getUnlocalizedName() {
        return "luxcapacitor";
    }

    @Override
    public ActionResult onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
        playerIn.setActiveHand(hand);
        if (!worldIn.isRemote) {
            double energyConsumption = ModuleManager.computeModularProperty(itemStackIn, ENERGY);
             MuseHeatUtils.heatPlayer(playerIn, energyConsumption / 500);
            if (ElectricItemUtils.getPlayerEnergy(playerIn) > energyConsumption) {
//                ElectricItemUtils.drainPlayerEnergy(playerIn, energyConsumption);

//                double red = ModuleManager.computeModularProperty(itemStackIn, RED);
//                double green = ModuleManager.computeModularProperty(itemStackIn, GREEN);
//                double blue = ModuleManager.computeModularProperty(itemStackIn, BLUE);

                double colourIndex =  ModuleManager.computeModularProperty(itemStackIn, COLOUR);

                System.out.println("value: " + colourIndex);


                //                EntityLuxCapacitor luxCapacitor = new EntityLuxCapacitor(worldIn, playerIn, EnumColour.getColourEnumFromIndex(colourIndex));
//                worldIn.spawnEntity(luxCapacitor);
            }
            return ActionResult.newResult(EnumActionResult.SUCCESS, itemStackIn);
        }
        return ActionResult.newResult(EnumActionResult.PASS, itemStackIn);
    }

    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        return EnumActionResult.PASS;
    }

    @Override
    public EnumActionResult onItemUseFirst(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
        return EnumActionResult.PASS;
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {

    }

    @SideOnly(Side.CLIENT)
    @Override
    public TextureAtlasSprite getIcon(ItemStack item) {
        return MuseIcon.luxCapacitor;
    }
}
