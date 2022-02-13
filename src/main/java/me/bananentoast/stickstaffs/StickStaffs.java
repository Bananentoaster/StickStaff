package me.bananentoast.stickstaffs;

import lombok.Getter;
import me.bananentoast.stickstaffs.command.StaffCommand;
import me.bananentoast.stickstaffs.helpers.Metrics;
import me.bananentoast.stickstaffs.listener.InteractListener;
import me.bananentoast.stickstaffs.manager.StaffManager;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class StickStaffs extends JavaPlugin {

    @Getter
    private static StickStaffs instance;

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

}
