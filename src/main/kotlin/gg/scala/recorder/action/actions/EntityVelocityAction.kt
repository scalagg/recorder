package gg.scala.recorder.action.actions

import gg.scala.recorder.ENTITY_ID_OFFSET
import gg.scala.recorder.action.Action
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityVelocity
import org.bukkit.entity.Entity
import org.bukkit.entity.Player

/**
 * @author GrowlyX
 * @since 2/19/2022
 */
class EntityVelocityAction(
    entity: Entity,
    override var millisecondOffset: Int = -1
) : Action()
{
    private val entityId = entity.entityId

    private val x = entity.velocity.x
    private val y = entity.velocity.y
    private val z = entity.velocity.z

    override fun play(player: Player)
    {
        val packet = PacketPlayOutEntityVelocity(
            entityId + ENTITY_ID_OFFSET, x, y, z
        )

        sendPacket(player, packet)
    }
}
