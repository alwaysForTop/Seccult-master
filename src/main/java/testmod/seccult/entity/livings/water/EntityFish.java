package testmod.seccult.entity.livings.water;

import java.util.List;

import net.minecraft.block.BlockLiquid;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import testmod.seccult.init.ModMagicks;
import testmod.seccult.magick.active.Magick;
import testmod.seccult.magick.implementation.ImplementationFocused;

public class EntityFish extends EntityWaterCreature{
	private int swimingTime;
	
	private int warningTime;
	
	public EntityFish(World worldIn) {
		super(worldIn);		
		float size = rand.nextFloat();
		if(size > 0.6F) size = 0.6F;
		this.setSize(size, size / 2);
		this.swimingTime += 35;
		
		this.swingLimit = 15;
	}

	@Override
	public boolean getCanSpawnHere() {
		return super.getCanSpawnHere();
	}
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
        this.getEntityAttribute(SWIM_SPEED).setBaseValue(2);
	}
	
	@Override
	public void waterMoving() 
	{
		this.setNoGravity(true);
		if(!this.world.isRemote && this.world.isDaytime()) {
            if (this.world.getNearestPlayerNotCreative(this, 5.0D) != null || warningTime > 0)
            {
            	EntityWaterCreature.inWaterWalk(this, 0.3F, 1, this.rand.nextInt(2) + 1);
        		if(this.rand.nextInt(4) == 0)
        		{
        			int i = 2 + this.rand.nextInt(8);
        			BlockPos b = ImplementationFocused.getBlockLookedAt(this, i);
        			if(b != null && this.world.getBlockState(b).getBlock() instanceof BlockLiquid)
        			{
        				Magick m = ModMagicks.getMagickFromName(ModMagicks.TeleportMagick);
        				m.setMagickAttribute(this, this, null, i, 0);
        			}
        		}
            }
			
            if(this.rand.nextInt(100) == 0)
            	swimingTime += this.rand.nextInt(200) + 20;
            if(swimingTime > 0)
            {
            	EntityWaterCreature.inWaterWalk(this, 0.1F, 5, 1.5F);
            	if(this.rand.nextInt(3) == 0)
            	{
            		EntityWaterCreature.inWaterWalk(this, 0.2F, 5, 1.5F);
            		if(this.rand.nextInt(30) == 0)
            		{
            			int i = 2 + this.rand.nextInt(8);
            			BlockPos b = ImplementationFocused.getBlockLookedAt(this, i);
            			if(b != null && this.world.getBlockState(b).getBlock() instanceof BlockLiquid)
            			{
            				Magick m = ModMagicks.getMagickFromName(ModMagicks.TeleportMagick);
            				m.setMagickAttribute(this, this, null, i, 0);
            			}
            		}
            	}
            	
            	if(!(world.getBlockState(getPosition().up()).getBlock() instanceof BlockLiquid))
            		this.motionY -= 1;
            	swimingTime--;
            }
		}
		else if(!this.world.isRemote)
		{
            if (this.world.getNearestPlayerNotCreative(this, 2.0D) != null || warningTime > 0)
            {
            	EntityWaterCreature.inWaterWalk(this, 0.3F, 1, this.rand.nextInt(2) + 1);
    			if(this.rand.nextInt(30) == 0)
    			{
        			int i = 4 + this.rand.nextInt(8);
        			BlockPos b = ImplementationFocused.getBlockLookedAt(this, i);
        			if(b != null && this.world.getBlockState(b).getBlock() instanceof BlockLiquid)
        			{
        				Magick m = ModMagicks.getMagickFromName(ModMagicks.TeleportMagick);
        				m.setMagickAttribute(this, this, null, i, 0);
        			}
    			}
    			swimingTime += 35;
    			
    			warningOthers();
            }
		}
		
		if(warningTime > 0)
			warningTime--;
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();

	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		boolean attack = super.attackEntityFrom(source, amount);
		if(attack)
		{
			this.warningTime += 1;
			warningOthers();
		}
		
		if(source.damageType.equals(DamageSource.IN_WALL.damageType))
		{
			int i = 2 + this.rand.nextInt(8);
			BlockPos b = ImplementationFocused.getBlockLookedAt(this, i);
			if(b != null && this.world.getBlockState(b).getBlock() instanceof BlockLiquid)
			{
				Magick m = ModMagicks.getMagickFromName(ModMagicks.TeleportMagick);
				m.setMagickAttribute(this, this, null, i, 0);
			}
		}
		
		return attack;
	}
	
	private void warningOthers()
	{
		List<Entity> entities = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().grow(4));
		
		for(Entity e : entities)
		{
			if(e instanceof EntityFish)
			{
				EntityFish fish = (EntityFish) e;
				fish.warningTime += 1;
			}
		}
	}

	@Override
	protected Item getDropItem() {
		return Items.FISH;
	}
	
	@Override
	protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier) {
        Item item = this.getDropItem();

        if (item != null)
        {
            if(this.rand.nextInt(3) != 0)
            this.dropItem(item, 1);
        }
	}
	
	@Override
	public void GroundThing() 
	{
		this.setNoGravity(false);
		EntityWaterCreature.onGroundJump(this, 0.5F, 20, 3);
	}
}
