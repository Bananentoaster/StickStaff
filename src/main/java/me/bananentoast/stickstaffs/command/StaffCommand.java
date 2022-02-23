package me.bananentoast.stickstaffs.command;

import me.bananentoast.stickstaffs.StickStaffs;
import me.bananentoast.stickstaffs.manager.staff.BaseStaff;
import me.bananentoast.stickstaffs.util.ListUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class StaffCommand implements CommandExecutor, TabCompleter {

    private final StickStaffs instance;

    public StaffCommand() {
        instance = StickStaffs.getInstance();
        instance.getCommand("staff").setExecutor(this);
        instance.getCommand("staff").setTabCompleter(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("§cThis command is player-only!");
            return true;
        }

        if (args.length == 0) {
            player.sendMessage("§cPlease use /staff <give|recipe>!");
            return true;
        }

        if (args[0].equals("give")) {
            if (!player.isOp()) {
                player.sendMessage("§cThis command requires to be an operator!");
                return true;
            }
            String staffName = args[1];
            if (!instance.getStaffManager().isStaff(staffName)) {
                player.sendMessage("§cInvalid Staff Name! Please use /staff give <staff name>!");
                return true;
            }
            player.getInventory().addItem(instance.getStaffManager().getStaff(staffName).getItem());
            player.sendMessage("§aYou received the " + staffName + " staff");
            return true;
        } else if (args[0].equals("recipe")) {
            String staffName = args[1];
            if (!instance.getStaffManager().isStaff(staffName)) {
                player.sendMessage("§cInvalid Staff Name! Please use /staff give <staff name>!");
                return true;
            }
            BaseStaff staff = instance.getStaffManager().getStaff(staffName);

            if (!staff.hasRecipe()) {
                player.sendMessage("§cThe " + staff.getName() + " has no recipe");
                return true;
            }

            Inventory inv = Bukkit.createInventory(null, InventoryType.WORKBENCH, staff.getItemName() + "§7's Recipe");
            List<ItemStack> stacks = new ArrayList<>();
            stacks.add(staff.getItem());
            for (String s1 : staff.getRecipe().getShape()) {
                for (char c : s1.toCharArray()) {
                    stacks.add(staff.getRecipe().getIngredientMap().get(c));
                }
            }
            inv.setContents(stacks.toArray(new ItemStack[0]));
            player.openInventory(inv);
        } else {
            player.sendMessage("§cPlease use /staff <give|recipe>!");
            return true;
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String name, String[] args) {
        List<String> list = new ArrayList<>();

        if (args.length < 2) {
            list.addAll(List.of("give", "recipe"));
            if (args.length == 0) return list;
            return ListUtil.getShorterList(list, args[0]);
        }

        if (args[0].equals("give") || args[0].equals("recipe")) {
            list.addAll(instance.getStaffManager().getCasedStaffNames());
            return ListUtil.getShorterList(list, args[1]);
        }

        return list;

    }
}
