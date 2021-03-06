package testmod.seccult.entity.livings.landCreature;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityFlying;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateFlying;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import testmod.seccult.entity.EntityBarrier;
import testmod.seccult.entity.EntityBorderCrosser;
import testmod.seccult.entity.EntityVoid;
import testmod.seccult.entity.ISpaceEntity;
import testmod.seccult.entity.ai.EntityAIAlertForHelp;
import testmod.seccult.entity.ai.EntityAIFightBack;
import testmod.seccult.entity.ai.EntityAIFindCloestMonster;
import testmod.seccult.entity.ai.EntityAIFloatRandom;
import testmod.seccult.entity.ai.EntityAIHurtByTarget;
import testmod.seccult.entity.ai.EntityFloatHelper;
import testmod.seccult.entity.ai.IFightBackMob;
import testmod.seccult.entity.projectile.EntityAlterSpace;
import testmod.seccult.entity.projectile.EntitySpaceGatorix;
import testmod.seccult.entity.projectile.EntityAlterSpace.AlterType;
import testmod.seccult.magick.implementation.ImplementationFocused;
import testmod.seccult.world.gen.DimensionMagic;

public class EntitySpaceManager extends EntityCreature implements EntityFlying, IRangedAttackMob, ISpaceEntity, IFightBackMob{
	private HashSet<EntitySpaceGatorix> gatorix = new HashSet<EntitySpaceGatorix>();
	private HashSet<EntitySpaceManager> manager = new HashSet<EntitySpaceManager>();
	private List<String> DamageType = new ArrayList<String>();
	
	private int lostAttacker;
	
	public EntitySpaceManager(World worldIn) {
		super(worldIn);
		this.setSize(2F, 3F);
		this.moveHelper = new EntityFloatHelper(this);
		this.isImmuneToFire = true;
		this.noClip = true;
		init();
	}

    protected PathNavigate createNavigator(World worldIn)
    {
        PathNavigateFlying pathnavigateflying = new PathNavigateFlying(this, worldIn);
        pathnavigateflying.setCanOpenDoors(false);
        pathnavigateflying.setCanFloat(true);
        pathnavigateflying.setCanEnterDoors(true);
        return pathnavigateflying;
    }
	
	private void init()
	{
		DamageType.add(DamageSource.ANVIL.getDamageType());
		DamageType.add(DamageSource.CACTUS.getDamageType());
		DamageType.add(DamageSource.CRAMMING.getDamageType());
		DamageType.add(DamageSource.DROWN.getDamageType());
		DamageType.add(DamageSource.FALL.getDamageType());
		DamageType.add(DamageSource.FALLING_BLOCK.getDamageType());
		DamageType.add(DamageSource.FIREWORKS.getDamageType());
		DamageType.add(DamageSource.FLY_INTO_WALL.getDamageType());
		DamageType.add(DamageSource.GENERIC.getDamageType());
		DamageType.add(DamageSource.HOT_FLOOR.getDamageType());
		DamageType.add(DamageSource.IN_FIRE.getDamageType());
		DamageType.add(DamageSource.IN_WALL.getDamageType());
		DamageType.add(DamageSource.LAVA.getDamageType());
		DamageType.add(DamageSource.ON_FIRE.getDamageType());
		DamageType.add(DamageSource.OUT_OF_WORLD.getDamageType());
		DamageType.add(DamageSource.STARVE.getDamageType());
	}

	@Override
	protected void initEntityAI() {
		this.tasks.addTask(0, new EntityAIFightBack(this, 1, 50, 12));
		this.tasks.addTask(1, new EntityAIAttackRanged(this, 1, 60, 8));
		this.tasks.addTask(2, new EntityAIFloatRandom(this));
		this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 6F));
		this.tasks.addTask(4, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[] {EntitySpaceManager.class}));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true, new Class[] {EntityNightmarePop.class}));
		this.targetTasks.addTask(3, new EntityAIAlertForHelp(this, new Class[] {EntitySpaceManager.class}));
		this.targetTasks.addTask(4, new EntityAIAlertForHelp(this, new Class[] {EntityNightmarePop.class}));
        this.targetTasks.addTask(5, new EntityAIFindCloestMonster<EntityMob>(this, EntityMob.class, true));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getAttributeMap().registerAttribute(SharedMonsterAttributes.FLYING_SPEED);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(650.0D);
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(1000.0D);
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(1000.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(1D);
		this.getEntityAttribute(SharedMonsterAttributes.FLYING_SPEED).setBaseValue(1D);
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(32.0D);
		this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1D);
	}

	@Override
	public void onUpdate() {
		this.noClip = true;
        super.onUpdate();

		this.setNoGravity(true);
		
		if(this.getAttackTarget() == null && lostAttacker < 60)
			lostAttacker++;
		else if(this.getAttackTarget() != null)
			lostAttacker = 0;
		
		if(!this.world.isAirBlock(getPosition()))
		{
			this.motionY += 0.15F;
		}
		
		if(!this.world.isRemote)
		{
			List<EntitySpaceGatorix> gato = this.world.getEntitiesWithinAABB(EntitySpaceGatorix.class, this.getEntityBoundingBox().grow(32));
		
			this.gatorix.addAll(gato);

			List<EntitySpaceManager> manager = this.world.getEntitiesWithinAABB(EntitySpaceManager.class, this.getEntityBoundingBox().grow(32));
			
			this.manager.addAll(manager);
			
			if(this.getAttackTarget() != null)
			for(EntitySpaceGatorix gatorix : this.gatorix)
			{
				if(gatorix.getPrevVictim() == null && gatorix.getVictim() != null)
					gatorix.setPrevVictim(gatorix.getVictim());
				gatorix.setVictim(this.getAttackTarget());
			}
			else
			{
				for(EntitySpaceGatorix gatorix : this.gatorix)
					if(gatorix.getPrevVictim() != null)
					{
						gatorix.setVictim(gatorix.getPrevVictim());
						gatorix.setPrevVictim(null);
					}
			}

		    for(Iterator<EntitySpaceGatorix> iterator=this.gatorix.iterator(); iterator.hasNext();)
		    {
		    	EntitySpaceGatorix gatorix = iterator.next();

		    	if(gatorix.getVictim() == null)
		    	{
		    		boolean lost = true;
		    		
		    		for(Iterator<EntitySpaceManager> it = this.manager.iterator(); it.hasNext();)
				    {
		    			if(this.lostAttacker < 60 || it.next().lostAttacker < 60)
		    			{
		    				lost = false;
		    				break;
		    			}
				    }
		    		
		    		if(!lost)
		    			continue;
		    		
			    	gatorix.setDead();
			    	iterator.remove();
		    	}
		    }
		}
		
		if(this.hurtResistantTime <= 0 && this.ticksExisted % 20 == 0)
			this.heal(2);
		
		if(this.dimension == DimensionMagic.MAGIC_ID && this.ticksExisted % 20 == 0)
			this.setHealth(2 + this.getHealth());
		
		if(this.getAttackTarget() != null && this.getDistance(getAttackTarget()) > 30)
		{
			Vec3d vec = getAttackTarget().getPositionVector().add(getAttackTarget().getLookVec().scale(3));
			
			this.teleportEntity(this, vec.x, vec.y, vec.z);
			this.faceEntity(getAttackTarget(), 360, 360);
		}
		
		removeBadEffect();
		reflectProjectile();
	}
	
	protected void removeBadEffect()
	{
		Iterator<PotionEffect> iterator = this.getActivePotionEffects().iterator();

        while (iterator.hasNext())
        {
            PotionEffect effect = iterator.next();
            if(effect.getPotion().isBadEffect())
            {
            	{
            		List<Entity> entity = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().grow(16));
        			for(int i = 0; i < entity.size(); ++i)
        			{
        				if(entity.get(i) instanceof EntityLivingBase)
        				{
        					((EntityLivingBase)entity.get(i)).addPotionEffect(new PotionEffect(effect.getPotion(), effect.getDuration(), effect.getAmplifier()));

        					onFinishedPotionEffect(this, effect);
    		            	iterator.remove();
        					break;
        				}
        			}
        			
        			if(entity.isEmpty())
        			{
        				onFinishedPotionEffect(this, effect);
		            	iterator.remove();
        			}
            	}
            }
        }
	}
	
	private void reflectProjectile()
	{
		List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().grow(3));
		
		//List<EntityLivingBase> livingEntity = this.world.getEntitiesWithinAABB(EntityLivingBase.class, this.getEntityBoundingBox().grow(16));

		/*for(Iterator<EntityLivingBase> it = livingEntity.iterator(); it.hasNext();)
	    {
			if(!(it.next() instanceof IMob))
			{
				it.remove();
			}
	    }
		*/
		for(int i = 0; i < list.size(); ++i)
		{
			Entity entity = list.get(i);
			if(!(entity instanceof EntityLivingBase) && !(entity instanceof ISpaceEntity) && !entity.onGround)
			{
				/*if(!livingEntity.isEmpty())
				{
					EntityLivingBase victim = livingEntity.get(0);

					teleportEntity(victim, entity.posX, entity.posY, entity.posZ);
				}*/
				if((entity instanceof EntityThrowable && ((EntityThrowable) entity).getThrower() != null) 
						|| (entity instanceof EntityArrow && ((EntityArrow) entity).shootingEntity != null)
						|| (entity instanceof EntityFireball && ((EntityFireball) entity).shootingEntity != null))
				{
					Entity living = null;
					if(entity instanceof EntityThrowable)
					{
						EntityThrowable throwable = (EntityThrowable) entity;
						living = throwable.getThrower();
					}
					else if(entity instanceof EntityArrow)
					{
						EntityArrow arrow = (EntityArrow) entity;
						living = arrow.shootingEntity;
					}
					else if(entity instanceof EntityFireball)
					{
						EntityFireball arrow = (EntityFireball) entity;
						living = arrow.shootingEntity;
					}
					
					float finalDistance = 3;
					
					Vec3d vec = new Vec3d(-entity.motionX, -entity.motionY, -entity.motionZ);
					Vec3d posAir = living.getPositionVector().addVector(0, living.height / 2, 0).addVector(vec.x * finalDistance, vec.y * finalDistance, vec.z * finalDistance);
					BlockPos bPos = new BlockPos(posAir);
					
					NBTTagCompound tag = new NBTTagCompound();
					entity.writeToNBT(tag);
					tag.setTag("Pos", this.newDoubleNBTList(bPos.getX(), bPos.getY(), bPos.getZ()));
					entity.setDead();
					entity.isDead = true;
					this.world.removeEntityDangerously(entity);
					try {
						Entity e = (Entity)entity.getClass().getConstructor(new Class[] { World.class }).newInstance(new Object[] { world });
						e.readFromNBT(tag);
						if(!this.world.isRemote)
							this.world.spawnEntity(e);
					} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
							| InvocationTargetException | NoSuchMethodException | SecurityException e) {
						e.printStackTrace();
					}
				}
				else
				{
				BlockPos pos = ImplementationFocused.getBlockMotionAt(entity, 6.2);
				for(int c = 2; c < 6.2; ++c)
				{
					if(this.getDistanceSq(pos) < 3.1)
						pos = ImplementationFocused.getBlockMotionAt(entity, 3.1*c);
				}
				
				NBTTagCompound tag = new NBTTagCompound();
				entity.writeToNBT(tag);
				tag.setTag("Pos", this.newDoubleNBTList(pos.getX(), pos.getY(), pos.getZ()));
				entity.setDead();
				entity.isDead = true;
				this.world.removeEntityDangerously(entity);
				try {
					Entity e = (Entity)entity.getClass().getConstructor(new Class[] { World.class }).newInstance(new Object[] { world });
					e.readFromNBT(tag);
					if(!this.world.isRemote)
						this.world.spawnEntity(e);
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException | NoSuchMethodException | SecurityException e) {
					e.printStackTrace();
				}
				}
			}
		}
	}
	
	protected boolean teleportEntity(Entity entity, double x, double y, double z)
	{
		double d0 = entity.posX;
        double d1 = entity.posY;
        double d2 = entity.posZ;
        entity.posX = x;
        entity.posY = y;
        entity.posZ = z;
        boolean flag = false;
        BlockPos blockpos = new BlockPos(entity);
        World world = entity.world;
        Random random = this.getRNG();

        if (world.isBlockLoaded(blockpos))
        {
            this.setPositionAndUpdate(entity.posX, entity.posY, entity.posZ);

            if (world.getCollisionBoxes(entity, entity.getEntityBoundingBox()).isEmpty() && !world.containsAnyLiquid(entity.getEntityBoundingBox()))
            {
                flag = true;
            }
        }

        if (!flag)
        {
        	entity.setPositionAndUpdate(d0, d1, d2);
            return false;
        }
        else
        {
            for (int j = 0; j < 128; ++j)
            {
                double d6 = (double)j / 127.0D;
                float f = (random.nextFloat() - 0.5F) * 0.2F;
                float f1 = (random.nextFloat() - 0.5F) * 0.2F;
                float f2 = (random.nextFloat() - 0.5F) * 0.2F;
                double d3 = d0 + (entity.posX - d0) * d6 + (random.nextDouble() - 0.5D) * (double)entity.width * 2.0D;
                double d4 = d1 + (entity.posY - d1) * d6 + random.nextDouble() * (double)entity.height;
                double d5 = d2 + (entity.posZ - d2) * d6 + (random.nextDouble() - 0.5D) * (double)entity.width * 2.0D;
                world.spawnParticle(EnumParticleTypes.PORTAL, d3, d4, d5, (double)f, (double)f1, (double)f2);
            }

            if (entity instanceof EntityCreature)
            {
                ((EntityCreature)entity).getNavigator().clearPath();
            }

            return true;
        }
	}
	
	protected static void onFinishedPotionEffect(EntityLiving player, PotionEffect effect)
	{
		effect.getPotion().removeAttributesModifiersFromEntity(player, player.getAttributeMap(), effect.getAmplifier());
	}
	
	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) 
	{
		int chance = (int) target.width;
		if(chance < 1)
			chance = 1;
		
		if(chance > 5)
			chance = 6;
		
		if((target.ticksExisted > 400 && this.rand.nextInt(10) == 0) || this.gatorix.size() > 4)
		{
			fightEntityBack(target, distanceFactor);
			return;
		}
		
		for(int i = 0; i < chance; ++i)
		{
			EntitySpaceGatorix gatorix = new EntitySpaceGatorix(getEntityWorld(), this, target);
			gatorix.shoot(this.getLookVec().x, this.getLookVec().y, this.getLookVec().z, 0, 0);
			gatorix.setCharged(target.width);
			if(!this.world.isRemote)
				this.world.spawnEntity(gatorix);
		}
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if(this.DamageType.contains(source.getDamageType()))
			return false;
		
		if(amount > 25)
		{
			if(source.getTrueSource() != null)
				source.getTrueSource().attackEntityFrom(DamageSource.OUT_OF_WORLD, amount - 25);
			
			amount = 25;
		}
		
		if(this.getHealth() < this.getMaxHealth() / 2 && this.getRevengeTarget() != null && this.rand.nextInt(30) == 0)
		{
			EntityNightmarePop pop = new EntityNightmarePop(getEntityWorld());
			pop.setPosition(this.posX + 3 - this.rand.nextInt(3), this.posY + 3 - this.rand.nextInt(3), this.posZ + 3 - this.rand.nextInt(3));
			pop.setAttackTarget(getRevengeTarget());
			if(!this.world.isRemote)
				world.spawnEntity(pop);
		}

		return super.attackEntityFrom(source, amount);
	}
	
	@Override
	public void setSwingingArms(boolean swingingArms) {
	}

	@Override
	public boolean isImmuneToExplosions() {
		return true;
	}
	
	@Override
	public float getEyeHeight() {
		return this.height / 2;
	}

	@Override
	public void fightEntityBack(EntityLivingBase target, float distanceFactor) {

			if(target.getHeldItemMainhand() != null)
			{
				this.faceEntity(target, 360, 360);
				BlockPos pos = ImplementationFocused.getBlockLookedAt(this, 1);
				EntityItem item = new EntityItem(getEntityWorld(), pos.getX(), pos.getY(), pos.getZ(), target.getHeldItemMainhand().copy());
				target.setHeldItem(EnumHand.MAIN_HAND, ItemStack.EMPTY);
				this.world.spawnEntity(item);
			}
			else if(target.getHeldItemOffhand() != null)
			{
				this.faceEntity(target, 360, 360);
				BlockPos pos = ImplementationFocused.getBlockLookedAt(this, 1);
				EntityItem item = new EntityItem(getEntityWorld(), pos.getX(), pos.getY(), pos.getZ(), target.getHeldItemOffhand().copy());
				target.setHeldItem(EnumHand.OFF_HAND, ItemStack.EMPTY);
				this.world.spawnEntity(item);
			}
		
		AlterType type = this.rand.nextInt(5) == 0? AlterType.Void: AlterType.Barrier;

		List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(target, target.getEntityBoundingBox());
		
		EntityBarrier barrier = null;
		
		for(Entity entity : list)
		{
			if(entity instanceof EntityBarrier)
			{
				type = AlterType.Void;
				barrier = (EntityBarrier) entity;
				break;
			}
		}

		int voidAmount = 0;
		
		for(Entity entity : list)
		{
			if(entity instanceof EntityVoid)
			{
				++voidAmount;
			}
		}
		
		if(voidAmount > 5)
			return;
		
		if(this.getHealth() < this.getMaxHealth() / 5 && barrier != null && this.rand.nextInt(5) == 0)
		{
			EntityBorderCrosser crosser = null;
			Integer[] dims = DimensionManager.getStaticDimensionIDs();
			int getDim = this.dimension;
			for(int dim : dims)
			{
				if(dim != this.dimension);
				getDim = dim;
			}
			
			crosser = new EntityBorderCrosser(world, getDim);
			crosser.setPosition(target.posX, target.posY, target.posZ);
			barrier.setCrosser(crosser);
			if(!this.world.isRemote)
				world.spawnEntity(crosser);
		}
		
		if(this.getHealth() < this.getMaxHealth() / 3 && this.rand.nextInt(5) == 0)
			type = AlterType.TerrainTrans;
		
		EntityAlterSpace gatorix = new EntityAlterSpace(getEntityWorld(), this, target,  target.height * 1.25F, target.width * 1.5F, type);
		gatorix.shoot(this.getLookVec().x, this.getLookVec().y, this.getLookVec().z, 0, 0);
		if(!this.world.isRemote)
			this.world.spawnEntity(gatorix);
	}
	
	@Override
	protected void onDeathUpdate() {
		/*if(this.world != null && !this.world.isRemote) {
			EntitySpaceManager newManager = new EntitySpaceManager(world);
			newManager.setPosition(this.posX + 5 - this.rand.nextInt(10), this.posY + 5 - this.rand.nextInt(10), this.posZ + 5 - this.rand.nextInt(10));

			this.world.spawnEntity(newManager);
			deathTime++;
		}*/
		super.onDeathUpdate();
	}
	
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		if(this.world != null) 
		{
			if(this.getHealth() > 0)
			{
				this.isDead = false;
				this.deathTime = 0;
				this.world.spawnEntity(this);
			}
			else if(!this.world.isRemote)
			{
				EntitySpaceManager newManager = new EntitySpaceManager(world);
				newManager.setPosition(this.posX + 5 - this.rand.nextInt(10), this.posY + 5 - this.rand.nextInt(10), this.posZ + 5 - this.rand.nextInt(10));
				this.world.spawnEntity(newManager);
				deathTime++;
			}
		}
	}
}
