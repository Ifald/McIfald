package com.mcifald;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDynamicLiquid;
import net.minecraft.block.BlockStaticLiquid;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;

import com.mcifald.armor.TopazArmor;
import com.mcifald.blocks.*;
import com.mcifald.crafting.RecipeRemover;
import com.mcifald.entity.EntityCyclops;
import com.mcifald.handler.*;
import com.mcifald.items.*;
import com.mcifald.proxy.CommonProxy;
import com.mcifald.tileentity.TileEntityAlabasterOven;
import com.mcifald.tileentity.TileEntityIngotMasher;
import com.mcifald.worldgen.McIfaldWorldGen;
import com.mcifald.worldgen.McIfaldWorldGen;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


@Mod(modid = McIfald.modid, version = McIfald.version)
public class McIfald {
	
	public static final String modid = "McIfald";
	public static final String version = "DEV v0.2";
	
	McIfaldWorldGen eventWorldGen = new McIfaldWorldGen();
		
	public static CreativeTabs mcIfaldTab;
	public static CreativeTabs mcIfaldBlockTab;
	public static CreativeTabs mcIfaldToolsTab;
	public static CreativeTabs mcIfaldArmorTab;
	public static CreativeTabs mcIfaldMachineTab;
	public static CreativeTabs mcIfaldPlantsTab;
	
	public static ToolMaterial TopazMaterial = EnumHelper.addToolMaterial("TopazMaterial", 2, 750, 6.0F, 2.0F, 10);
	public static ArmorMaterial TopazArmorMaterial = EnumHelper.addArmorMaterial("TopazArmorMaterial", 24, new int[] {3, 7, 5, 3}, 10);
	
	@Instance(modid)
    public static McIfald instance;
	
	public static Item itemCopperIngot;
	public static Item itemTinIngot;
	public static Item itemZincIngot;
	public static Item itemNickelIngot;
	public static Item itemManganeseIngot;
	public static Item itemVanadiumIngot;
	
	public static Item itemTinCog;
	public static Item itemIronWasher;
	public static Item itemIronDisc;
	public static Item itemIronHammer;
	public static Item itemIronPunch;
	
	public static Item itemTreePitch;
	
	public static Item itemStaff;
	
	//����
	public static Block oreAmethystOre;
	public static Block oreBerylOre;
	public static Block oreCopperOre;
	public static Block oreGarnetOre;
	public static Block oreJadeOre;
	public static Block oreManganeseOre;
	public static Block oreNickelOre;
	public static Block oreSapphireOre;
	public static Block oreSilverOre;
	public static Block oreTinOre;
	public static Block oreTopazOre;
	public static Block oreTsavoriteOre;
	public static Block oreZincOre;
	public static Block oreVanadiumOre;


	
	public static Block oreNetherCopperOre;
	public static Block oreNetherTinOre;
	public static Block oreNetherZincOre;
	public static Block oreNetherNickelOre;
	public static Block oreNetherManganeseOre;
	public static Block oreNetherVanadiumOre;
	public static Block oreNetherTopazOre;
	
	public static Block blockCopperBlock;
	public static Block blockMinerals;
	public static Block blockObsidianTable;
	
	public static Block blockPurpleLampOn;
	public static Block blockPurpleLampOff;
	
	public static Item itemTopaz;

	public static Item itemTopazSword;
	public static Item itemTopazAxe;
	public static Item itemTopazShovel;
	public static Item itemTopazHoe;
	public static Item itemTopazPickaxe;
	
	public static int armorTopazHelmID;
	public static int armorTopazChestID;
	public static int armorTopazLegsID;
	public static int armorTopazBootsID;
	
	public static Item armorTopazHelm;
	public static Item armorTopazChest;
	public static Item armorTopazLegs;
	public static Item armorTopazBoots;
	
	public static Item foodHotDog;
	public static Item foodBBQRibs;
	
	//Crops
	public static Item cropStrawberrySeeds;
	public static Item cropStrawberry;
	public static Block cropStrawberryPlant;
	
	public static Item cropBloodMelonSeeds;
	public static Item cropBloodMelonSlice;
	public static Block cropBloodMelonStem;
	public static Block cropBloodMelon;
	
	//FLuids
	public static Block fluidSludgeStill;
	public static Block fluidSludgeFlowing;
	public static Fluid fluidSludge;
	
	public static MaterialLiquid materialSludge;
		
	//Machines
	public static Block blockAlabasterOvenIdle;
	public static Block blockAlabasterOvenActive;
	public static final int guiIDAlabasterOven = 0;
	
	public static Block blockIngotMasherIdle;
	public static Block blockIngotMasherActive;
	public static final int guiIDIngotMasher = 1;
	
	public static Block blockWorkSurface;
	public static final int guiIDWorkSurface = 1;
	
	@SidedProxy(clientSide = "com.mcifald.proxy.ClientProxy", serverSide = "com.mcifald.proxy.CommonProxy")
	public static CommonProxy mcifaldProxy;
	
	@EventHandler
	public void PreInit(FMLPreInitializationEvent preEvent){
		
		mcIfaldTab = new CreativeTabs("McIfald") {
			@SideOnly(Side.CLIENT)
			public Item getTabIconItem() {
				return Item.getItemFromBlock(McIfald.blockObsidianTable);
			}
		};
		
		mcIfaldBlockTab = new CreativeTabs("McIfaldBlock") {
			@SideOnly(Side.CLIENT)
			public Item getTabIconItem() {
				return Item.getItemFromBlock(McIfald.blockPurpleLampOn);
			}
		};
		
		mcIfaldToolsTab = new CreativeTabs("McIfaldTools") {
			@SideOnly(Side.CLIENT)
			public Item getTabIconItem() {
				return Item.getItemFromBlock(McIfald.blockWorkSurface);
			}
		};
		
		mcIfaldArmorTab = new CreativeTabs("McIfaldArmor") {
			@SideOnly(Side.CLIENT)
			public Item getTabIconItem() {
				return Item.getItemFromBlock(McIfald.oreCopperOre);
			}
		};
			
		mcIfaldMachineTab = new CreativeTabs("McIfaldMachine") {
			@SideOnly(Side.CLIENT)
			public Item getTabIconItem() {
				return Item.getItemFromBlock(McIfald.blockIngotMasherActive);
			}
		};
				
		mcIfaldPlantsTab = new CreativeTabs("McIfaldPlants") {
			@SideOnly(Side.CLIENT)
			public Item getTabIconItem() {
				return Item.getItemFromBlock(McIfald.cropBloodMelon);
			}
		};

		
		//Initializations
		
		//Items
		itemCopperIngot = new NCItems().setUnlocalizedName("CopperIngot");
		itemTinIngot = new NCItems().setUnlocalizedName("TinIngot");
		itemZincIngot = new NCItems().setUnlocalizedName("ZincIngot");
		itemNickelIngot = new NCItems().setUnlocalizedName("NickelIngot");
		itemManganeseIngot = new NCItems().setUnlocalizedName("ManganeseIngot");
		itemVanadiumIngot = new NCItems().setUnlocalizedName("VanadiumIngot");
		
		//Item Parts
		itemTinCog = new NCItems().setUnlocalizedName("TinCog");
		itemIronWasher = new NCItems().setUnlocalizedName("IronWasher");
		itemIronDisc = new NCItems().setUnlocalizedName("IronDisc");
		itemIronHammer = new IronHammer().setUnlocalizedName("IronHammer");
		itemIronPunch = new IronPunch().setUnlocalizedName("IronPunch");
		
		//Custom Fuel and Tree Drop
		itemTreePitch = new NCItems().setUnlocalizedName("TreePitch");
		
		//5x5 Item
		itemStaff = new NCItems().setUnlocalizedName("Staff");
		
		//Gems
		itemTopaz = new NCItems().setUnlocalizedName("Topaz");
		
		//Food
		foodHotDog = new ItemFood(6, 0.6F, true).setUnlocalizedName("HotDog").setCreativeTab(mcIfaldTab).setTextureName(McIfald.modid + ":HotDog");
		foodBBQRibs = new FoodBBQRibs(10, 1.0F, true).setUnlocalizedName("BBQRibs");
		
		//Crops
		cropStrawberryPlant = new NCCrop().setBlockName("StrawberryPlant");
		cropStrawberrySeeds = new ItemSeeds(cropStrawberryPlant, Blocks.farmland).setUnlocalizedName("StrawberrySeeds").setTextureName(modid + ":StrawberrySeeds").setCreativeTab(mcIfaldPlantsTab);
		cropStrawberry = new ItemFood(4, 0.5F, false).setUnlocalizedName("Strawberry").setTextureName(modid + ":Strawberry").setCreativeTab(mcIfaldPlantsTab);
		
		cropBloodMelon = new BloodMelon().setBlockName("BloodMelon").setBlockTextureName(modid + ":BloodMelon");
		cropBloodMelonStem = new BlockNCStem(cropBloodMelon).setBlockName("BloodMelonStem").setBlockTextureName(modid + ":BloodMelon");
		cropBloodMelonSeeds = new ItemSeeds(cropBloodMelonStem, Blocks.farmland).setUnlocalizedName("BloodMelonSeeds").setTextureName(modid + ":BloodMelonSeeds").setCreativeTab(mcIfaldPlantsTab);
		cropBloodMelonSlice = new ItemFood(4, 0.5F, false).setUnlocalizedName("BloodMelonSlice").setTextureName(modid + ":BloodMelonSlice").setCreativeTab(mcIfaldPlantsTab);
		
		//Tools and Weapons
		itemTopazSword = new TopazSword(TopazMaterial).setUnlocalizedName("TopazSword");
		itemTopazAxe = new TopazAxe(TopazMaterial).setUnlocalizedName("TopazAxe");
		itemTopazShovel = new TopazShovel(TopazMaterial).setUnlocalizedName("TopazShovel");
		itemTopazHoe = new TopazHoe(TopazMaterial).setUnlocalizedName("TopazHoe");
		itemTopazPickaxe = new TopazPickaxe(TopazMaterial).setUnlocalizedName("TopazPickaxe");
		
		//Armor
		armorTopazHelm = new TopazArmor(TopazArmorMaterial, armorTopazHelmID, 0).setUnlocalizedName("TopazHelm");
		armorTopazChest = new TopazArmor(TopazArmorMaterial, armorTopazChestID, 1).setUnlocalizedName("TopazChest");
		armorTopazLegs = new TopazArmor(TopazArmorMaterial, armorTopazLegsID, 2).setUnlocalizedName("TopazLegs");
		armorTopazBoots = new TopazArmor(TopazArmorMaterial, armorTopazBootsID, 3).setUnlocalizedName("TopazBoots");
		
		//����
		oreAmethystOre = new OreBlock(Material.rock).setBlockName("AmethystOre");
		oreBerylOre = new OreBlock(Material.rock).setBlockName("BerylOre");
		oreCopperOre = new OreBlock(Material.rock).setBlockName("CopperOre");
		oreGarnetOre = new OreBlock(Material.rock).setBlockName("GarnetOre");
		oreJadeOre = new OreBlock(Material.rock).setBlockName("JadeOre");
		oreManganeseOre = new OreBlock(Material.rock).setBlockName("ManganeseOre");
		oreNickelOre = new OreBlock(Material.rock).setBlockName("NickelOre");		
		oreSapphireOre = new OreBlock(Material.rock).setBlockName("SapphireOre");
		oreSilverOre = new OreBlock(Material.rock).setBlockName("SilverOre");	
		oreTinOre = new OreBlock(Material.rock).setBlockName("TinOre");
		oreTopazOre = new OreBlock(Material.rock).setBlockName("TopazOre");
		oreTsavoriteOre = new OreBlock(Material.rock).setBlockName("TsavoriteOre");
		oreZincOre = new OreBlock(Material.rock).setBlockName("ZincOre");
		oreVanadiumOre = new OreBlock(Material.rock).setBlockName("VanadiumOre");		
		
		oreNetherCopperOre = new OreBlock(Material.rock).setBlockName("NetherCopperOre");
		oreNetherTinOre = new OreBlock(Material.rock).setBlockName("NetherTinOre");
		oreNetherZincOre = new OreBlock(Material.rock).setBlockName("NetherZincOre");
		oreNetherNickelOre = new OreBlock(Material.rock).setBlockName("NetherNickelOre");
		oreNetherManganeseOre = new OreBlock(Material.rock).setBlockName("NetherManganeseOre");
		oreNetherVanadiumOre = new OreBlock(Material.rock).setBlockName("NetherVanadiumOre");
		oreNetherTopazOre = new OreBlock(Material.rock).setBlockName("NetherTopazOre");
		
		//Blocks
		blockCopperBlock = new CopperBlock(Material.iron).setBlockName("CopperBlock");
		blockMinerals = new MineralBlocks().setBlockName("Minerals");
		blockObsidianTable = new ObsidianBlock(Material.rock).setBlockName("ObsidianTable");
		
		//Lamps
		blockPurpleLampOn = new PurpleLamp(true).setBlockName("PurpleLampOn");
		blockPurpleLampOff = new PurpleLamp(false).setBlockName("PurpleLampOff").setCreativeTab(mcIfaldMachineTab);
		
		//Fluids
		materialSludge = new MaterialLiquid(MapColor.greenColor);
		fluidSludgeStill = new NCBlockStaticLiquid(McIfald.materialSludge).setBlockName("SludgeStill");
		fluidSludgeFlowing = new NCBlockDynamicLiquid(McIfald.materialSludge).setBlockName("SludgeFlowing");
		
		
		
		//Machines
		blockAlabasterOvenIdle = new AlabasterOven(false).setBlockName("AlabasterOvenIdle").setCreativeTab(mcIfaldMachineTab).setHardness(3.5F);
		blockAlabasterOvenActive = new AlabasterOven(true).setBlockName("AlabasterOvenActive").setLightLevel(0.625F).setHardness(3.5F);
		
		blockIngotMasherIdle = new IngotMasher(false).setBlockName("IngotMasherIdle").setCreativeTab(mcIfaldMachineTab).setHardness(3.5F);
		blockIngotMasherActive = new IngotMasher(true).setBlockName("IngotMasherActive").setHardness(3.5F);
		
		//Crafting Tables
		blockWorkSurface = new WorkSurface().setBlockName("WorkSurface");

		//Registers
		//Items		
		GameRegistry.registerItem(itemCopperIngot, "CopperIngot");
		GameRegistry.registerItem(itemTinIngot, "TinIngot");
		GameRegistry.registerItem(itemZincIngot, "ZincIngot");
		GameRegistry.registerItem(itemNickelIngot, "NickelIngot");
		GameRegistry.registerItem(itemManganeseIngot, "ManganeseIngot");
		GameRegistry.registerItem(itemVanadiumIngot, "VanadiumIngot");
		
		GameRegistry.registerItem(itemTinCog, "TinCog");
		GameRegistry.registerItem(itemIronWasher, "IronWasher");
		GameRegistry.registerItem(itemIronDisc, "IronDisc");
		GameRegistry.registerItem(itemIronHammer, "IronHammer");
		GameRegistry.registerItem(itemIronPunch, "IronPunch");		
		
		GameRegistry.registerItem(itemTreePitch, "TreePitch");
		
		GameRegistry.registerItem(itemStaff, "Staff");
		
		GameRegistry.registerItem(itemTopaz, "Topaz");
		
		GameRegistry.registerItem(foodHotDog, "HotDog");
		GameRegistry.registerItem(foodBBQRibs, "BBQRibs");
		
		GameRegistry.registerItem(cropStrawberrySeeds, "StrawberrySeeds");
		GameRegistry.registerItem(cropStrawberry, "Strawberry");
		GameRegistry.registerBlock(cropStrawberryPlant, "StrawberryPlant");
		
		GameRegistry.registerItem(cropBloodMelonSeeds, "BloodMelonSeeds");
		GameRegistry.registerItem(cropBloodMelonSlice, "BloodMelonSlice");
		GameRegistry.registerBlock(cropBloodMelon, "BloodMelon");
		GameRegistry.registerBlock(cropBloodMelonStem, "BloodMelonStem");
		
		MinecraftForge.addGrassSeed(new ItemStack(cropStrawberrySeeds), 10);
		MinecraftForge.addGrassSeed(new ItemStack(cropBloodMelonSeeds), 10);
		
		ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH).addItem(new WeightedRandomChestContent(new ItemStack(armorTopazChest),1,1,065));
		
		GameRegistry.registerItem(itemTopazAxe, "TopazAxe");
		GameRegistry.registerItem(itemTopazSword, "TopazSword");
		GameRegistry.registerItem(itemTopazShovel, "TopazShovel");
		GameRegistry.registerItem(itemTopazHoe, "TopazHoe");
		GameRegistry.registerItem(itemTopazPickaxe, "TopazPickaxe");
		
		//Armor
		GameRegistry.registerItem(armorTopazHelm, "TopazHelm");
		GameRegistry.registerItem(armorTopazChest, "TopazChest");
		GameRegistry.registerItem(armorTopazLegs, "TopazLegs");
		GameRegistry.registerItem(armorTopazBoots, "TopazBoots");
		
		//����
		GameRegistry.registerBlock(oreAmethystOre, "AmethystOre");
		GameRegistry.registerBlock(oreBerylOre, "BerylOre");
		GameRegistry.registerBlock(oreCopperOre, "CopperOre");
		GameRegistry.registerBlock(oreGarnetOre, "GarnetOre");
		GameRegistry.registerBlock(oreJadeOre, "JadeOre");
		GameRegistry.registerBlock(oreManganeseOre, "ManganeseOre");
		GameRegistry.registerBlock(oreNickelOre, "NickelOre");		
		GameRegistry.registerBlock(oreSapphireOre, "SapphireOre");
		GameRegistry.registerBlock(oreSilverOre, "SilverOre");	
		GameRegistry.registerBlock(oreTinOre, "TinOre");
		GameRegistry.registerBlock(oreTopazOre, "TopazOre");
		GameRegistry.registerBlock(oreTsavoriteOre, "TsavoriteOre");
		GameRegistry.registerBlock(oreZincOre, "ZincOre");
		GameRegistry.registerBlock(oreVanadiumOre, "VanadiumOre");
		
		//Nether Ores
		GameRegistry.registerBlock(oreNetherCopperOre, "NetherCopperOre");
		GameRegistry.registerBlock(oreNetherTinOre, "NetherTinOre");
		GameRegistry.registerBlock(oreNetherZincOre, "NetherZincOre");
		GameRegistry.registerBlock(oreNetherNickelOre, "NetherNickelOre");
		GameRegistry.registerBlock(oreNetherManganeseOre, "NetherManganeseOre");
		GameRegistry.registerBlock(oreNetherVanadiumOre, "NetherVanadiumOre");
		GameRegistry.registerBlock(oreNetherTopazOre, "NetherTopazOre");
		
		//Blocks
		GameRegistry.registerBlock(blockCopperBlock, "CopperBlock");
		GameRegistry.registerBlock(blockMinerals, ItemMineralBlocks.class, blockMinerals.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(blockObsidianTable, "ObsidianTable");
		
		//Lamps
		GameRegistry.registerBlock(blockPurpleLampOn, "PurpleLampOn");
		GameRegistry.registerBlock(blockPurpleLampOff, "PurpleLampOff");
		
		//Fluids
		
		//GameRegistry.registerBlock(fluidSludgeStill, "SludgeStill");
		//GameRegistry.registerBlock(fluidSludgeFlowing, "SludgeFlowing");
		//FluidRegistry.registerFluid(fluidSludgeStill);
		
		//Machines
		GameRegistry.registerBlock(blockAlabasterOvenIdle, "AlabasterOvenIdle");
		GameRegistry.registerBlock(blockAlabasterOvenActive, "AlabasterOvenActive");
		
		GameRegistry.registerBlock(blockIngotMasherIdle, "IngotMasherIdle");
		GameRegistry.registerBlock(blockIngotMasherActive, "IngotMasherActive");
		
		GameRegistry.registerBlock(blockWorkSurface, "WorkSurface");
		
		//Spawn
		GameRegistry.registerWorldGenerator(eventWorldGen, 0);
		
		//Renderers
		mcifaldProxy.registerRenderThings();
		
	}
	
	@EventHandler
	public void Init(FMLInitializationEvent event){
		
		FMLCommonHandler.instance().bus().register(new CraftingHandler());
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
		
		GameRegistry.registerTileEntity(TileEntityAlabasterOven.class, "AlabasterOven");
		GameRegistry.registerTileEntity(TileEntityIngotMasher.class, "IngotMasher");
		
		//Recipes
		RecipeRemover.removeCraftingRecipe();
		RecipeRemover.removeFurnaceRecipe();
		
		GameRegistry.addRecipe(new ItemStack(blockCopperBlock), new Object[]{"CCC", "CCC", "CCC", 'C', itemCopperIngot});
		GameRegistry.addRecipe(new ItemStack(itemTinCog, 4), new Object[]{" X ", "XFX", " X ", 'X', itemTinIngot, 'F', Items.flint});
		GameRegistry.addRecipe(new ItemStack(Items.book), new Object[]{"XXX", "YYY", "XXX", 'X', Items.leather, 'Y', Items.paper});
		GameRegistry.addRecipe(new ItemStack(Blocks.furnace), new Object[]{"XXX", "X X", "XXX", 'X', Blocks.stone});
		GameRegistry.addRecipe(new ItemStack(Items.stick, 2), new Object[]{"X", "X", "X", 'X', Blocks.planks});
		
		GameRegistry.addRecipe(new ItemStack(armorTopazHelm), new Object[]{"XXX", "X X", 'X', itemTopaz});
		GameRegistry.addRecipe(new ItemStack(armorTopazChest), new Object[]{"X X", "XXX", "XXX", 'X', itemTopaz});
		GameRegistry.addRecipe(new ItemStack(armorTopazLegs), new Object[]{"XXX", "X X", "X X", 'X', itemTopaz});
		GameRegistry.addRecipe(new ItemStack(armorTopazBoots), new Object[]{"X X", "X X", 'X', itemTopaz});
		
		GameRegistry.addRecipe(new ItemStack(blockPurpleLampOff), new Object[]{"CXC", "XEX", "CXC", 'C', Blocks.glass, 'X', Items.glowstone_dust, 'E', Items.redstone});
		
		GameRegistry.addShapelessRecipe(new ItemStack(oreCopperOre), new Object[]{itemCopperIngot, Blocks.cobblestone});
		
		GameRegistry.addRecipe(new ItemStack(itemIronDisc, 4), new Object[]{"IH", 'I', Items.iron_ingot, 'H', new ItemStack(itemIronHammer, 1, OreDictionary.WILDCARD_VALUE)});
		GameRegistry.addRecipe(new ItemStack(itemIronWasher), new Object[]{"DP", 'D', itemIronDisc, 'P', new ItemStack(itemIronPunch, 1, OreDictionary.WILDCARD_VALUE)});
		
		//Smelting
		GameRegistry.addSmelting(oreCopperOre, new ItemStack(itemCopperIngot), 0);
		GameRegistry.addSmelting(oreTinOre, new ItemStack(itemTinIngot), 0);
		GameRegistry.addSmelting(oreZincOre, new ItemStack(itemZincIngot), 0);
		GameRegistry.addSmelting(oreNickelOre, new ItemStack(itemNickelIngot), 0);
		GameRegistry.addSmelting(oreManganeseOre, new ItemStack(itemManganeseIngot), 0);
		GameRegistry.addSmelting(oreVanadiumOre, new ItemStack(itemVanadiumIngot), 0);
		
		GameRegistry.addSmelting(oreNetherCopperOre, new ItemStack(oreCopperOre, 2), 0);
		
		GameRegistry.registerFuelHandler(new FuelHandler());
		
		//Entities
		EntityHandler.registerMonsters(EntityCyclops.class, "Cyclops");
	}
	
	@EventHandler
	public void PostInit(FMLPostInitializationEvent postEvent){
		
	}
	
	
}
