import js.core.jso
import react.FC
import react.Props
import react.create
import react.router.RouterProvider
import react.router.dom.createHashRouter

val App = FC<Props> {
    val hashRouter = createHashRouter(
        routes = arrayOf(
            jso {
                path = "/"
                element = VideoApp.create()
                //errorElement = Error.create()
            },
            jso {
                path = "/welcome"
                element = Welcome.create {
                    name = "John Doe"
                }
                //errorElement = Error.create()
            },
        ),
    )


    RouterProvider {
        router = hashRouter
    }

}