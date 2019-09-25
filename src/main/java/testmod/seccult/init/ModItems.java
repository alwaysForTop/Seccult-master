package testmod.seccult.init;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemPickaxe;
import net.minecraftforge.common.util.EnumHelper;
import testmod.seccult.items.*;
import testmod.seccult.items.Accessories.*;
import testmod.seccult.items.TRprojectile.ItemTerraBeam;
import testmod.seccult.items.armor.Chlorophyte.*;
import testmod.seccult.items.armor.Magick.*;
import testmod.seccult.items.armor.Ocean.*;
import testmod.seccult.items.armor.ShadowSky.*;
import testmod.seccult.items.armor.SilkFeather.*;
import testmod.seccult.items.armor.Sorcerer.*;
import testmod.seccult.items.tools.*;
import testmod.seccult.items.tools.TRWeapon.*;

public class ModItems 
{
	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	//Material
	public static final ToolMaterial MATERIAL_SPA = EnumHelper.addToolMaterial("material_spa", 32, 0, 9999.0F, 12446F, 200);
	public static final ArmorMaterial ARMOR_MATERIAL_SPA = EnumHelper.addArmorMaterial("armor_material_spa", "seccult:spa", 10,
			new int[] {2, 3, 4, 2}, 200, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2.0F);
	
	public static final ArmorMaterial ARMOR_MATERIAL_SILK = EnumHelper.addArmorMaterial("armor_material_silk", "seccult:silk", 5,
			new int[] {1, 1, 1, 1}, 200, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 50.0F);
	
	public static final ArmorMaterial ARMOR_MATERIAL_SORCERER = EnumHelper.addArmorMaterial("armor_material_sorcerer", "seccult:sorcerer", 15,
			new int[] {4, 5, 5, 3}, 200, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 10.0F);
	
	public static final ArmorMaterial ARMOR_MATERIAL_SHADOW = EnumHelper.addArmorMaterial("armor_material_shadow", "seccult:shadow", 30,
			new int[] {6, 8, 8, 5}, 200, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 8.0F);
	
	public static final ArmorMaterial ARMOR_MATERIAL_CHLOROPHYTE = EnumHelper.addArmorMaterial("armor_material_chlorophyte", "seccult:chlorophyte", 25,
			new int[] {2, 3, 4, 2}, 200, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 19.0F);
	
	public static final ArmorMaterial ARMOR_MATERIAL_OCEAN = EnumHelper.addArmorMaterial("armor_material_ocean", "seccult:ocean", 30,
			new int[] {5, 7, 7, 5}, 200, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 18.0F);
	
	//Items
	public static final Item SPA = new ItemBase("spa");
	public static final Item TimeStopper = new ItemTimeStopper("timestopper");
	public static final Item BlackVelvetHell = new ItemBlackVelvetHell("blackvelvethell");
	public static final Item YesYourHighness = new ItemYesYourHighness("yesyourhighness");
	public static final Item EVENT_THING = new ItemTerrariaEventThing("eow");
	public static final Item SCP173 = new ItemSCP173("scp173");
	public static final Item Wand = new ItemWand("wand");
	public static final Item SoulStone = new ItemSoulStone("soul_stone");
	public static final Item DummySystem = new ItemDummySystem("dummy_system");
	public static final Item MagickCore = new ItemMagickCore("magick_core");
	
	//Tools
	public static final Item SPA_SOWRD = new SwordTool("spa_sword");
	public static final ItemPickaxe SPA_PICKAXE = new PickaxeTool("spa_pickaxe", MATERIAL_SPA);
	
	//TRWeapon
	//public static final Item Enchanted_Sword = new EnchantedSword("enchanted_sword");
	//public static final Item Terraria_Blade = new TerrariaBlade("terraria_blade");
	//public static final Item Muramasa = new Muramasa("muramasa");
	//public static final Item Night_Edge = new NightEdge("night_edge");
	//public static final Item StarFury = new StarFury("starfury");
	//public static final Item Blade_Of_Grass = new BladeOfGrass("blade_of_grass");
	//public static final Item Fiery_GreatSword = new FieryGreatSword("fiery_greatsword");
	//public static final Item Light_Bane = new LightBane("light_bane");
	//public static final Item Cobalt_Sword = new CobaltSword("cobalt_sword");
	//public static final Item Palladium_Sword = new PalladiumSword("palladium_sword");
	//public static final Item Mythril_Sword = new MythrilSword("mythril_sword");
	//public static final Item Orichalcum_Sword = new OrichalcumSword("orichalcum_sword");
	//public static final Item Chlorophyte_Saber = new ChlorophyteSaber("chlorophyte_saber");
	//public static final Item Breaker_Blade = new BreakerBlade("breaker_blade");
	//public static final Item Adamantite_Sword = new AdamantiteSword("adamantite_sword");
	//public static final Item Titanium_Sword = new TitaniumSword("titanium_sword");
	//public static final Item Excalibur = new Excalibur("excalibur");
	//public static final Item True_Excalibur = new TrueExcalibur("true_excalibur");
	//public static final Item Chlorophyte_Claymore = new ChlorophyteClaymore("chlorophyte_claymore");
	//public static final Item The_Horseman_Blade = new TheHorsemanBlade("the_horseman_blade"); 
	//public static final Item True_Night_Edge = new TrueNightEdge("true_night_edge");
	//public static final Item Star_Wrath = new StarWrath("star_wrath");
	//public static final Item Influx_Waver = new InfluxWaver("influx_waver");
	public static final Item Meowmere = new Meowmere("meowmere");
	//public static final Item Beam_Sword = new BeamSword("beam_sword");
	public static final Item Vampire_Knives = new VampireKnives("vampire_knives");
	//public static final Item Daybreak = new Daybreak("daybreak");
	public static final Item Solar_Eruption = new SolarEruption("solar_eruption");
	//public static final Item Scourge_of_the_Corruptor = new ScourgeoftheCorruptor("scourge_of_the_corruptor");
	public static final Item LastPrism = new LastPrism("last_prism");
	
	
	//TRprojectile
	public static final Item Beam = new ItemTerraBeam("light");
	//public static final Item StarFury_Beam = new ItemTerraBeam("starfury_beam");
	
	//Armor
	public static final Item SILK_FEATHER_HELMET = new SilkFeatherHelmet("silk_feather_helmet", ARMOR_MATERIAL_SILK, 1, EntityEquipmentSlot.HEAD);
	public static final Item SILK_FEATHER_CHEST = new SilkFeatherChest("silk_feather_chest", ARMOR_MATERIAL_SILK, 1, EntityEquipmentSlot.CHEST);
	public static final Item SILK_FEATHER_LEGGINGS = new SilkFeatherLegs("silk_feather_leggings", ARMOR_MATERIAL_SILK, 1, EntityEquipmentSlot.LEGS);
	public static final Item SILK_FEATHER_BOOTS = new SilkFeatherBoot("silk_feather_boots", ARMOR_MATERIAL_SILK, 1, EntityEquipmentSlot.FEET);
	
	public static final Item SPA_HELMET = new MagickHelmet("spa_helmet", ARMOR_MATERIAL_SPA, 1, EntityEquipmentSlot.HEAD);
	public static final Item SPA_CHEST = new MagickChest("spa_chest", ARMOR_MATERIAL_SPA, 1, EntityEquipmentSlot.CHEST);
	public static final Item SPA_LEGGINGS = new MagickLegs("spa_leggings", ARMOR_MATERIAL_SPA, 1, EntityEquipmentSlot.LEGS);
	public static final Item SPA_BOOTS = new MagickBoots("spa_boots", ARMOR_MATERIAL_SPA, 1, EntityEquipmentSlot.FEET);
	
	public static final Item SORCERER_HELMET = new SorcererHelmet("sorcerer_helmet", ARMOR_MATERIAL_SORCERER, 1, EntityEquipmentSlot.HEAD);
	public static final Item SORCERER_CHEST = new SorcererChest("sorcerer_chest", ARMOR_MATERIAL_SORCERER, 1, EntityEquipmentSlot.CHEST);
	public static final Item SORCERER_LEGGINGS = new SorcererLegs("sorcerer_leggings", ARMOR_MATERIAL_SORCERER, 1, EntityEquipmentSlot.LEGS);
	public static final Item SORCERER_BOOTS = new SorcererBoot("sorcerer_boots", ARMOR_MATERIAL_SORCERER, 1, EntityEquipmentSlot.FEET);
	
	public static final Item CHLOROPHYTE_HELMET = new ChlorophyteHelmet("chlorophyte_helmet", ARMOR_MATERIAL_CHLOROPHYTE, 1, EntityEquipmentSlot.HEAD);
	public static final Item CHLOROPHYTE_CHEST = new ChlorophyteChest("chlorophyte_chest", ARMOR_MATERIAL_CHLOROPHYTE, 1, EntityEquipmentSlot.CHEST);
	public static final Item CHLOROPHYTE_LEGGINGS = new ChlorophyteLeggings("chlorophyte_leggings", ARMOR_MATERIAL_CHLOROPHYTE, 1, EntityEquipmentSlot.LEGS);
	public static final Item CHLOROPHYTE_BOOTS = new ChlorophyteBoot("chlorophyte_boots", ARMOR_MATERIAL_CHLOROPHYTE, 1, EntityEquipmentSlot.FEET);
	
	public static final Item OCEAN_HELMET = new OceanHelmet("ocean_helmet", ARMOR_MATERIAL_OCEAN, 1, EntityEquipmentSlot.HEAD);
	public static final Item OCEAN_CHEST = new OceanChest("ocean_chest", ARMOR_MATERIAL_OCEAN, 1, EntityEquipmentSlot.CHEST);
	public static final Item OCEAN_LEGGINGS = new OceanLegs("ocean_leggings", ARMOR_MATERIAL_OCEAN, 1, EntityEquipmentSlot.LEGS);
	public static final Item OCEAN_BOOTS = new OceanBoot("ocean_boots", ARMOR_MATERIAL_OCEAN, 1, EntityEquipmentSlot.FEET);
	
	public static final Item SHADOW_SKY_HELMET = new ShadowSkyHelmet("shadow_sky_helmet", ARMOR_MATERIAL_SHADOW, 1, EntityEquipmentSlot.HEAD);
	public static final Item SHADOW_SKY_CHEST = new ShadowSkyChest("shadow_sky_chest", ARMOR_MATERIAL_SHADOW, 1, EntityEquipmentSlot.CHEST);
	public static final Item SHADOW_SKY_LEGGINGS = new ShadowSkyLegs("shadow_sky_leggings", ARMOR_MATERIAL_SHADOW, 1, EntityEquipmentSlot.LEGS);
	public static final Item SHADOW_SKY_BOOTS = new ShadowSkyBoot("shadow_sky_boots", ARMOR_MATERIAL_SHADOW, 1, EntityEquipmentSlot.FEET);
	
	//Accessories
	public static final Item A_LAST_STRUGGLE = new ALastStruggle("last_struggle");
	public static final Item A_MADE_IN_HEAVEN = new AMadeInHeaven("made_in_heaven");
	public static final Item RECOVERY_THING = new AMagicCaster("item_recovery");
	public static final Item VirtulBody = new AVirtulBody("virtul_body");
}
