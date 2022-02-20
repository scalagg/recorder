package gg.scala.recorder.action

import gg.scala.flavor.service.Configure
import gg.scala.flavor.service.Service
import gg.scala.recorder.action.actions.*
import gg.scala.recorder.action.actions.effect.EntityEffectAction
import gg.scala.recorder.action.actions.effect.EntityEffectRemoveAction
import kotlin.reflect.KClass

/**
 * @author GrowlyX
 * @since 2/19/2022
 */
@Service
object Actions
{
    private val idToClass = mutableMapOf<Int, KClass<out Action>>()
    val classToId = mutableMapOf<KClass<out Action>, Int>()

    @Configure
    fun configure()
    {
        register<EntityAnimationAction>(0x0B)
        register<EntityCollectItemAction>(0x0D)
        register<EntityDestroyAction>(0x13)
        register<EntityEquipmentAction>(0x04)
        register<EntityHeadLookAction>(0x19)
        register<EntityMetadataAction>(0x1C)
        register<EntityStatusAction>(0x1A)
        register<EntityTeleportAction>(0x18)
        register<EntityVelocityAction>(0x12)

        // effect actions
        register<EntityEffectAction>(0x1D)
        register<EntityEffectRemoveAction>(0x1E)
    }

    fun byId(id: Int): KClass<out Action>? = idToClass[id]

    inline fun <reified T : Action> byAction(): Int =
        classToId[T::class]!!

    private inline fun <reified T : Action> register(packetId: Int)
    {
        idToClass[packetId] = T::class
        classToId[T::class] = packetId
    }
}
