package speedy3210.craftablenetherstar;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSimpleFoiled;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = CraftableNetherStar.MODID, name = CraftableNetherStar.NAME, version = CraftableNetherStar.VERSION)
public class CraftableNetherStar
{
    public static final String MODID = "craftnstar";
    public static final String VERSION = "0.3.0";
    public static final String NAME = "Craftable Nether Star +";
    
    public static Item starBranch;
    public static Item starCore;
    
    @Instance(value = CraftableNetherStar.MODID)
    public static CraftableNetherStar instance;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	starBranch = new Item().setMaxStackSize(64).setCreativeTab(CreativeTabs.tabMaterials).setUnlocalizedName("starBranch");
    	starCore = new Item().setMaxStackSize(16).setCreativeTab(CreativeTabs.tabMaterials).setUnlocalizedName("starCore");
    	GameRegistry.registerItem(starBranch, "starBranch");
    	GameRegistry.registerItem(starCore, "starCore");
    	
    }
        
    @EventHandler
    public void load(FMLInitializationEvent event)
    {

    	Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(starBranch, 0, new ModelResourceLocation("craftnstar:starBranch", "inventory"));
    	Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(starCore, 0, new ModelResourceLocation("craftnstar:starCore", "inventory"));
    }
        
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    }
}
