package com.mcifald.handler;

import net.minecraft.item.ItemStack;
import com.mcifald.McIfald;
import cpw.mods.fml.common.IFuelHandler;

public class FuelHandler implements IFuelHandler {

	@Override
	public int getBurnTime(ItemStack fuel) {
		
		if(fuel.getItem() == McIfald.itemTreePitch) return 800;
		
		return 0;
	}

}
