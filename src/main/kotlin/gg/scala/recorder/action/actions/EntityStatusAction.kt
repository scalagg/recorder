package gg.scala.recorder.action.actions

import gg.scala.recorder.ENTITY_ID_OFFSET
import gg.scala.recorder.action.Action
import gg.scala.recorder.field
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityStatus
import org.bukkit.EntityEffect
import org.bukkit.entity.Entity
import org.bukkit.entity.Player

/**
 * @author GrowlyX
 * @since 2/19/2022
 */
class EntityStatusAction(
    entity: Entity, effect: EntityEffect,
    override var millisecondOffset: Int = -1
) : Action()
{
    private val entityId = entity.entityId
    private val status = effect.data

    override fun play(player: Player)
    {
        val packet = PacketPlayOutEntityStatus()
        packet.field("a", entityId + ENTITY_ID_OFFSET)
        packet.field("b", status)

        sendPacket(player, packet)
    }
}
