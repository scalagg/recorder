package gg.scala.recorder.actions

import gg.scala.flavor.service.Configure
import gg.scala.flavor.service.Service
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
