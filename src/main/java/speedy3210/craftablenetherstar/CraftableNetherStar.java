package speedy3210.craftablenetherstar;

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
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameData;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = CraftableNetherStar.MODID, name = CraftableNetherStar.NAME, version = CraftableNetherStar.VERSION)
public class CraftableNetherStar{
	
	public static Configuration config;
	
	public static String recipeType = "NORMAL";
	public static boolean starRecipe = true;
    public static boolean blazeRodRecipe = true;
    public static boolean ghastTearRecipe = true;
    public static boolean blazePowderRecipe = true;
    public static boolean enderPearlRecipe = true;
	
    public static final String MODID = "craftnstar";
    public static final String VERSION = "0.3.0";
    public static final String NAME = "Craftable Nether Star +";
    
    public static Item starBranch;
    public static Item starCore;
    public static Item bottledTear;
    
    ItemStack crDiamondBlock = new ItemStack(Blocks.diamond_block);
    ItemStack crGlowstoneBlock = new ItemStack(Blocks.glowstone);
    ItemStack crDiamond = new ItemStack(Items.diamond);
    ItemStack crBlazePwdr = new ItemStack(Items.blaze_powder);
    ItemStack crBlazeRod = new ItemStack(Items.blaze_rod);
    ItemStack crLapis = new ItemStack(Items.dye);
    ItemStack crCacGreen = new ItemStack(Items.dye);
    ItemStack crGlowPwdr =  new ItemStack(Items.glowstone_dust);
    
    @Instance(value = CraftableNetherStar.MODID)
    public static CraftableNetherStar instance;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
    	
    	config = new Configuration(event.getSuggestedConfigurationFile());
    	syncConfig();
    	
    	crLapis.setItemDamage(4);
    	crCacGreen.setItemDamage(2);
    	
    	createItems();
    	addRecipes();
    	
    }
        
    @EventHandler
    public void load(FMLInitializationEvent event){
    	    	
    	Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(starBranch, 0, new ModelResourceLocation("craftnstar:starBranch", "inventory"));
    	Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(starCore, 0, new ModelResourceLocation("craftnstar:starCore", "inventory"));
    	Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(bottledTear, 0, new ModelResourceLocation("craftnstar:bottledTear", "inventory"));
    
    }
        
    @EventHandler
    public void postInit(FMLPostInitializationEvent event){
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
    	
    	if (config.hasChanged()) config.save();	
    	
    	System.out.println("[CraftNS] Config Reloaded!");
	}
    
    private void addRecipes(){
    	
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
    		GameRegistry.addRecipe(new ItemStack(Items.ender_pearl), "glg", "lql", "glg", 'g', crCacGreen, 'l', crLapis, 'q', new ItemStack(Items.quartz));
    	
    	if(blazePowderRecipe)
    		GameRegistry.addShapelessRecipe(new ItemStack(Items.blaze_powder), new ItemStack(Items.glowstone_dust), new ItemStack(Items.redstone));
    		
    }
    
    private void createItems(){
    	 
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
