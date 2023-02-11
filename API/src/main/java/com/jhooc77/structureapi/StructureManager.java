package com.jhooc77.structureapi;

import org.bukkit.NamespacedKey;

import java.io.IOException;
import java.io.InputStream;

public interface StructureManager {

    Structure loadStructure(InputStream inputStream) throws IOException;

    Structure loadStructure(NamespacedKey key);
}
