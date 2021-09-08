package net.aruneko.chainwoodcutter.listeners

import net.aruneko.chainwoodcutter.extensions.*
import org.bukkit.Server
import org.bukkit.Tag.*
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.plugin.Plugin

class ChainWoodCutterListener(private val plugin: Plugin, private val server: Server) : Listener {
    @EventHandler
    fun onBreakLog(event: BlockBreakEvent) {
        val player = event.player
        val mainHandItem = player.inventory.itemInMainHand

        if (!player.isSneaking) {
            // スニークしてなかったらやめる
            return
        }

        val block = event.block

        if (!block.isPreferredTool(mainHandItem) || !MINEABLE_AXE.isTagged(block.type)) {
            // 適正ツールじゃなかったらやめる
            return
        }

        if (!LOGS.isTagged(block.type)) {
            // 原木じゃなかったらやめる
            return
        }

        val logAndLeaves = block.findLogAndLeaves()
        if (logAndLeaves.none { LEAVES.isTagged(it.type) }) {
            // 葉っぱが周りについてなかったらやめる
            return
        }

        val logs = logAndLeaves.filter { LOGS.isTagged(it.type) }

        logs.sortByDistance(player).reversed().forEach { it.breakNaturally(mainHandItem) }
    }
}
