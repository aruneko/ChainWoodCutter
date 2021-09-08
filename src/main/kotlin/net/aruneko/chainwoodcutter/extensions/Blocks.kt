package net.aruneko.chainwoodcutter.extensions

import org.bukkit.block.Block
import org.bukkit.entity.Player

fun List<Block>.extractEqualsOrLeaves(block: Block) : List<Block> {
    val leaves = block.leaveOf()
    return this.filter { it.type === block.type || leaves.contains(it.type) }
}

fun List<Block>.sortByDistance(player: Player): List<Block> {
    return this.sortedBy { player.location.distance(it.location) }
}
