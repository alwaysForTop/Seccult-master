package testmod.seccult.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCocoa;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import testmod.seccult.Seccult;
import testmod.seccult.creativetab.CreativeTabsLoader;
import testmod.seccult.init.ModBlocks;
import testmod.seccult.init.ModItems;
import testmod.seccult.util.registerModel;

public class LanternFruit extends BlockCocoa implements registerModel{
    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
    private final String name;
	public LanternFruit(String name) {
		super();
		setLightLevel(10);
		this.setRegistryName(name);
		this.setUnlocalizedName(name);
		this.name = name;
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.SOUTH).withProperty(AGE, Integer.valueOf(0)));
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
		setCreativeTab(CreativeTabsLoader.tab);
	}
	
	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		super.updateTick(worldIn, pos, state, rand);
		if(state.getValue(AGE).intValue() == 0)
			this.setLightLevel(3);
		else if(state.getValue(AGE).intValue() == 1)
			this.setLightLevel(7);
		else if(state.getValue(AGE).intValue() == 2)
			this.setLightLevel(12);
	}
	
	@Override
    public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state)
    {
        pos = pos.offset((EnumFacing)state.getValue(FACING));
        IBlockState iblockstate = worldIn.getBlockState(pos);
        return iblockstate.getBlock() == ModBlocks.LOGS;
    }
	
    @Override
    public void getDrops(net.minecraft.util.NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        int i = ((Integer)state.getValue(AGE)).intValue();
        int j = 1;

        if (i >= 2)
        {
            j = 3;
        }

        for (int k = 0; k < j; ++k)
        {
        	Block block = ModBlocks.LANTERN_FRUIT;
        	block.getBlockState().getBaseState().withProperty(AGE, ((Integer)state.getValue(AGE)).intValue());
            drops.add(new ItemStack(block, 1, 0));
        }
    }

    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
    	Block block = ModBlocks.LANTERN_FRUIT;
    	block.getBlockState().getBaseState().withProperty(AGE, ((Integer)state.getValue(AGE)).intValue());
        return new ItemStack(block, 1, 0);
    }
	
	@Override
	public void registerModels() 
	{
			Seccult.proxy.registerVariantRenderer(Item.getItemFromBlock(this), 0, this.name, "inventory");
	}
}
