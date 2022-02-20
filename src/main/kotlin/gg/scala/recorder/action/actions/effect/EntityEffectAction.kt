package gg.scala.recorder.action.actions.effect

import gg.scala.recorder.ENTITY_ID_OFFSET
import gg.scala.recorder.action.Action
import net.minecraft.server.v1_8_R3.MobEffect
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityEffect
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.potion.PotionEffect

/**
 * @author GrowlyX
 * @since 2/19/2022
 */
class EntityEffectAction(
    entity: LivingEntity, effect: PotionEffect,
    override var millisecondOffset: Int = -1
) : Action()
{
    private val entityId = entity.entityId
    private val effectId = effect.type.id
    private val amplifier = effect.amplifier
    private val duration = effect.duration
    private val hideParticles = effect.isAmbient

    private val hasParticles = effect.hasParticles()

    override fun play(player: Player)
    {
        val packet = PacketPlayOutEntityEffect(
            entityId + ENTITY_ID_OFFSET,
            MobEffect(
                effectId, duration, amplifier,
                hideParticles, hasParticles
            )
        )

        sendPacket(player, packet)
    }
}
