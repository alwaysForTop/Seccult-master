package testmod.seccult.blocks;

import java.util.List;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import testmod.seccult.Seccult;
import testmod.seccult.blocks.tileEntity.tileKillerQueen;
import testmod.seccult.creativetab.CreativeTabsLoader;
import testmod.seccult.init.ModBlocks;
import testmod.seccult.init.ModItems;
import testmod.seccult.items.ItemSoulStone;
import testmod.seccult.items.armor.MagickArmor;
import testmod.seccult.util.registerModel;

public class BlockKillerQueen extends BlockContainer implements registerModel{
	private final String name;
	
	public BlockKillerQueen(String name) {
		super(Material.IRON);
		this.name = name;
		setSoundType(SoundType.METAL);
		setRegistryName(name);
		setUnlocalizedName(name);
		this.setCreativeTab(CreativeTabsLoader.tab);
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new tileKillerQueen();
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

		if(player.getHeldItem(hand).getItem() == ModItems.SoulStone)
		{
			if(ItemSoulStone.getSoul(player.getHeldItem(hand), worldIn) != null)
			{
				tileKillerQueen killer = (tileKillerQueen) worldIn.getTileEntity(pos);
				if(killer.putEntity(ItemSoulStone.getSoul(player.getHeldItem(hand), worldIn)))
					player.getHeldItem(hand).setCount(player.getHeldItem(hand).getCount() - 1);
				else return super.onBlockActivated(worldIn, pos, state, player, hand, facing, hitX, hitY, hitZ);
			}
			
		}
		
		return super.onBlockActivated(worldIn, pos, state, player, hand, facing, hitX, hitY, hitZ);
	}
	
	@Override
	public void addInformation(ItemStack stack, World player, List<String> tooltip, ITooltipFlag advanced) {
		super.addInformation(stack, player, tooltip, advanced);
		MagickArmor.addStringToTooltip("&c来自老板的热心关照", tooltip);
	}
	
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
    
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }
	
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }
    
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}
	
	@Override
	public void registerModels() 
	{
		Seccult.proxy.registerVariantRenderer(Item.getItemFromBlock(this), 0, this.name, "inventory");
	}
}
