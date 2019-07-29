package testmod.seccult.entity;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import testmod.seccult.init.ModDamage;

public class EntityClowCardArrow extends Entity{
	private Entity owner;
	private int damage;
	private float speed;
	private int Split;
	
	public EntityClowCardArrow(World worldIn) {
		super(worldIn);
		this.noClip = false;
		this.setSize(1.0F, 0.3F);
	}

	public EntityClowCardArrow(World worldIn, Entity entity, int Damage, float speed, int split) {
		super(worldIn);
		this.owner = entity;
		this.setPositionAndRotation(owner.posX, owner.posY, owner.posZ, owner.rotationYaw, owner.rotationPitch);
		this.damage = Damage;
		this.speed = speed;
		this.Split = split;
	}
	
	@Override
	protected void entityInit() {

	}

	@Override
	public void onUpdate() {
		// TODO Auto-generated method stub
		super.onUpdate();
		Move();
		collideWithNearbyEntities();
		if(this.ticksExisted > 10)
		Split();
		if(this.ticksExisted > 200)
			this.setDead();
	}
	
	private void Split() {
		if(this.Split > 1)
		{
			int times = this.rand.nextInt(2) + 1;
			for(int i = 0; i < times; times--)
			{
				if(this.owner != null)
				{
					EntityClowCardArrow newArrow = new EntityClowCardArrow(this.world, owner, this.damage, this.speed, this.Split - 1);
					newArrow.setPositionAndRotation(this.posX + 1 - this.rand.nextFloat() * 2, this.posY + 1 - this.rand.nextFloat() * 2, this.posZ + 1 - this.rand.nextFloat() * 2, this.rotationYaw, this.rotationPitch);
					this.world.spawnEntity(newArrow);
				}
				else
				{
					EntityClowCardArrow newArrow = new EntityClowCardArrow(this.world, this, this.damage, this.speed, this.Split - 1);
					newArrow.setPositionAndRotation(this.posX + 1 - this.rand.nextFloat() * 2, this.posY + 1 - this.rand.nextFloat() * 2, this.posZ + 1 - this.rand.nextFloat() * 2, this.rotationYaw, this.rotationPitch);
					this.world.spawnEntity(newArrow);
				}
			}
			this.Split = 0;
		}
	}

	private void Move() {
		if(!this.world.isAirBlock(getPosition()))
		{
			this.setDead();
			return;
		}
		this.motionX = this.getLookVec().x / 2 * speed;
		this.motionY = this.getLookVec().y / 2 * speed;
		this.motionZ = this.getLookVec().z / 2 * speed;
		
		this.posX = this.lastTickPosX + this.motionX;
		this.posY = this.lastTickPosY + this.motionY;
		this.posZ = this.lastTickPosZ + this.motionZ;
		
		this.lastTickPosX = this.posX;
		this.lastTickPosY = this.posY;
		this.lastTickPosZ = this.posZ;
	}
	
    protected void collideWithNearbyEntities()
    {
        List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().grow(0.5));
        if (!list.isEmpty())
        {
            for (int l = 0; l < list.size(); ++l)
            {
                Entity entity = list.get(l);
                this.applyEntityCollision(entity);
            }
        }
    }
	
	@Override
	public void applyEntityCollision(Entity entityIn) {
		//super.applyEntityCollision(entityIn);
		if(!(entityIn instanceof EntityClowCardArrow) && (owner != null && entityIn != owner))
		{
			entityIn.attackEntityFrom(ModDamage.causeNormalEntityDamage(owner), this.damage);
		}
		else if(!(entityIn instanceof EntityClowCardArrow))
			entityIn.attackEntityFrom(ModDamage.causeNormalEntityDamage(this), this.damage);
	}
	
	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
	}
}