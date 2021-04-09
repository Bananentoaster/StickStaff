package me.bananentoast.stickstaffs;

import me.bananentoast.stickstaffs.command.StaffCommand;
import me.bananentoast.stickstaffs.listener.InteractListener;
import me.bananentoast.stickstaffs.manager.StaffManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class StickStaffs extends JavaPlugin {

    private StaffCommand staffCommand;
    private InteractListener interactListener;
    private StaffManager staffManager;

    @Override
    public void onEnable() {
        staffCommand = new StaffCommand(this);
        interactListener = new InteractListener(this);
        staffManager = new StaffManager(this);
        staffManager.loadStaffs();
    }

    @Override
    public void onDisable() {
    }

    public StaffManager getStaffManager() {
        return staffManager;
    }
}
