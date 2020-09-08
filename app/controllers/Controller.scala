package controllers

import javax.inject._
import play.api.mvc._
import services.CommandExecutor
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


/**
 * This controller demonstrates how to use dependency injection to
 * bind a component into a controller class. The class creates an
 * `Action` that shows an incrementing count to users. The [[CommandExecutor]]
 * object is injected by the Guice dependency injection system.
 */



@Singleton
class Controller @Inject()(cc: ControllerComponents,
                           commandExecutor: CommandExecutor) extends AbstractController(cc) {

  /**
   * Create an action that responds with the [[CommandExecutor]]'s current
   * count. The result is plain text. This `Action` is mapped to
   * `GET /count` requests by an entry in the `routes` config file.
   */
  def execute = Action {
    Future {
      commandExecutor.execute()
    }
    Ok("executed")
  }

}
