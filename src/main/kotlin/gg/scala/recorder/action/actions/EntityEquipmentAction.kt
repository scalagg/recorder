package gg.scala.recorder.action.actions

import gg.scala.recorder.ENTITY_ID_OFFSET
import gg.scala.recorder.action.Action
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityEquipment
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

/**
 * @author GrowlyX
 * @since 2/19/2022
 */
class EntityEquipmentAction(
    entity: LivingEntity, slot: Slot,
    private val item: ItemStack,
    override var millisecondOffset: Int = -1
) : Action()
{
    private val entityId = entity.entityId
    private val slot = slot.value

    override fun play(player: Player)
    {
        val packet = PacketPlayOutEntityEquipment(
            entityId + ENTITY_ID_OFFSET,
            slot, CraftItemStack.asNMSCopy(item)
        )

        sendPacket(player, packet)
    }

    enum class Slot(
        val value: Int
    )
    {
        HAND(0),
        BOOTS(1),
        LEGGINGS(2),
        CHESTPLATE(3),
        HELMET(4)
    }
}
