import com.example.BrowserRouterTest
import com.example.HashRouterTest
import kotlinx.browser.document
import react.Props
import react.ReactElement
import react.create
import react.dom.client.createRoot

fun main() {
    val container = document.createElement("div")
    document.body?.appendChild(container)

    /*
     * Unset to see the difference between the `BrowserRouter` and the `HashRouter`.
     */
    val useBrowserRouter = true

    val content: ReactElement<Props> = when {
        useBrowserRouter -> BrowserRouterTest.create()
        else -> HashRouterTest.create()
    }

    createRoot(container).render(content)
}
