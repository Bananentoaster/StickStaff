package me.bananentoast.stickstaffs.manager.staff;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

public class FlyStaff extends BaseStaff {

    public FlyStaff() {
        super("fly", "§f§lFly Staff", "§7Change your fly speed");
    }

    @Override
    public void onClick(Player player) {

        if (!player.getAllowFlight()) {
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§cYou can't fly!"));
            return;
        }

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

        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§a§lFly-Speed: x" + speed * 10));

    }

}
