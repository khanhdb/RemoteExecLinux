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
  def execute(cmd: String): String
}

/**
 * This class is a concrete implementation of the [[CommandExecutor]] trait.
 */
@Singleton
class AtomicCommandExecutor extends CommandExecutor {
  import scala.sys.process._
  override def execute(cmd : String): String = {
    val output = cmd !!;
    logger.debug(output)
    output
  }
}
