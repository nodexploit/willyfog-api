package controllers;

import play.*;
import play.mvc.*;

public class Application extends Controller {

    public Result greet() {
        return ok("Hello world");
    }
}
