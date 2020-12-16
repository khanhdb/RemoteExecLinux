package controllers

import play.api.libs.json.JsValue

import javax.inject._
import play.api.mvc._
import services.CommandExecutor

@Singleton
class Controller @Inject()(cc: ControllerComponents,
                           commandExecutor: CommandExecutor) extends AbstractController(cc) {

  def execute: Action[JsValue]= Action(parse.json) { request =>
    try{
      val command = request.body("command").as[String]
      val secret = request.body("secret").as[String]
      if (secret == sys.env("EXEC_SECRET")){
        val result = commandExecutor.execute(command)
        Ok(result)
      } else Unauthorized("EXEC_SECRET doesn't matched")
    } catch {
      case _ : NoSuchElementException =>
        BadRequest("invalid body format or server has missing config")
    }
  }

}
