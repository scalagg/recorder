package gg.scala.recorder.actions

import net.evilblock.cubed.util.nms.MinecraftProtocol
import net.minecraft.server.v1_8_R3.Packet
import org.bson.ByteBuf
import org.bukkit.entity.Player

/**
 * @author GrowlyX
 * @since 2/19/2022
 */
abstract class Action
{
    abstract var millisecondOffset: Int

    abstract fun read(input: ByteBuf)
    abstract fun write(output: ByteBuf)

    protected fun sendPacket(
        target: Player, packet: Packet<*>
    )
    {
        MinecraftProtocol.send(target, packet)
    }
}
