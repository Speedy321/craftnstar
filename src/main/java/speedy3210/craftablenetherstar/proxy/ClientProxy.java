package speedy3210.craftablenetherstar.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import speedy3210.craftablenetherstar.CraftableNetherStar;

public class ClientProxy extends CommonProxy {
    @Override
    public void load(FMLInitializationEvent e) {
        super.load(e);
        
    	Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(CraftableNetherStar.starBranch, 0, new ModelResourceLocation("craftnstar:starBranch", "inventory"));
    	Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(CraftableNetherStar.starCore, 0, new ModelResourceLocation("craftnstar:starCore", "inventory"));
    	Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(CraftableNetherStar.bottledTear, 0, new ModelResourceLocation("craftnstar:bottledTear", "inventory"));
    }
}
