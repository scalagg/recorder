package gg.scala.recorder

import gg.scala.commons.ExtendedScalaPlugin
import gg.scala.commons.annotations.container.ContainerDisable
import gg.scala.commons.annotations.container.ContainerEnable
import me.lucko.helper.plugin.ap.Plugin
import me.lucko.helper.plugin.ap.PluginDependency

/**
 * @author GrowlyX
 * @since 2/19/2022
 */
@Plugin(
    name = "KeRecord",
    authors = ["colinmcdonald22", "GrowlyX"],
    depends = [
        PluginDependency("scala-commons"),
        PluginDependency("store-spigot")
    ]
)
class KeRecordPlugin : ExtendedScalaPlugin()
{
    @ContainerEnable
    fun containerEnable()
    {
        // something
    }

    @ContainerDisable
    fun containerDisable()
    {
        // something
    }
}
