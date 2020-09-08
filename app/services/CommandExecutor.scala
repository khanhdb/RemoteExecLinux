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
  import scala.sys.process._
  override def execute(): Unit = {
    val output = "/home/khanhdb/docker/build-image.sh" #&& "docker-compose -f /home/khanhdb/docker/docker-compose.yml up -d" !!;
    logger.debug(output)
  }
}
