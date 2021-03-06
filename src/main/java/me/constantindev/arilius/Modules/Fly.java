package me.constantindev.arilius.Modules;

import me.constantindev.arilius.Arilius;
import me.constantindev.arilius.Etc.Base.ModuleBase;
import me.constantindev.arilius.Etc.CheatConfigKey;
import me.constantindev.arilius.Etc.CheatConfigManager;
import me.constantindev.arilius.Etc.Helper.ClientHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.vector.Vector3d;

public class Fly extends ModuleBase {
    public Fly() {
        super("Fly", "Weeeee");
    }

    @Override
    public void register(CheatConfigManager config) {
        config.addToConfig(new CheatConfigKey<>("mode", "jetpack", this));
        config.addToConfig(new CheatConfigKey<>("strength", 0.005, this));
        super.register(config);
    }

    @Override
    public void run() {

        switch (Arilius.config.getByName("mode", this).getValue().toString().toLowerCase()) {

            case "jetpack":
                if (Minecraft.getInstance().gameSettings.keyBindJump.isKeyDown()) {
                    double strength = 0.005;
                    try {
                        strength = Double.parseDouble(Arilius.config.getByName("strength", this).getValue().toString());
                    } catch (Exception ex) {
                        Arilius.config.getByName("strength", this).setValue(strength);
                    }
                    Minecraft.getInstance().player.addVelocity(0, strength, 0);
                    Minecraft.getInstance().player.velocityChanged = true;
                }
                break;
            case "vanilla":
                Minecraft.getInstance().player.abilities.isFlying = true;
                break;
            case "tp":
                double x = 0, z = 0;
                if (Minecraft.getInstance().gameSettings.keyBindForward.isKeyDown()) {
                    Vector3d v = Minecraft.getInstance().player.getLookVec();
                    x = v.getX() * 2;
                    z = v.getZ() * 2;
                    Minecraft.getInstance().player.velocityChanged = true;
                }
                Minecraft.getInstance().player.setMotion(x, Minecraft.getInstance().player.getLookVec().getY(), z);

                break;
            default:
                Arilius.config.getByName("mode", this).setValue("jetpack");
                ClientHelper.SendClientNotif("[FLY] Invalid mode detected, switching to jetpack. Please choose one of vanilla or jetpack.");
        }
        super.run();
    }
}
