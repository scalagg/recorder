package gg.scala.recorder

import net.evilblock.cubed.util.Reflection

/**
 * @author GrowlyX
 * @since 2/19/2022
 */
fun Any.field(name: String, any: Any)
{
    Reflection.setFieldValue(this, name, any)
}
