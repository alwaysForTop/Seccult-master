package testmod.seccult.items.armor.Ocean;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import testmod.seccult.items.armor.OceanArmor;
import testmod.seccult.items.armor.MagickArmor.CoreType;

public class OceanBoot extends OceanArmor{

	public OceanBoot(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
		super(name, materialIn, renderIndexIn, equipmentSlotIn);
		setMagickAttribute(0.1F, 1, 1F);
	}
	
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
		super.onArmorTick(world, player, itemStack);
		addMagickCore(itemStack, CoreType.JumpCore);
	}
}
