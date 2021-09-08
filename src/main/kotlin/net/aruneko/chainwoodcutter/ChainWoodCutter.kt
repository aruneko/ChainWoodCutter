package net.aruneko.chainwoodcutter

import net.aruneko.chainwoodcutter.listeners.ChainWoodCutterListener
import org.bukkit.plugin.java.JavaPlugin

class ChainWoodCutter : JavaPlugin() {
    override fun onEnable() {
        server.pluginManager.registerEvents(ChainWoodCutterListener(this, server), this)
    }

    override fun onDisable() {}
}