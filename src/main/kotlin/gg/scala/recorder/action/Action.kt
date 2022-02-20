package gg.scala.recorder.action

import net.evilblock.cubed.util.nms.MinecraftProtocol
import net.minecraft.server.v1_8_R3.Packet
import org.bukkit.entity.Player

/**
 * @author GrowlyX
 * @since 2/19/2022
 */
abstract class Action
{
    abstract var millisecondOffset: Int

    abstract fun play(player: Player)

    protected fun sendPacket(
        target: Player, packet: Packet<*>
    )
    {
        MinecraftProtocol.send(target, packet)
    }
}
