package gg.scala.recorder.action.actions

import gg.scala.recorder.ENTITY_ID_OFFSET
import gg.scala.recorder.action.Action
import gg.scala.spigot.util.MathHelper
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityTeleport
import org.bukkit.entity.Entity
import org.bukkit.entity.Player

/**
 * @author GrowlyX
 * @since 2/19/2022
 */
class EntityTeleportAction(
    entity: Entity,
    override var millisecondOffset: Int = -1
) : Action()
{
    private val entityId = entity.entityId

    private val x = MathHelper.floor(entity.location.x * 32.0)
    private val y = MathHelper.floor(entity.location.y * 32.0)
    private val z = MathHelper.floor(entity.location.z * 32.0)

    private val yaw = MathHelper.floor(entity.location.yaw * 256.0 / 360.0)
    private val pitch = MathHelper.floor(entity.location.pitch * 256.0 / 360.0)

    private val onGround = entity.isOnGround

    override fun play(player: Player)
    {
        val packet = PacketPlayOutEntityTeleport(
            entityId + ENTITY_ID_OFFSET,
            x.toInt(), y.toInt(), z.toInt(),
            yaw.toInt().toByte(), pitch.toInt().toByte(),
            onGround
        )

        sendPacket(player, packet)
    }
}
