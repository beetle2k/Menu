package com.sainttx.menu;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

/**
 * Created by Matthew on 9/8/2015.
 */
public class MenuListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onItemClick(InventoryClickEvent event) {
        Inventory inventory = event.getInventory();

        // Only listening for menus
        if (!(inventory.getHolder() instanceof Menu)) {
            return;
        }

        event.setCancelled(true);
        Menu menu = (Menu) inventory.getHolder();

        // Only when they click inside a menu
        if (event.getSlotType() == InventoryType.SlotType.OUTSIDE) {
            return;
        }

        int index = event.getRawSlot();
        if (index > inventory.getSize()) {
            return;
        }

        menu.selectIcon(index, (Player) event.getWhoClicked());
        menu.update();
    }
}
