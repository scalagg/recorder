package gg.scala.recorder.action.actions

import gg.scala.recorder.ENTITY_ID_OFFSET
import gg.scala.recorder.action.Action
import gg.scala.recorder.field
import net.evilblock.cubed.util.Reflection
import net.minecraft.server.v1_8_R3.PacketPlayOutAnimation
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player

/**
 * @author GrowlyX
 * @since 2/19/2022
 */
class AnimationAction(
    entity: LivingEntity, animation: Animation,
    override var millisecondOffset: Int = -1
) : Action()
{
    private val entityId = entity.entityId
    private val animation = animation.value

    enum class Animation(
        val value: Int
    )
    {
        SWING_ARM(0),
        EAT_FOOD(3),
        CRITICAL(4),
        MAGIC_CRITICAL(5)
    }

    override fun play(player: Player)
    {
        val packet = PacketPlayOutAnimation()
        packet.field("a", entityId + ENTITY_ID_OFFSET)
        packet.field("b", animation)

        sendPacket(player, packet)
    }
}
