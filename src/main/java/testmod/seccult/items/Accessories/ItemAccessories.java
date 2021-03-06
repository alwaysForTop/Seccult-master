package testmod.seccult.items.Accessories;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import testmod.seccult.api.accessorie.PlayerAccessorieHandler;
import testmod.seccult.items.ItemBase;

public class ItemAccessories extends ItemBase{

	public ItemAccessories(String name) {
		super(name);
	}
	
	public boolean hasAccessories(EntityPlayer player, ItemStack item)
	{
		ItemStack[] stack = PlayerAccessorieHandler.get(player).getAccessories();
		for(int i = 0; i < stack.length; i++)
		{
			if(stack[i] == item)
				return true;
		}
		return false;
	}
	
	public static boolean hasAccessories(EntityPlayer player, Item item)
	{
		ItemStack[] stack = PlayerAccessorieHandler.get(player).getAccessories();
		for(int i = 0; i < stack.length; i++)
		{
			if(stack[i] != null && stack[i].getItem() == item)
				return true;
		}
		return false;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		if(PlayerAccessorieHandler.get(playerIn).addAccessorie(playerIn.getHeldItem(handIn)))
		{
			playerIn.getHeldItem(handIn).shrink(1);
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
		}
		return new ActionResult<ItemStack>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
	}
}
