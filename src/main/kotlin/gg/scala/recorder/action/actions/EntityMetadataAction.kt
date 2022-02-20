package gg.scala.recorder.action.actions

import gg.scala.recorder.ENTITY_ID_OFFSET
import gg.scala.recorder.action.Action
import gg.scala.recorder.field
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityMetadata
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity
import org.bukkit.entity.Entity
import org.bukkit.entity.Player

/**
 * @author GrowlyX
 * @since 2/19/2022
 */
class EntityMetadataAction(
    entity: Entity,
    override var millisecondOffset: Int = -1
) : Action()
{
    private val entityId = entity.entityId

    // this probably won't serialize
    // correctly- we'll see during testing
    private val metadata = (entity as CraftEntity)
        .handle.dataWatcher.c()

    override fun play(player: Player)
    {
        val packet = PacketPlayOutEntityMetadata()
        packet.field("a", entityId + ENTITY_ID_OFFSET)
        packet.field("b", metadata)

        sendPacket(player, packet)
    }
}
