
//import io.ktor.application.*
//import io.ktor.features.ContentNegotiation
//import io.ktor.html.respondHtml
//import io.ktor.request.receiveParameters
//import io.ktor.routing.*
//import io.ktor.serialization.json
//import io.ktor.sessions.*
//import io.ktor.html.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import kotlinx.html.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.html.*
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*

data class UserSession(val name: String)

fun main() {
    embeddedServer(Netty, port = 8080, module = Application::module).start(wait = true)
}

fun Application.module() {
    install(Sessions) {
        cookie<UserSession>("user_session")
    }
    install(ContentNegotiation) {
        json()
    }

    routing {
        get("/") {
            call.respondHtml {
                head {
                    title("Registration")
                }
                body {
                    h1 { +"Registration" }
                    form(action = "/register", method = FormMethod.post) {
                        textInput(name = "username") {
                            placeholder = "Username"
                        }
                        textInput(name = "password") {
                            placeholder = "Password"
                            attributes["type"] = "password"
                        }
                        submitInput { value = "Register" }
                    }
                }
            }
        }

        post("/register") {
            val params = call.receiveParameters()
            val username = params["username"] ?: ""
            val password = params["password"] ?: ""

            // Здесь можно добавить логику для сохранения пользователя в базу данных

            call.sessions.set(UserSession(username))
            call.respondHtml {
                head {
                    title("Welcome")
                }
                body {
                    h1 { +"Welcome, $username!" }
                }
            }
        }
    }
}
