package com.mcifald.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import com.mcifald.McIfald;
import com.mcifald.container.ContainerAlabasterOven;
import com.mcifald.container.ContainerIngotMasher;
import com.mcifald.container.ContainerWorkSurface;
import com.mcifald.gui.GuiAlabasterOven;
import com.mcifald.gui.GuiIngotMasher;
import com.mcifald.gui.GuiWorkSurface;
import com.mcifald.tileentity.TileEntityAlabasterOven;
import com.mcifald.tileentity.TileEntityIngotMasher;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,	int x, int y, int z) {
		TileEntity entity = world.getTileEntity(x, y, z);
		
		if(entity != null) {
			switch(ID) {
			case McIfald.guiIDAlabasterOven:
				if (entity instanceof TileEntityAlabasterOven) {
					return new ContainerAlabasterOven(player.inventory, (TileEntityAlabasterOven) entity);
				}
				return null;
				
			case McIfald.guiIDIngotMasher:
				if (entity instanceof TileEntityIngotMasher) {
					return new ContainerIngotMasher(player.inventory, (TileEntityIngotMasher) entity);
				}
				return null;
			}
		}
		
		if(ID == McIfald.guiIDWorkSurface) {
			return ID == McIfald.guiIDWorkSurface && world.getBlock(x, y, z) == McIfald.blockWorkSurface ? new ContainerWorkSurface(player.inventory, world, x, y, z) : null;
		}
		
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,	int x, int y, int z) {
		TileEntity entity = world.getTileEntity(x, y, z);
		
		if(entity != null) {
			switch(ID) {
			case McIfald.guiIDAlabasterOven:
				if (entity instanceof TileEntityAlabasterOven) {
					return new GuiAlabasterOven(player.inventory, (TileEntityAlabasterOven) entity);
				}
				return null;
				
			case McIfald.guiIDIngotMasher:
				if (entity instanceof TileEntityIngotMasher) {
					return new GuiIngotMasher(player.inventory, (TileEntityIngotMasher) entity);
				}
				return null;
			}
		}
		
		if(ID == McIfald.guiIDWorkSurface) {
			return ID == McIfald.guiIDWorkSurface && world.getBlock(x, y, z) == McIfald.blockWorkSurface ? new GuiWorkSurface(player.inventory, world, x, y, z) : null;
		}
		
		return null;
	}

}
