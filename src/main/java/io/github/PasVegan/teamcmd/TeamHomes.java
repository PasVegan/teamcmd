package io.github.PasVegan.teamcmd;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;

import java.io.*;
import java.util.HashMap;

public class TeamHomes implements Serializable {
    private final HashMap<String, TeamHome> homes = new HashMap<>();

    public static TeamHomes load(String fileName) {
        try {
            File dataFile = FabricLoader.getInstance().getConfigDir().resolve(fileName).toFile();
            if (dataFile.exists()) {
                FileInputStream fileIn = new FileInputStream(dataFile);
                ObjectInputStream objectIn = new ObjectInputStream(fileIn);
                TeamHomes teamHomes = (TeamHomes) objectIn.readObject();
                objectIn.close();
                fileIn.close();
                return teamHomes;
            } else {
                return new TeamHomes();
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void save(String fileName) {
        try {
            File dataFile = FabricLoader.getInstance().getConfigDir().resolve(fileName).toFile();
            FileOutputStream fileOut = new FileOutputStream(dataFile);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(this);
            objectOut.close();
            fileOut.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public TeamHome getHome(String teamName) {
        return homes.get(teamName);
    }

    public void setHome(String teamName, Vec3d position, Vec2f rotation, int dimension) {
        TeamHome home = new TeamHome();
        home.posX = position.x;
        home.posY = position.y;
        home.posZ = position.z;
        home.pitch = rotation.x;
        home.yaw = rotation.y;
        home.dimension = dimension;
        homes.put(teamName, home);
    }

    public void deleteTeamHome(String teamName) {
        homes.remove(teamName);
    }

    public static class TeamHome implements Serializable {
        public double posX;
        public double posY;
        public double posZ;
        public float yaw;
        public float pitch;
        public int dimension;
    }
}