package antennsw

import com.google.inject.{AbstractModule, PrivateModule}
import com.typesafe.config.{Config, ConfigFactory}
import net.codingwell.scalaguice.*

class MyModule extends AbstractModule with ScalaModule {
  override def  configure(): Unit = 
    val config: Config = ConfigFactory.load()
    bind[Config].toInstance(config)
}