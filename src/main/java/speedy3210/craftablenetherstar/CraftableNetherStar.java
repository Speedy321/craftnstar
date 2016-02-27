package speedy3210.craftablenetherstar;

import com.typesafe.config.Config;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSimpleFoiled;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameData;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import speedy3210.craftablenetherstar.proxy.CommonProxy;

@Mod(modid = CraftableNetherStar.MODID, name = CraftableNetherStar.NAME, version = CraftableNetherStar.VERSION)
public class CraftableNetherStar{
	
	@SidedProxy(clientSide = "speedy3210.craftablenetherstar.proxy.ClientProxy", serverSide = "speedy3210.craftablenetherstar.proxy.ServerProxy")
    public static CommonProxy proxy;
	
	public static Configuration config;
	
	public static String recipeType = "NORMAL";
	public static boolean starRecipe = true;
    public static boolean blazeRodRecipe = true;
    public static boolean ghastTearRecipe = true;
    public static boolean blazePowderRecipe = true;
    public static boolean enderPearlRecipe = true;
    public static boolean slimeBallRecipe = true;
	public static boolean stringRecipe = true;
	public static boolean boneRecipes = true;
	public static boolean nameTagRecipe = true;
	public static boolean saddleRecipe = true;
    
    public static final String MODID = "craftnstar";
    public static final String VERSION = "0.3.0";
    public static final String NAME = "Craftable Nether Star +";
    
    public static Item starBranch;
    public static Item starCore;
    public static Item bottledTear;
    
    static ItemStack crDiamondBlock = new ItemStack(Blocks.diamond_block);
    static ItemStack crGlowstoneBlock = new ItemStack(Blocks.glowstone);
    static ItemStack crDiamond = new ItemStack(Items.diamond);
    static ItemStack crBlazePwdr = new ItemStack(Items.blaze_powder);
    static ItemStack crBlazeRod = new ItemStack(Items.blaze_rod);
    static ItemStack crGlowPwdr =  new ItemStack(Items.glowstone_dust);
    static ItemStack crFlint = new ItemStack(Items.flint);
    static ItemStack crString = new ItemStack(Items.string);
    
    @Instance(value = CraftableNetherStar.MODID)
    public static CraftableNetherStar instance;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
    	
    	config = new Configuration(event.getSuggestedConfigurationFile());
    	syncConfig();
    	
    	proxy.preInit(event);
    }
        
    @EventHandler
    public void load(FMLInitializationEvent event){
    	proxy.load(event);
    }
        
    @EventHandler
    public void postInit(FMLPostInitializationEvent event){
    	proxy.postInit(event);
    }
    
    @SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
		if (eventArgs.modID.equals(MODID)) {
			syncConfig();
		}
	}
    
    private void syncConfig() {
    	
    	config.load();
    	
    	starRecipe = config.getBoolean("Enable Nether Star Recipe",	Configuration.CATEGORY_GENERAL, starRecipe, "true = enabled");
    	recipeType = config.getString("Nether Star Recipe Type", Configuration.CATEGORY_GENERAL, recipeType, "Crafting recipes: NORMAL, HARD, NETHER, random values will give default recipes.");
    	blazeRodRecipe = config.getBoolean("Enable Blaze Rod Recipe", Configuration.CATEGORY_GENERAL, blazeRodRecipe, "true = enabled");
    	ghastTearRecipe = config.getBoolean("Enable Ghast Tear Recipe", Configuration.CATEGORY_GENERAL, ghastTearRecipe, "true = enabled");
    	blazePowderRecipe = config.getBoolean("Enable Blaze Powder Recipe", Configuration.CATEGORY_GENERAL, blazePowderRecipe, "true = enabled");
    	enderPearlRecipe = config.getBoolean("Enable EnderPearl Recipe", Configuration.CATEGORY_GENERAL, enderPearlRecipe, "true = enabled");
    	slimeBallRecipe = config.getBoolean("Enable Slime Ball Recipe", Configuration.CATEGORY_GENERAL, slimeBallRecipe, "true = enabled");
    	stringRecipe = config.getBoolean("Enable String Recipe", Configuration.CATEGORY_GENERAL, stringRecipe, "true = enabled");
    	boneRecipes = config.getBoolean("Enable Bone Recipes", Configuration.CATEGORY_GENERAL, boneRecipes, "true = enabled");
    	nameTagRecipe = config.getBoolean("Enable Name Tag Recipe", Configuration.CATEGORY_GENERAL, nameTagRecipe, "true = enabled");
    	saddleRecipe = config.getBoolean("Enable Saddle Recipe", Configuration.CATEGORY_GENERAL, saddleRecipe, "true = enabled");
    	
    	if (config.hasChanged()) config.save();	
    	
    	System.out.println("[CraftNS] Config Reloaded!");
	}
    
    public static void addRecipes(){
    	
    	//star recipes
    	if(starRecipe){
    		if(recipeType == "NETHER"){
    			GameRegistry.addRecipe(new ItemStack(starBranch), " d ", "dgd", "dgd", 'g', crGlowPwdr, 'd', crBlazeRod);
    			GameRegistry.addRecipe(new ItemStack(Items.nether_star), "xyx", "yzy", "xyx", 'x', crBlazePwdr, 'y', new ItemStack(starBranch), 'z', new ItemStack(starCore));
    			GameRegistry.addRecipe(new ItemStack(starCore), "gdg", "ded", "gdg", 'g', crGlowPwdr, 'd', crBlazeRod, 'e', new ItemStack(Blocks.emerald_block));
    		}else{
    			if(recipeType == "HARD"){
    				GameRegistry.addRecipe(new ItemStack(starBranch), " d ", "dgd", "dgd", 'g', crGlowstoneBlock, 'd', crDiamondBlock);
    			}else{
    				GameRegistry.addRecipe(new ItemStack(starBranch), " d ", "dgd", "dgd", 'g', crGlowstoneBlock, 'd', crDiamond);
    			}
    			GameRegistry.addRecipe(new ItemStack(Items.nether_star), "xyx", "yzy", "xyx", 'x', crGlowstoneBlock, 'y', new ItemStack(starBranch), 'z', new ItemStack(starCore));
    			GameRegistry.addRecipe(new ItemStack(starCore), "gdg", "ded", "gdg", 'g', crGlowstoneBlock, 'd', crDiamondBlock, 'e', new ItemStack(Blocks.emerald_block));
    		}
    	}
    	if(blazeRodRecipe)
    		GameRegistry.addRecipe(new ItemStack(Items.blaze_rod), " b ", "bsb", " b ", 'b', crBlazePwdr, 's', new ItemStack(Items.stick));
    	if(ghastTearRecipe){
    		GameRegistry.addRecipe(new ItemStack(Items.ghast_tear), "btb", "tqt", "btb", 'b', crBlazePwdr, 't', new ItemStack(bottledTear), 'q', new ItemStack(Items.quartz));
    		GameRegistry.addShapelessRecipe(new ItemStack(bottledTear), new ItemStack(Items.water_bucket), new ItemStack(Items.glass_bottle), new ItemStack(Blocks.soul_sand));
    	}
    	if(enderPearlRecipe)
    		GameRegistry.addRecipe(new ItemStack(Items.ender_pearl), "glg", "lql", "glg", 'g', new ItemStack(Items.dye, 1, 2), 'l', new ItemStack(Items.dye, 1, 4), 'q', new ItemStack(Items.quartz));
    	if(blazePowderRecipe)
    		GameRegistry.addShapelessRecipe(new ItemStack(Items.blaze_powder), new ItemStack(Items.glowstone_dust), new ItemStack(Items.redstone));
    	if(slimeBallRecipe)
    		GameRegistry.addShapelessRecipe(new ItemStack(Items.slime_ball), new ItemStack(Items.water_bucket), new ItemStack(Items.clay_ball), new ItemStack(Items.dye, 1, 10));
    	if(stringRecipe)
    		GameRegistry.addShapedRecipe(new ItemStack(Items.string), " c", "c ", 'c', new ItemStack(Items.reeds));
    	if(boneRecipes){
    		GameRegistry.addShapelessRecipe(new ItemStack(Items.bone), crFlint, new ItemStack(Items.chicken));
    		GameRegistry.addShapelessRecipe(new ItemStack(Items.bone), crFlint, new ItemStack(Items.cooked_chicken));
    		GameRegistry.addShapelessRecipe(new ItemStack(Items.bone), crFlint, new ItemStack(Items.rabbit));
    		GameRegistry.addShapelessRecipe(new ItemStack(Items.bone), crFlint, new ItemStack(Items.cooked_rabbit));
    		GameRegistry.addShapelessRecipe(new ItemStack(Items.bone), crFlint, new ItemStack(Items.fish, 1, OreDictionary.WILDCARD_VALUE));
    		GameRegistry.addShapelessRecipe(new ItemStack(Items.bone), crFlint, new ItemStack(Items.cooked_fish, 1, OreDictionary.WILDCARD_VALUE));
    	}
    	if(nameTagRecipe)
    		GameRegistry.addRecipe(new ItemStack(Items.name_tag), "  s", " p ", "p  ", 's', crString, 'p', new ItemStack(Items.paper));
    	if(saddleRecipe)
    		GameRegistry.addRecipe(new ItemStack(Items.saddle), "hwh", "hhh", "sis", 'h', new ItemStack(Items.leather), 'w', new ItemStack(Blocks.wool, 1, OreDictionary.WILDCARD_VALUE), 's', crString, 'i', new ItemStack(Items.iron_ingot));
    		
    }
    
    public static void createItems(){
    	 
    	if(starRecipe){
    		starBranch = new ItemSimpleFoiled().setMaxStackSize(64).setCreativeTab(CreativeTabs.tabMaterials).setUnlocalizedName("starBranch");
    		starCore = new ItemSimpleFoiled().setMaxStackSize(16).setCreativeTab(CreativeTabs.tabMaterials).setUnlocalizedName("starCore");

        	GameRegistry.registerItem(starBranch, "starBranch");
        	GameRegistry.registerItem(starCore, "starCore");
    	}
    	if(ghastTearRecipe){
    		bottledTear = new Item().setMaxStackSize(16).setCreativeTab(CreativeTabs.tabMaterials).setUnlocalizedName("bottledTear");
    		GameRegistry.registerItem(bottledTear, "bottledTear");
    	}
    	
    }
}
