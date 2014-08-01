package com.mcifald.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import com.mcifald.McIfald;

public class CopperBlock extends Block {

	public CopperBlock(Material material) {
		super(material);
		
		this.setStepSound(soundTypeMetal);
		this.setCreativeTab(McIfald.mcIfaldBlockTab);
	}
	
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		this.blockIcon = iconRegister.registerIcon(McIfald.modid + ":" + this.getUnlocalizedName().substring(5));
	}
}
