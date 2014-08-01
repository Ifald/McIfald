package com.mcifald.armor;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.world.World;
import com.mcifald.McIfald;

public class TopazArmor extends ItemArmor {

	public TopazArmor(ArmorMaterial material, int id,
			int slot) {
		super(material, id, slot);
		this.setCreativeTab(McIfald.mcIfaldArmorTab);

		if(slot == 0) {
			this.setTextureName(McIfald.modid + ":TopazHelm");
		}else if (slot == 1) {
			this.setTextureName(McIfald.modid + ":TopazChest");
		}else if (slot == 2) {
			this.setTextureName(McIfald.modid + ":TopazLegs");
		}else if (slot == 3) {
			this.setTextureName(McIfald.modid + ":TopazBoots");
		}	
	}
		
	public String getArmorTexture(ItemStack itemstack, Entity entity, int slot, String type) {
		if (itemstack.getItem() == McIfald.armorTopazHelm || itemstack.getItem() == McIfald.armorTopazChest || itemstack.getItem() == McIfald.armorTopazBoots) {  
				return McIfald.modid + ":textures/model/armor/topaz_layer_1.png";
		}else if (itemstack.getItem() == McIfald.armorTopazLegs) {  
			return McIfald.modid + ":textures/model/armor/topaz_layer_2.png";
		}else{
			return null;
		}
	}
	
	public void onCreated(ItemStack itemstack, World world, EntityPlayer entityplayer) {
		
		if (itemstack.getItem() == McIfald.armorTopazHelm) {
			itemstack.addEnchantment(Enchantment.aquaAffinity, 2);
		}else if (itemstack.getItem() == McIfald.armorTopazChest) {
			itemstack.addEnchantment(Enchantment.fireProtection, 4);
		}else if (itemstack.getItem() == McIfald.armorTopazLegs) {
			itemstack.addEnchantment(Enchantment.thorns, 5);
		}else if (itemstack.getItem() == McIfald.armorTopazBoots) {
			itemstack.addEnchantment(Enchantment.unbreaking, 4);
		}
	}
}
