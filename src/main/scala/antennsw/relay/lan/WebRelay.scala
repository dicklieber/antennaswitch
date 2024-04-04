package antennsw.relay.lan

import antennsw.SwitchState
import antennsw.relay.{Relay, RelayBoard}
import com.typesafe.scalalogging.LazyLogging

import java.net.{URI, URL}
import java.net.http.{HttpClient, HttpRequest}
import java.net.http.HttpClient.{Redirect, Version}
import java.net.http.HttpResponse.BodyHandlers
import java.time.Duration

case class WebRelay(name: String, ipAddress: String) extends RelayBoard with LazyLogging:
  val client: HttpClient = HttpClient.newBuilder()
    .version(Version.HTTP_1_1)
    .followRedirects(Redirect.NEVER)
    .connectTimeout(Duration.ofSeconds(20))
    .build()

  def switch(switchState: SwitchState): Unit =
    // turn off other relays for the [[Rqadio]]
    // turn on the desired relay
    for (rn <- 1 to 8) {
      val bool = switchState.maybeAntenna.map(_.relayNumber == rn).getOrElse(false)
      op(rn-1, bool)
    }
    

  private def op(relayNumber: Int, on: Boolean = false): Unit =
    val shifted = relayNumber << 1

    val cmd = if (on)
      shifted | 1
    else
      shifted

    val sValue = f"$cmd%02d"

    val sUri = s"http://$ipAddress/30000/$sValue"
    

    val request: HttpRequest = HttpRequest.newBuilder()
      .uri(URI.create(sUri))
      .timeout(Duration.ofMinutes(2))
      //    .header("Content-Type", "application/json")
      
      .GET()
      .build()
    val response = client.send(request, BodyHandlers.ofString())

    response.statusCode() match
      case 200 =>
        logger.debug(response.body())
      case err =>
        logger.trace(" {}", err)
        val body: String = response.body()
        logger.trace(body)
  
  
