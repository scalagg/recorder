package gg.scala.recorder.action.actions.effect

import gg.scala.recorder.ENTITY_ID_OFFSET
import gg.scala.recorder.action.Action
import net.minecraft.server.v1_8_R3.MobEffect
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityEffect
import net.minecraft.server.v1_8_R3.PacketPlayOutRemoveEntityEffect
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

/**
 * @author GrowlyX
 * @since 2/19/2022
 */
class EntityEffectRemoveAction(
    entity: LivingEntity, effect: PotionEffectType,
    override var millisecondOffset: Int = -1
) : Action()
{
    private val entityId = entity.entityId
    private val effectId = effect.id

    override fun play(player: Player)
    {
        val packet = PacketPlayOutRemoveEntityEffect(
            entityId + ENTITY_ID_OFFSET,
            MobEffect(effectId, 1)
        )

        sendPacket(player, packet)
    }
}
