package gg.scala.recorder.action.actions.spawn

import gg.scala.recorder.ENTITY_ID_OFFSET
import gg.scala.recorder.action.Action
import gg.scala.recorder.field
import net.minecraft.server.v1_8_R3.MathHelper
import net.minecraft.server.v1_8_R3.PacketPlayOutSpawnEntity
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player

/**
 * @author GrowlyX
 * @since 5/7/2022
 */
class SpawnObjectAction(
    entity: LivingEntity,
    private val type: Byte,
    private val data: Int,
    override var millisecondOffset: Int = -1
) : Action()
{
    private val entityId = entity.entityId

    @Transient
    private val location = entity.location

    private val x = MathHelper
        .floor(location.x * 32.0)

    private val y = MathHelper
        .floor(location.y * 32.0)

    private val z = MathHelper
        .floor(location.z * 32.0)

    private val pitch = MathHelper
        .floor(location.pitch * 256.0 / 360.0)
        .toByte()

    private val yaw = MathHelper
        .floor(location.yaw * 256.0 / 360.0)
        .toByte()

    private var motX: Short = 0
    private var motY: Short = 0
    private var motZ: Short = 0

    init
    {
        if (data > 0)
        {
            val velocity = entity.velocity

            // clamp values to +/- 3.9
            // (Mojang does this, likely overflow prevention?)
            val velocityX = velocity.x
                .coerceAtLeast(-3.9)
                .coerceAtMost(3.9)

            val velocityY = velocity.y
                .coerceAtLeast(-3.9)
                .coerceAtMost(3.9)

            val velocityZ = velocity.z
                .coerceAtLeast(-3.9)
                .coerceAtMost(3.9)

            this.motX = (velocityX * 8000.0)
                .toInt().toShort()

            this.motY = (velocityY * 8000.0)
                .toInt().toShort()

            this.motZ = (velocityZ * 8000.0)
                .toInt().toShort()
        }
    }

    override fun play(player: Player)
    {
        val packet = PacketPlayOutSpawnEntity()

        packet.field("a", entityId + ENTITY_ID_OFFSET)
        packet.field("j", type)
        packet.field("b", x)
        packet.field("c", y)
        packet.field("d", z)
        packet.field("h", pitch)
        packet.field("i", yaw)
        packet.field("k", data)
        packet.field("e", motX)
        packet.field("f", motY)
        packet.field("g", motZ)

        sendPacket(player, packet)
    }
}
