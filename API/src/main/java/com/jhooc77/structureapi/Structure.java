package com.jhooc77.structureapi;

import org.bukkit.Location;
import org.bukkit.block.structure.Mirror;
import org.bukkit.block.structure.StructureRotation;

import java.util.Random;

public interface Structure {

    void place(Location location, boolean spawnEntity, StructureRotation rotation, Mirror mirror, float integrity, Random random);

}
