@file:Import("logger.main.kts")
@file:Repository("https://jitpack.io")
@file:Repository("https://repo1.maven.org/maven2/")
@file:DependsOn("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2")
@file:CompilerOptions("-jvm-target", "1.8")

@file:Suppress("UNUSED_PARAMETER")

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.io.File

val path: String =args.getOrElse(0){System.getProperty("user.dir")}  
val dir: File =File(path)

val maxDepth:Int =args.getOrElse(1){4}.toString().toInt() // args.getOrElse(1){4} as Int
val levelOffset = dir.toString().count { it==File.separatorChar} -1 // do not count the root

suspend fun generateDirectoriesTree(file:File, maxDepth:Int, levelOffset:Int) = withContext(Dispatchers.IO) {
    val indentCharacter = " "
    file.walkTopDown().onEnter { !it.isHidden }.onFail { file, ioException -> val errorMessage = "The following error occurred while traversing file $file $ioException";logger.error(errorMessage, null)
    }.maxDepth(maxDepth).sorted().forEach { file->
        // C:\Users\User Name\IdeaProjects\DirectoryTreeGenerator/src so this is in level 2 relative to the root dir DirectoryTreeGenerator :XD
        val level = file.absolutePath.count { it == File.separatorChar } - levelOffset
        val emoji = if (file.isDirectory) "\uD83D\uDCC1" else "\uD83D\uDDBA" // not properly encoded in windows thus no need of showing emojis
        when(level){
            1->println("$indentCharacter.$level ${file.absolutePath}")
            else->println("${indentCharacter * level}.$level {${file.name}}")
        }
    }
}
operator fun CharSequence.times(count:Int) = repeat(count)
fun <T> runScript(block:suspend CoroutineScope.()->T){
    runBlocking {
        block()
    }
}
fun main(args:Array<String>) {
    runScript {
        require(dir.isDirectory && dir.exists()) {
            val errorMessage = "The Path $dir does not exist!";logger.error(
            errorMessage,
            null
        )
        }
        generateDirectoriesTree(dir, maxDepth, levelOffset)
    }
}
main(args)