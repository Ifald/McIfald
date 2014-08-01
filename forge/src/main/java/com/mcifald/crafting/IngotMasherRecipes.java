package com.mcifald.crafting;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import com.mcifald.McIfald;

public class IngotMasherRecipes {

	public IngotMasherRecipes() {
		
	}
	
	public static ItemStack getMashingResult(Item item, Item item2) {
		return getOutput(item, item2);
	}
	
	public static ItemStack getOutput(Item item, Item item2) {
		//Recipe One
		if (item == McIfald.itemManganeseIngot && item2 == Items.iron_ingot || item == Items.iron_ingot && item2 == McIfald.itemManganeseIngot) {
			return new ItemStack(McIfald.itemVanadiumIngot, 2);
		}
		//Тестовий рецепт
		if (item == McIfald.itemManganeseIngot && item2 == Items.gold_ingot || item == Items.gold_ingot && item2 == McIfald.itemManganeseIngot) {
			return new ItemStack(McIfald.itemVanadiumIngot, 2);
		}
		
		return null;
	}
	
}
