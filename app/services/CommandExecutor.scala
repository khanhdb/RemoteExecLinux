package services

import javax.inject._
import play.api.Logger

/**
 * This trait demonstrates how to create a component that is injected
 * into a controller. The trait represents a counter that returns a
 * incremented number each time it is called.
 */
trait CommandExecutor {
  protected val logger: Logger = Logger(this.getClass)
  def execute(): Unit
}

/**
 * This class is a concrete implementation of the [[CommandExecutor]] trait.
 */
@Singleton
class AtomicCommandExecutor extends CommandExecutor {
  override def execute(): Unit = {
    import java.io.{BufferedReader, InputStreamReader}
    logger.debug("executing...")
    val p = new ProcessBuilder("/bin/bash","/home/khanhdb/docker/deploy.sh")
    val p2 = p.start()
    val br = new BufferedReader(new InputStreamReader(p2.getInputStream()))

    var line : String = ""
    while ({line = br.readLine();  line!= null}) {
      logger.debug(line)
    }
  }
}
