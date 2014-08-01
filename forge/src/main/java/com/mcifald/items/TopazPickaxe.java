package com.mcifald.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemPickaxe;
import com.mcifald.McIfald;

public class TopazPickaxe extends ItemPickaxe {

	public TopazPickaxe(ToolMaterial p_i45347_1_) {
		super(p_i45347_1_);
		this.setCreativeTab(McIfald.mcIfaldToolsTab);
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		this.itemIcon = iconRegister.registerIcon(McIfald.modid + ":" + this.getUnlocalizedName().substring(5));
	}
	

}
