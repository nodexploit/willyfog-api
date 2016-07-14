import com.twitter.finagle.Http
import com.twitter.finagle.exp.Mysql
import com.twitter.util.Await
import io.finch._

object Main {

  implicit val client = Mysql.client
    .withCredentials("root", "root")
    .withDatabase("prueba")
    .newRichClient("127.0.0.1:3306")
}
