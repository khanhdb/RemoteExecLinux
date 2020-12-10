package controllers

import javax.inject._
import play.api.mvc._
import services.CommandExecutor

@Singleton
class Controller @Inject()(cc: ControllerComponents,
                           commandExecutor: CommandExecutor) extends AbstractController(cc) {

  def execute = Action {
    val result = commandExecutor.execute()
    Ok(result)
  }

}
