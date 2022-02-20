package gg.scala.recorder.action.actions

import gg.scala.recorder.ENTITY_ID_OFFSET
import gg.scala.recorder.action.Action
import net.minecraft.server.v1_8_R3.PacketPlayOutCollect
import org.bukkit.entity.Item
import org.bukkit.entity.Player

/**
 * @author GrowlyX
 * @since 2/19/2022
 */
class CollectItemAction(
    collected: Item, collector: Player,
    override var millisecondOffset: Int = -1
) : Action()
{
    private val collectedId = collected.entityId
    private val collectorId = collector.entityId

    override fun play(player: Player)
    {
        val packet = PacketPlayOutCollect(
            collectedId + ENTITY_ID_OFFSET,
            collectorId + ENTITY_ID_OFFSET
        )

        sendPacket(player, packet)
    }
}
