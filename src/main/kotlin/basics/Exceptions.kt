package basics

import java.nio.file.Files
import java.nio.file.Path

fun main() {
    readFile(Path.of("/home/kir/IdeaProjects/kotlin/build.gradle.kts"))

}

private fun readFile(path: Path): String {
    val inputStream = Files.newInputStream(path)
    try {
        var byte = inputStream.read()
        while (byte != -1) {
            println(byte)
            byte = inputStream.read()
        }
    } catch (e: Exception) {
        System.err.println(e.message)
    } finally {
        System.err.println("closing input")
        inputStream.close()
    }
    return "Yep"
}