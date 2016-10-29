package controllers.v1;

import play.mvc.Result;

public class DocController extends BaseController {

    public Result swagger() {
        return ok(new java.io.File("docs/willyfog-api-v1.yaml"))
                .withHeader(ACCESS_CONTROL_ALLOW_ORIGIN, "*");
    }
}
