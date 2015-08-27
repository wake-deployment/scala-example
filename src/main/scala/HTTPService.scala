import akka.actor.ActorSystem
import akka.stream.{ActorMaterializer, Materializer}
import akka.http.scaladsl.Http
// import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.model.HttpMethods._
import akka.http.scaladsl.model._

object HTTPService {
  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()

  def route(req: HttpRequest): HttpResponse = {
    case HttpRequest(GET, Uri.Path("/"), _, _, _) => {
      println(req.toString)
      HttpResponse(entity = "Hello world!")
    }

    case HttpRequest(GET, Uri.Path("/ping"), _, _, _) =>
      HttpResponse(entity = "PONG!")

    case HttpRequest(GET, Uri.Path("/crash"), _, _, _) =>
      sys.error("BOOM!")

    case _: HttpRequest =>
      HttpResponse(404, entity = "Unknown resource!")
  }

  def run(cb: HttpRequest => HttpResponse) = {
    Http().bindAndHandleSync(route, "localhost", 8080)
  }
}

object Main {
  def main(argv: Array[String]):Unit = {
    HTTPService.run
  }
}
