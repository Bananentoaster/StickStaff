package me.bananentoast.stickstaffs;

import me.bananentoast.stickstaffs.command.StaffCommand;
import me.bananentoast.stickstaffs.helpers.Metrics;
import me.bananentoast.stickstaffs.listener.InteractListener;
import me.bananentoast.stickstaffs.manager.StaffManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class StickStaffs extends JavaPlugin {

    public static StickStaffs instance;

    private StaffCommand staffCommand;
    private InteractListener interactListener;
    private StaffManager staffManager;

    @Override
    public void onEnable() {
        instance = this;

        staffCommand = new StaffCommand();
        interactListener = new InteractListener();
        staffManager = new StaffManager();
        staffManager.loadStaffs();

        Metrics metrics = new Metrics(this, 14298);
    }

    @Override
    public void onDisable() {
    }

    public static StickStaffs getInstance() {
        return instance;
    }

    public StaffManager getStaffManager() {
        return staffManager;
    }
}
