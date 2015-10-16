package com.sainttx.menu;

import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Matthew on 9/8/2015.
 */
public class Menu implements InventoryHolder {

    private Inventory inventory;
    private Map<Integer, Icon> icons;
    private Menu parent;

    /**
     * Creates a menu
     *
     * @param title the title of the menu
     * @param rows  the amount of rows for the GUI to have
     */
    public Menu(String title, int rows) {
        this.icons = new HashMap<>();
        this.inventory = Bukkit.createInventory(this, rows * 9, title);
    }

    /**
     * Adds an icon to the first available slot in the inventory
     *
     * @param icon the icon to add
     * @return will return false if the inventory has no available slots
     */
    public boolean addIcon(Icon icon) {
        int first = getInventory().firstEmpty();

        if (first == -1) {
            return false;
        }

        addIcon(icon, first);
        return true;
    }

    /**
     * Adds an {@link Icon} to a specific slot in the inventory, overriding and
     * removing any previous {@link Icon}
     *
     * @param icon the icon to add
     * @param slot the slot in the inventory to add
     */
    public void addIcon(Icon icon, int slot) {
        if (icons.containsKey(slot)) {
            removeIcon(slot);
        }

        getInventory().setItem(slot, icon.getItemStack());
        icons.put(slot, icon);
    }

    /**
     * Returns an {@link Icon} at a specific slot
     *
     * @param slot the slot
     * @return the icon at the slot, null if there is no icon there
     */
    public Icon getIcon(int slot) {
        return icons.get(slot);
    }

    /**
     * Removes an icon at a specific slot
     *
     * @param slot the slot
     */
    public void removeIcon(int slot) {
        if (!icons.containsKey(slot)) {
            return;
        }

        icons.remove(slot);
        getInventory().setItem(slot, null);
    }

    /**
     * Clicks an icon at a slot for a player
     *
     * @param slot   the slot number
     * @param player the player that clicked inside the menu
     */
    public void selectIcon(int slot, Player player) {
        if (!icons.containsKey(slot)) {
            return;
        }

        Icon icon = icons.get(slot);
        icon.click(player, this);
    }

    /**
     * Sets the parent menu
     *
     * @param parent the new parent menu
     */
    public void setParent(Menu parent) {
        this.parent = parent;
    }

    /**
     * Returns the parent menu
     *
     * @return the parent menu
     */
    public Menu getParent() {
        return parent;
    }

    /**
     * Updates the inventory of all viewers inside the inventory
     */
    public void update() {
        for (HumanEntity viewer : getInventory().getViewers()) {
            if (!(viewer instanceof Player)) {
                continue;
            }
            ((Player) viewer).updateInventory();
        }
    }

    /**
     * Closes the inventory on all viewers
     */
    public void close() {
        for (HumanEntity viewer : new ArrayList<>(getInventory().getViewers())) {
            viewer.closeInventory();
        }
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }
}
