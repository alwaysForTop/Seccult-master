package testmod.seccult.client.entity.render;
 
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import testmod.seccult.Seccult;
import testmod.seccult.client.entity.model.ModelFish;
import testmod.seccult.entity.livings.water.EntityFish;
import testmod.seccult.entity.livings.water.EntityJellyfish;

@SideOnly(Side.CLIENT)
public class RenderFish extends RenderLiving<EntityFish>
{
	private static final ResourceLocation TEXTURESD = new ResourceLocation(Seccult.MODID + ":textures/entity/fish_normal.png");
	
	public RenderFish(RenderManager renderManager) 
	{
		super(renderManager, new ModelFish(), 0.3F);
	}
	
	@Override
	protected void preRenderCallback(EntityFish e, float partialTickTime) {
		super.preRenderCallback(e, partialTickTime);
		float size = e.width;
		GlStateManager.scale(size * 2, size * 2, size * 2);
		this.shadowSize = size / 2;
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityFish entity)
	{
		return TEXTURESD;
	}
}
