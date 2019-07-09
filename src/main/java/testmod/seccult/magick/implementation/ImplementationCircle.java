package testmod.seccult.magick.implementation;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import testmod.seccult.entity.EntityIMCircle;

public class ImplementationCircle extends ImplementationStoreable{

	public ImplementationCircle(String nbtName) {
		super(nbtName);
	}

	@Override
	public void getTarget() {
		if(getEntity() != null) 
		{
			List<Entity> eList = getEntity();
			for(int i = 0; i < eList.size(); i++)
			{	
				if(eList.get(i) != null) {
					Entity IMentity = eList.get(i);
					EntityIMCircle projec = new EntityIMCircle(player, player.world, IMentity.posX, IMentity.posY, IMentity.posZ, scale);
					projec.setData(LoadMagick, LoadSelect);
					projec.setColor(this.color[0], this.color[1], this.color[2]);
					player.world.spawnEntity(projec);
				}
			}
			setEntity(null);
		}
		else
		if(getBlock() != null)
		{
			List<BlockPos> bList = getBlock();
			for(int i = 0; i < bList.size(); i++)
			{	
				BlockPos IMblock = bList.get(i);
				EntityIMCircle projec = new EntityIMCircle(player, player.world, IMblock.getX(), IMblock.getY() + 1, IMblock.getZ(), scale);
				projec.setData(LoadMagick, LoadSelect);
				projec.setColor(this.color[0], this.color[1], this.color[2]);
				player.world.spawnEntity(projec);
			}
		}
	}
}
