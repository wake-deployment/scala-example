import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpMethods._
import akka.http.scaladsl.model._
import com.typesafe.config.ConfigFactory
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object HTTPService {
  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()

  val config = ConfigFactory.load

  def route(req: HttpRequest): Future[HttpResponse] = req match {
    case HttpRequest(GET, Uri.Path("/"), _, _, _) => Future { hello(req) }
    case HttpRequest(GET, Uri.Path("/echo"), _, _, _) => Future { echo(req) }
    case _: HttpRequest => Future { HttpResponse(404, entity = "Not found!") }
  }

  def run = {
    Http().bindAndHandleAsync(
      route,
      config.getString("http.interface"),
      config.getInt("http.port")
    )
  }

  def hello(req: HttpRequest): HttpResponse = {
    HttpResponse(entity = "Hello world!")
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
