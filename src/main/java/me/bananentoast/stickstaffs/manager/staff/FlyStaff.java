package me.bananentoast.stickstaffs.manager.staff;

import org.bukkit.Material;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;

public class FlyStaff extends BaseStaff {

    public FlyStaff() {
        super("fly", "§f§lFly Staff");
    }

    @Override
    public void onClick(Player player) {

        if (!player.isFlying()) {
            player.setFlying(true);
        }

        float speed = player.getFlySpeed();

        if (speed <= 0.1f) {
            speed = 0.25f;
        } else if (speed <= 0.25f) {
            speed = 0.5f;
        } else if (speed <= 0.5f) {
            speed = 0.75f;
        } else if (speed <= 0.75f) {
            speed = 1.0f;
        } else {
            speed = 0.1f;
        }

        player.setFlySpeed(speed);

        player.sendMessage("§a§lFly-Speed: x" + speed * 10);

    }

}
