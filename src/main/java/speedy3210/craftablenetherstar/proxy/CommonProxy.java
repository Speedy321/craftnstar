package speedy3210.craftablenetherstar.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import speedy3210.craftablenetherstar.CraftableNetherStar;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent e) {
        // Initialization of blocks and items typically goes here:
    	CraftableNetherStar.createItems();
    	CraftableNetherStar.addRecipes();
    }

    public void load(FMLInitializationEvent event) {

    }

    public void postInit(FMLPostInitializationEvent e) {

    }
}