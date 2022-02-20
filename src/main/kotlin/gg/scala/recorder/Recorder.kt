package gg.scala.recorder

import com.cryptomorin.xseries.XSound.Record
import gg.scala.commons.ExtendedScalaPlugin
import gg.scala.flavor.Flavor
import gg.scala.flavor.FlavorOptions
import me.lucko.helper.plugin.ap.Plugin

/**
 * @author GrowlyX
 * @since 2/19/2022
 */
@Plugin(
    name = "Recorder",
    description = "Written by colinmcdonald22, ported to 1.8 & completed by GrowlyX."
)
class Recorder : ExtendedScalaPlugin()
{
    private val flavor by lazy {
        Flavor.create<Recorder>(
            FlavorOptions(logger)
        )
    }

    override fun enable()
    {
        flavor.bind<Recorder>() to this
        flavor.startup()
    }

    override fun disable()
    {
        flavor.close()
    }
}
