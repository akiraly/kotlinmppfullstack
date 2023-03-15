import kotlinx.serialization.Serializable
import react.create
import react.dom.client.createRoot
import web.dom.document

@Serializable
data class Video(
    val id: Int,
    val title: String,
    val speaker: String,
    val videoUrl: String
)

fun main() {
//    document.bgColor = "red"
//    val container = document.createElement("div")
//    document.body!!.appendChild(container)
//
//    val welcome = Welcome.create {
//        name = "Kotlin/JS 2"
//    }
//    createRoot(container).render(welcome)

//    val container = document.getElementById("root") ?: error("Couldn't find root container!")
//    createRoot(container).render(Fragment.create {
//        h1 {
//            +"KotlinConf Explorer"
//        }
//        div {
//            h3 {
//                +"Videos to watch"
//            }
//            for (video in unwatchedVideos) {
//                p {
//                    +"${video.speaker}: ${video.title}"
//                }
//            }
//            h3 {
//                +"Videos watched"
//            }
//            for (video in watchedVideos) {
//                p {
//                    +"${video.speaker}: ${video.title}"
//                }
//            }
//        }
//        div {
//            css {
//                position = Position.absolute
//                fontFamily = FontFamily.monospace
//                color = NamedColor.coral
//                top = 10.px
//                right = 10.px
//            }
//            h3 {
//                +"John Doe: Building and breaking things"
//            }
//            img {
//                src = "https://via.placeholder.com/640x360.png?text=Video+Player+Placeholder"
//            }
//        }
//    })

    val container = document.getElementById("root") ?: error("Couldn't find root container!")
    createRoot(container).render(App.create())
}