package com.mcifald.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockStaticLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import com.mcifald.McIfald;

public class NCBlockStaticLiquid extends BlockStaticLiquid {

	public NCBlockStaticLiquid(Material material) {
		super(material);
		
		this.setCreativeTab(McIfald.mcIfaldTab);
	}

}
