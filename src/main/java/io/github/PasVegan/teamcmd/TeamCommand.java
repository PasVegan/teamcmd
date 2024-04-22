package io.github.PasVegan.teamcmd;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

public class TeamCommand implements DedicatedServerModInitializer {
    public static final Config CONFIG = Config.load("teamcommand.toml");
    public static final TeamHomes HOMES = TeamHomes.load("teamhomes.dat");

    @Override
    public void onInitializeServer() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> CommandBuilder.register(dispatcher));
        ServerTickEvents.END_SERVER_TICK.register(TeamUtil::tick);
    }
}
