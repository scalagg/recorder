package gg.scala.recorder.action.actions

import gg.scala.recorder.ENTITY_ID_OFFSET
import gg.scala.recorder.action.Action
import gg.scala.recorder.field
import gg.scala.spigot.util.MathHelper
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityHeadRotation
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player

/**
 * @author GrowlyX
 * @since 2/19/2022
 */
class EntityHeadLookAction(
    entity: LivingEntity,
    override var millisecondOffset: Int = -1
) : Action()
{
    private val entityId = entity.entityId

    private val rotation = MathHelper.floor(
        (entity as CraftLivingEntity).handle.headRotation * 256.0 / 360.0
    )

    override fun play(player: Player)
    {
        val packet = PacketPlayOutEntityHeadRotation()
        packet.field("a", entityId + ENTITY_ID_OFFSET)
        packet.field("b", rotation)

        sendPacket(player, packet)
    }
}
