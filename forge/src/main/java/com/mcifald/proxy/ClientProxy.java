package com.mcifald.proxy;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import com.mcifald.McIfald;
import com.mcifald.entity.EntityCyclops;
import com.mcifald.model.ModelCyclops;
import com.mcifald.renderer.ItemRenderObsidianTable;
import com.mcifald.renderer.RenderCyclops;
import com.mcifald.renderer.RenderObsidianTable;
import com.mcifald.tileentity.TileEntityObsidianTable;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

	public void registerRenderThings() {
		//ObsidianTable
		TileEntitySpecialRenderer render = new RenderObsidianTable();
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityObsidianTable.class, render);
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(McIfald.blockObsidianTable), new ItemRenderObsidianTable(render, new TileEntityObsidianTable()));
		
		//Entities
		RenderingRegistry.registerEntityRenderingHandler(EntityCyclops.class, new RenderCyclops(new ModelCyclops(), 0.3F));
	}
	
	public void registerTileEntitySpecialRenderer() {
		
	}
}
