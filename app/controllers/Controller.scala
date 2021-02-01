package controllers

import play.api.{Configuration, Logger}
import play.api.libs.json.JsValue

import javax.inject._
import play.api.mvc._
import services.CommandExecutor

@Singleton
class Controller @Inject()(cc: ControllerComponents,
                           commandExecutor: CommandExecutor, config : Configuration) extends AbstractController(cc) {

  protected val logger: Logger = Logger(this.getClass)
  def execute: Action[JsValue]= Action(parse.json) { request =>
    try{
      val command = request.body("command").as[String]
      val secret = request.body("secret").as[String]
      val secretInConfig = config.get[String]("secret")
      if (secret == secretInConfig){
        val result = commandExecutor.execute(command)
        Ok(result)
      } else Unauthorized("secret doesn't matched")
    } catch {
      case _ : NoSuchElementException =>
        BadRequest("invalid body format or server has missing config")
    }
  }

}
