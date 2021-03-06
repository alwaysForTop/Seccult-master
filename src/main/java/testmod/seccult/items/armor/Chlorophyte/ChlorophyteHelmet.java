package testmod.seccult.items.armor.Chlorophyte;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import testmod.seccult.items.armor.ChlorophyteArmor;
import testmod.seccult.network.NetworkEffectData;
import testmod.seccult.network.NetworkHandler;
import testmod.seccult.network.TransPoint;

public class ChlorophyteHelmet extends ChlorophyteArmor{

	public ChlorophyteHelmet(String name, ArmorMaterial materialIn, int renderIndexIn,
			EntityEquipmentSlot equipmentSlotIn) {
		super(name, materialIn, renderIndexIn, equipmentSlotIn);
		setMagickAttribute(0.2F, 1, 1F);
	}
	
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
		super.onArmorTick(world, player, itemStack);
		if(hasArmorSetItem(player))
			particle(player);
	}

	public void particle(EntityPlayer player)
	{
		for (int i = 0; i < 5; i++) {
			double tx = player.posX + (player.world.rand.nextFloat() * 0.1F);
			double ty = player.posY + (player.world.rand.nextFloat() * 0.1F) + player.height + 0.4F;
			double tz = player.posZ + (player.world.rand.nextFloat() * 0.1F);
			double motionX = 0.25F - 0.5 * player.world.rand.nextFloat();
			double motionY = 0.25F - 0.5 * player.world.rand.nextFloat();
			double motionZ = 0.25F - 0.5 * player.world.rand.nextFloat();

			double[] vec = {motionX / 50, motionY / 50, motionZ / 50};
			double[] pos = {tx, ty, tz};
			float[] color = {0.2F, 1.0f, 0.0f};
			
	        NetworkHandler.sendToAllAround(new NetworkEffectData(pos, vec, color, player.world.rand.nextFloat() * 1.2F, 2),
	        		new TransPoint(player.dimension, pos[0], pos[1], pos[2], 32), player.world);
		}
	}
}
