package mods.immibis.redlogic.interaction;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import net.minecraftforge.oredict.RecipeSorter;

import mods.immibis.redlogic.UtilsDye;

public class RecipeDyeLumarButton implements IRecipe {

	public RecipeDyeLumarButton()
	{
	}
	
	@Override
	public ItemStack getCraftingResult(InventoryCrafting ic) {
		int colour = -1;
		int buttonDmg = -1;
		for(int k = 0; k < ic.getSizeInventory(); k++) {
			ItemStack s = ic.getStackInSlot(k);
			if(s != null) {
				if(s.getItem() instanceof ItemLumarButton) {
					if(buttonDmg != -1)
						return null;
					buttonDmg = s.getItemDamage();
				
				} else if(colour == -1) {
					colour = UtilsDye.getDyeColor(s);

					if(colour == -1)
						return null;
				} else
					return null;
			}
		}
		
		if(colour == -1 || buttonDmg == -1)
			return null;
		
		return TileLumarButton.getItemStack(colour, TileLumarButton.getTypeFromDamage(buttonDmg), TileLumarButton.getModelFromDamage(buttonDmg));
	}
	
	@Override
	public boolean matches(InventoryCrafting inventorycrafting, World world) {
		return getCraftingResult(inventorycrafting) != null;
	}
	
	@Override
	public ItemStack getRecipeOutput() {
		return TileLumarButton.getItemStack(0, LumarButtonType.Normal, LumarButtonModel.Button);
	}
	
	@Override
	public int getRecipeSize() {
		return 2;
	}
	
	static {
		RecipeSorter.register(RecipeDyeLumarButton.class.getName(), RecipeDyeLumarButton.class, RecipeSorter.Category.SHAPELESS, "");
	}
}
