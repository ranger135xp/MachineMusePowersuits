package net.machinemuse.powersuits.common.items.modules.armor;

import net.machinemuse.numina.api.item.IModularItem;
import net.machinemuse.powersuits.client.event.MuseIcon;
import net.machinemuse.powersuits.common.items.modules.PowerModuleBase;
import net.machinemuse.numina.utils.string.MuseCommonStrings;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.List;

/**
 * Created by User: Andrew
 * Date: 4/21/13
 * Time: 2:03 PM
 */
public class ApiaristArmorModule extends PowerModuleBase {
    public static final String MODULE_APIARIST_ARMOR = "Apiarist Armor";
    public static final String APIARIST_ARMOR_ENERGY_CONSUMPTION = "Apiarist Armor Energy Consumption";

    public ApiaristArmorModule(List<IModularItem> validItems) {
        super(validItems);
        ItemStack stack = new ItemStack( Item.REGISTRY.getObject(new ResourceLocation("forestry", "craftingMaterial")), 6);
        stack.setItemDamage(3);
        addInstallCost(stack);
        addBaseProperty(APIARIST_ARMOR_ENERGY_CONSUMPTION, 10, "J");
    }

    @Override
    public String getCategory() {
        return MuseCommonStrings.CATEGORY_ARMOR;
    }

    @Override
    public String getDataName() {
        return MODULE_APIARIST_ARMOR;
    }

    @Override
    public String getUnlocalizedName() {
        return "apiaristArmor";
    }

    @Override
    public TextureAtlasSprite getIcon(ItemStack item) {
        return MuseIcon.apiaristArmor;
    }
}