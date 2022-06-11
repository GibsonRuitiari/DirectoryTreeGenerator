import java.text.SimpleDateFormat
import java.util.Date

val ANSI_RESET="\u001B[0m"
val GREEN="\\u001B[32m" // info
val RED="\u001B[31m" // error
interface Logger {
    fun info(msg:String?)
    fun error(msg: String?,err:Throwable?)
}

val logger = object :Logger{
    val currentDate = SimpleDateFormat("YYYY-MM-D hh:mm:ss").format(Date())
    override fun info(msg: String?) {
        val infoMessage =" $GREEN$currentDate | INFO  | $msg$ANSI_RESET"
        println(infoMessage)
    }
    override fun error(msg: String?, err: Throwable?) {
        val errorMsg ="$RED$currentDate | ERROR | $msg$ANSI_RESET"
        println(errorMsg)
    }
}

