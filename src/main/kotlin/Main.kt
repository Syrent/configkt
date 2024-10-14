package org.sayandev

import kotlinx.serialization.Serializable
import net.mamoe.yamlkt.Comment
import net.mamoe.yamlkt.Yaml
import java.io.File

fun main() {
    val file = File("test.yml").apply {
        if (!this.exists()) {
            this.createNewFile()
        }
    }
    file.writeText(Yaml.encodeToString(Test()))
    println("File saved at: ${file.absolutePath}")
}

@Serializable
data class Test(
    @Comment("This is a comment")
    val name: String = "foo",
    val bool: Boolean = true,
    val nested: Nested = Nested()
) {
    @Serializable
    data class Nested(
        @Comment("This is another comment")
        val data: String = "This is a nested data class",
    )
}