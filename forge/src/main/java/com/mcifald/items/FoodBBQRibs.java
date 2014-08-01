package com.mcifald.items;

import net.minecraft.item.ItemFood;
import com.mcifald.McIfald;

public class FoodBBQRibs extends ItemFood {

	public FoodBBQRibs(int heal, float saturation, boolean wolfmeat) {
		super(heal, saturation, wolfmeat);

		this.setPotionEffect(8, 60, 1, 1F);
		this.setPotionEffect(10, 60, 1, 1F);
		this.setTextureName(McIfald.modid + ":BBQRibs");
		this.setCreativeTab(McIfald.mcIfaldTab);
	}
	
}
