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
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameData;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = CraftableNetherStar.MODID, name = CraftableNetherStar.NAME, version = CraftableNetherStar.VERSION)
public class CraftableNetherStar
{
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
    ItemStack crLapis = new ItemStack(Items.dye);
    ItemStack crCacGreen = new ItemStack(Items.dye);
    
    @Instance(value = CraftableNetherStar.MODID)
    public static CraftableNetherStar instance;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	crLapis.setItemDamage(4);
    	crCacGreen.setItemDamage(2);
    	
    	starBranch = new ItemSimpleFoiled().setMaxStackSize(64).setCreativeTab(CreativeTabs.tabMaterials).setUnlocalizedName("starBranch");
    	starCore = new ItemSimpleFoiled().setMaxStackSize(16).setCreativeTab(CreativeTabs.tabMaterials).setUnlocalizedName("starCore");
    	bottledTear = new Item().setMaxStackSize(16).setCreativeTab(CreativeTabs.tabMaterials).setUnlocalizedName("bottledTear");
    	
    	GameRegistry.registerItem(starBranch, "starBranch");
    	GameRegistry.registerItem(starCore, "starCore");
    	GameRegistry.registerItem(bottledTear, "bottledTear");
    	    	
    	GameRegistry.addRecipe(new ItemStack(Items.nether_star), "xyx", "yzy", "xyx", 'x', crGlowstoneBlock, 'y', new ItemStack(starBranch), 'z', new ItemStack(starCore));
    	GameRegistry.addRecipe(new ItemStack(starCore), "gdg", "ded", "gdg", 'g', crGlowstoneBlock, 'd', crDiamondBlock, 'e', new ItemStack(Blocks.emerald_block));
    	GameRegistry.addRecipe(new ItemStack(starBranch), " d ", "dgd", "dgd", 'g', crGlowstoneBlock, 'd', crDiamondBlock);
    	
    	GameRegistry.addRecipe(new ItemStack(Items.blaze_rod), " b ", "bsb", " b ", 'b', crBlazePwdr, 's', new ItemStack(Items.stick));
    	GameRegistry.addRecipe(new ItemStack(Items.ghast_tear), "btb", "tqt", "btb", 'b', crBlazePwdr, 't', new ItemStack(bottledTear), 'q', new ItemStack(Items.quartz));
    	GameRegistry.addRecipe(new ItemStack(Items.ender_pearl), "glg", "lql", "glg", 'g', crCacGreen, 'l', crLapis, 'q', new ItemStack(Items.quartz));
    	
    	GameRegistry.addShapelessRecipe(new ItemStack(Items.blaze_powder), new ItemStack(Items.glowstone_dust), new ItemStack(Items.redstone));
    	GameRegistry.addShapelessRecipe(new ItemStack(bottledTear), new ItemStack(Items.water_bucket), new ItemStack(Items.glass_bottle), new ItemStack(Blocks.soul_sand));
    }
        
    @EventHandler
    public void load(FMLInitializationEvent event)
    {
    	Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(starBranch, 0, new ModelResourceLocation("craftnstar:starBranch", "inventory"));
    	Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(starCore, 0, new ModelResourceLocation("craftnstar:starCore", "inventory"));
    	Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(bottledTear, 0, new ModelResourceLocation("craftnstar:bottledTear", "inventory"));
    
    }
        
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    }
}
