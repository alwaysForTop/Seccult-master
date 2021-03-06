package testmod.seccult.magick;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;

public class MagickCompilerForEntity {
	
	private int magick;
	private int strength;
	private int attribute;
	private int[] imple;
	private int[] imple_strength;
	private int[] imple_attribute;
	private boolean doEntity;
	private boolean doBlock;
	private Entity entity;
	
	private boolean dontCost;
	
	public MagickCompilerForEntity(Entity entity, int magick, int strength, int attribute, int[] imple, int[] imple_strength, int[] imple_attribute) 
	{
		this.entity = entity;
		this.magick = magick;
		this.imple = imple;
		this.imple_strength = imple_strength;
		this.imple_attribute = imple_attribute;
		this.strength = strength;
		this.attribute = attribute;
		doEntity = true;
		doBlock = true;
	}
	
	public void DontCost()
	{
		this.dontCost = true;
	}
	
	public void DontEntity()
	{
		this.doEntity = false;
	}
	
	public void DontBlock()
	{
		this.doBlock = false;
	}
	
	public boolean Compile()
	{
		return pushMagick(Compile(1));
	}
	
	public NBTTagCompound Compile(int g)
	{
    	int[][] MagickThing = new int[4][];

		List<int[]> ListMagick = new ArrayList<>();
		List<int[]> ListSelector = new ArrayList<>();
		List<int[]> ListSelectorPower = new ArrayList<>();
		List<int[]> ListSelectorAttribute = new ArrayList<>();
		int MaxLength = 0;

    			int[] Selector = imple;
    			int[] SelectorPower = imple_strength;
    			int[] SelectorAttribute = imple_attribute;
    			int[] Magick = {magick, strength, attribute, Selector.length};
    			
    			if(Selector.length > MaxLength)
    				MaxLength = Selector.length;
    			
    			ListSelector.add(Selector);
    			ListSelectorPower.add(SelectorPower);
    			ListSelectorAttribute.add(SelectorAttribute);
    			ListMagick.add(Magick);

    		for(int i = 0; i < imple.length; ++i)
    		{
    			if(imple[i] == 5)
    				doEntity = false;
    			if(imple[i] == 6)
    				doBlock = false;
    		}

		MagickThing[0] = new int[1];
		MagickThing[1] = new int[1];
		MagickThing[2] = new int[1];
		MagickThing[3] = new int[1];
    	
		int[][] SelectorList = new int[1][];
		int[][] SelectorPowerList = new int[1][];
		int[][] SelectorAttributeList = new int[1][];
		
		for(int i = 0; i < 1; i++)
		{
			int[] m = ListMagick.get(i);
			for(int z = 0; z < 4; z ++)
        	{
				MagickThing[z][i] = m[z];
        	}
			int L = MagickThing[3][i];
			int[] im = ListSelector.get(i);
			SelectorList[i] = new int[L];
			SelectorPowerList[i] = new int[L];
			SelectorAttributeList[i] = new int[L];
			for(int z = 0; z < im.length; z++)
			{
				SelectorList[i][z] = ListSelector.get(i)[z];
				SelectorPowerList[i][z] = ListSelectorPower.get(i)[z];
				SelectorAttributeList[i][z] = ListSelectorAttribute.get(i)[z];
			}
		}
		NBTTagCompound MagickTag = MagickCompiler.compileMagick(MagickThing, SelectorList, SelectorPowerList, SelectorAttributeList, 1, doEntity, doBlock);
		return MagickTag;
	}
	
	public boolean pushMagick(NBTTagCompound MagickTag)
	{
		try {
		MagickCompiler compiler = new MagickCompiler();
		if(this.entity != null)
		{
			if(dontCost)
			compiler.dontCost = true;
			compiler.pushMagickData(MagickTag, this.entity);
			return true;
		}
		}
		catch(Exception e)
		{
			return false;
		}
		return false;
	}
}
