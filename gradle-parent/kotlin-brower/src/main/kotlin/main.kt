import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.EventListener
import kotlin.browser.*
import kotlinx.html.*
import kotlinx.html.dom.*

fun main() {
    document.write("<h1 id='title'>Hello, kotlin world!</h1>")
    document.getElementById("title")?.addEventListener("click", EventListener {
        console.log("you click the h1 title!")
    })

    document.getElementById("fillEmail")?.addEventListener("click", EventListener {
        val email = document.getElementById("email") as HTMLInputElement
        email.value = "hadi@jetbrains.com"
    })

    document.body!!.append.div {
        h1 {
            +"Welcome to Kotlin/JS!"
        }
        p {
            +"Fancy joining this year's "
            a("https://kotlinconf.com/") {
                +"KotlinConf"
            }
            +"?"
        }
    }

    console.log("Hello, Kotlin/JS!")
}