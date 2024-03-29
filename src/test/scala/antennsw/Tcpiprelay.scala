package antennsw

import java.io.OutputStream
import java.net.http.*
import java.net.http.HttpClient.{Redirect, Version}
import java.net.http.HttpResponse.{BodyHandler, BodyHandlers}
import java.net.{URI, URL, URLConnection}
import java.time.Duration
import scala.language.postfixOps

object Tcpiprelay extends App{
  val client: HttpClient = HttpClient.newBuilder()
    .version(Version.HTTP_1_1)
    .followRedirects(Redirect.NORMAL)
    .connectTimeout(Duration.ofSeconds(20))
    .build()
  apply(22, true)
  Thread.sleep(1000)
  apply(22, false)
  for(r <- 0 to 15){
    apply(r, true)
    Thread.sleep(250)
    apply(r, false)
    Thread.sleep(250)
  }

  def apply(relay:Int, on:Boolean)= 
    val shifted = relay << 1
    val cmd = if(on)
      shifted | 1
    else
      shifted

    val sValue = f"$cmd%02d"
    println(sValue)


    val sUri = s"http://192.168.1.4/30000/$sValue"
    val request: HttpRequest = HttpRequest.newBuilder()
      .uri(URI.create(sUri))
      .timeout(Duration.ofMinutes(2))
  //    .header("Content-Type", "application/json")
      .GET()
      .build()
    print(sUri)
    val response = client.send(request, BodyHandlers.ofString())
    
    response.statusCode() match
      case 200 =>
        println(" Ok")
      case err =>
        println(s" $err")
        val body: String = response.body()
        println(body)
    
}
