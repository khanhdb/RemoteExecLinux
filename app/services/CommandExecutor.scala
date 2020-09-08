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
    runCommand("/bin/bash", "/home/khanhdb/docker/build-image.sh", "re-build image from latest code...", 4)
    runCommand("docker-compose", "-f /home/khanhdb/docker/docker-compose.yml up -d", "docker-compose up...", 4)
  }

  private def runCommand(command : String, param : String, startLog : String, maxEmptyLine: Int): Unit = {
    import java.io.{BufferedReader, InputStreamReader}
    logger.debug(startLog)
    val p = new ProcessBuilder(command, param)
    val p2 = p.start()
    val br = new BufferedReader(new InputStreamReader(p2.getInputStream))

    var line : String = ""
    var nullLineCount = 0
    while ({line = br.readLine(); nullLineCount < maxEmptyLine}) {
      nullLineCount = if (line == null) nullLineCount + 1 else nullLineCount
      logger.debug(line)
    }
    logger.debug("done")
  }
}
