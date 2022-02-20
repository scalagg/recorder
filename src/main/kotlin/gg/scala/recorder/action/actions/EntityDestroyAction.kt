package gg.scala.recorder.action.actions

import gg.scala.recorder.ENTITY_ID_OFFSET
import gg.scala.recorder.action.Action
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy
import org.bukkit.entity.Entity
import org.bukkit.entity.Player

/**
 * @author GrowlyX
 * @since 2/19/2022
 */
class EntityDestroyAction(
    vararg destroyed: Entity,
    override var millisecondOffset: Int = -1
) : Action()
{
    private val entities = destroyed
        .map { it.entityId }
        .toTypedArray()

    override fun play(player: Player)
    {
        val copy = entities.clone()

        for (indexed in entities.withIndex())
        {
            copy[indexed.index] += ENTITY_ID_OFFSET
        }

        sendPacket(
            player,
            PacketPlayOutEntityDestroy(*copy.toIntArray())
        )
    }
}
