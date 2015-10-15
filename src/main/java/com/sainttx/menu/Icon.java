package com.sainttx.menu;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Matthew on 9/8/2015.
 */
public abstract class Icon {

    private final ItemStack itemStack;

    /**
     * Creates an Icon for a {@link Menu}
     *
     * @param itemStack the item represented by this icon
     */
    public Icon(final ItemStack itemStack) {
        if (itemStack == null) {
            throw new IllegalArgumentException("item cannot be null");
        }
        this.itemStack = itemStack;
    }

    /**
     * Gets the item
     *
     * @return the item that this icon represents
     */
    public ItemStack getItemStack() {
        return itemStack;
    }

    /**
     * Sets the item represented by this icon
     *
     * @param itemStack the new item
     */
    public void setItemStack(ItemStack itemStack) {
        if (itemStack == null) {
            return;
        }

        // Change all data
        this.itemStack.setType(itemStack.getType());
        this.itemStack.setAmount(itemStack.getAmount());
        this.itemStack.setDurability(itemStack.getDurability());

        // Set item meta
        if (itemStack.hasItemMeta()) {
            this.itemStack.setItemMeta(itemStack.getItemMeta().clone());
        } else {
            this.itemStack.setItemMeta(null);
        }
    }

    /**
     * Handles a player clicking the inventory
     *
     * @param player the player that clicked this icon
     * @param menu   the menu this icon was clicked inside
     */
    public abstract void click(Player player, Menu menu);
}
