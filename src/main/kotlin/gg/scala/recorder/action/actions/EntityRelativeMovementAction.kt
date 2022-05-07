package gg.scala.recorder.action.actions

import gg.scala.recorder.ENTITY_ID_OFFSET
import gg.scala.recorder.action.Action
import net.minecraft.server.v1_8_R3.MathHelper
import net.minecraft.server.v1_8_R3.PacketPlayOutEntity
import org.bukkit.Location
import org.bukkit.entity.Entity
import org.bukkit.entity.Player

/**
 * @author GrowlyX
 * @since 5/7/2022
 */
class EntityRelativeMovementAction(
    entity: Entity,
    oldLocation: Location,
    newLocation: Location,
    override var millisecondOffset: Int = -1
) : Action()
{
    private val entityId = entity.entityId

    private val x = (newLocation.x - oldLocation.x)
        .toInt().toByte()

    private val y = (MathHelper
        .floor(newLocation.y * 32.0) - MathHelper
        .floor(oldLocation.y * 32.0)
            ).toByte()

    private val z = (newLocation.z - oldLocation.z)
        .toInt().toByte()

    private val yaw = MathHelper
        .floor(newLocation.yaw * 256.0 / 360.0)
        .toByte()

    private val pitch = MathHelper
        .floor(newLocation.pitch * 256.0 / 360.0)
        .toByte()

    private val onGround = entity.isOnGround

    override fun play(player: Player)
    {
        val packet = PacketPlayOutEntity.PacketPlayOutRelEntityMoveLook(
            entityId + ENTITY_ID_OFFSET, x, y, z, yaw, pitch, onGround
        )

        sendPacket(player, packet)
    }
}
