package com.jhooc77.structureapi;

import net.minecraft.server.v1_12_R1.DefinedStructureManager;
import net.minecraft.server.v1_12_R1.MinecraftKey;
import net.minecraft.server.v1_12_R1.MinecraftServer;
import org.bukkit.NamespacedKey;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class StructureManager_V1_12_R1 implements StructureManager {

    final DefinedStructureManager structureManager;
    final Method setFromInputStream;

    Map<Structure, String> maps = new HashMap<>();
    AtomicInteger integer = new AtomicInteger(0);

    public StructureManager_V1_12_R1() {
        this.structureManager = new DefinedStructureManager("world/structures", MinecraftServer.getServer().dataConverterManager);
        try {
            setFromInputStream = DefinedStructureManager.class.getDeclaredMethod("a", String.class, InputStream.class);
            setFromInputStream.setAccessible(true);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Structure loadStructure(InputStream inputStream) throws IOException {
        try {
            int number = integer.getAndIncrement();
            String key = "ap" + number;
            setFromInputStream.invoke(structureManager, key, inputStream);
            Structure structure = new Structure_V1_12_R1(structureManager.b(MinecraftServer.getServer(), new MinecraftKey(key)));
            maps.put(structure, key);
            return structure;
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Structure loadStructure(NamespacedKey key) {
        return new Structure_V1_12_R1(structureManager.a(MinecraftServer.getServer(), new MinecraftKey(key.getNamespace(), key.getKey())));
    }
}
