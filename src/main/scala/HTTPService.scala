import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpMethods._
import akka.http.scaladsl.model._
import com.typesafe.config.ConfigFactory

object HTTPService {
  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()

  def route(req: HttpRequest): HttpResponse = req match {
    case HttpRequest(GET, Uri.Path("/"), _, _, _) => {
      println(req.toString)
      HttpResponse(entity = "Hello world!")
    }
  val config = ConfigFactory.load

    case HttpRequest(GET, Uri.Path("/ping"), _, _, _) =>
      HttpResponse(entity = "PONG!")

    case HttpRequest(GET, Uri.Path("/crash"), _, _, _) =>
      sys.error("BOOM!")

    case _: HttpRequest =>
      HttpResponse(404, entity = "Unknown resource!")
  }

  def echo(req: HttpRequest): HttpResponse = {
    HttpResponse(entity = req.toString)
  }
}

object Main {
  def main(argv: Array[String]):Unit = {
    HTTPService.run
  }
}
