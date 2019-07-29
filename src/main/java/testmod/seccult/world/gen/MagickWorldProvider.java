package testmod.seccult.world.gen;

import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.gen.ChunkGeneratorOverworld;
import net.minecraft.world.gen.IChunkGenerator;
import testmod.seccult.world.gen.biome.SeccultBiomeProvider;

public class MagickWorldProvider extends WorldProvider{
	@Override
	protected void init() {
		this.biomeProvider = new BiomeProvider(this.world.getWorldInfo());
		/*try {
			this.biomeProvider = new SeccultBiomeProvider(this.world.getWorldInfo(), this.getSeed());
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}*/
		this.hasSkyLight = true;
		this.doesWaterVaporize = false;
		//this.world.setSeaLevel(64);
	}
	
	@Override
	public DimensionType getDimensionType() {
		return DimensionMagic.MAGIC;
	}

	@Override
	public Vec3d getFogColor(float p_76562_1_, float p_76562_2_) {
		float f = MathHelper.cos(p_76562_1_ * ((float)Math.PI * 2F)) * 2.0F + 0.5F;
        f = MathHelper.clamp(f, 0.0F, 1.0F);
        float f1 = 1.0F;
        float f2 = 0.89705883F;
        float f3 = 0.7F;
        f1 = f1 * (f * 0.94F + 0.2456F);
        f2 = f2 * (f * 0.94F + 0.06F);
        f3 = f3 * (f * 0.91F + 0.323324F);
        return new Vec3d((double)f1, (double)f2, (double)f3);
	}
	
	/*@Override
	public void onWorldUpdateEntities() {
		super.onWorldUpdateEntities();
		//this.world.setWorldTime((Calendar.HOUR - 6) * 1000 + Calendar.MINUTE * (1000 / 60));
	}*/

	@Override
	public boolean isSurfaceWorld() {
		return true;
	}
	
	@Override
	public boolean canCoordinateBeSpawn(int x, int z) {
		return super.canCoordinateBeSpawn(x, z);
	}
	
	@Override
	public boolean canRespawnHere() {
		return true;
	}
	
	@Override
	public boolean doesXZShowFog(int x, int z) {
		return true;
	}
	
	@Override
	public WorldBorder createWorldBorder() {
		return new WorldBorder();
	}
	
	@Override
	public IChunkGenerator createChunkGenerator() {
		//return new ChunkGeneratorSeccult(world, true, this.getSeed());
		return new ChunkGeneratorOverworld(world, this.getSeed(), true, "");
	}
}