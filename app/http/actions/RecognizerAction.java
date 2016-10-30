package http.actions;

import com.google.gson.Gson;
import com.google.inject.Inject;
import daos.UserHasRoleDao;
import http.ErrorResponse;
import models.OAuthAccessToken;
import models.Role;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static play.mvc.Http.HeaderNames.CONTENT_TYPE;

public class RecognizerAction extends play.mvc.Action.Simple {

    @Inject
    private UserHasRoleDao userHasRoleDao;
    @Inject
    private Gson gson;

    @Override
    public CompletionStage<Result> call(Http.Context ctx) {

        CompletionStage<Result> result;
        boolean authorized;

        Long roleId = userHasRoleDao.userRole((Long) ctx.args.get("user_id"));

        authorized = roleId == Role.RECOG;

        if (authorized) {
            result = delegate.call(ctx);
        } else {
            result = CompletableFuture.completedFuture(
                    Results.unauthorized(gson.toJson(new ErrorResponse("Not authorized")))
                            .withHeader(CONTENT_TYPE, "application/json")
            );
        }

        return result;
    }

    private boolean validateAccessToken(OAuthAccessToken oAuthAccessToken) {
        return oAuthAccessToken != null &&
                !oAuthAccessToken.isExpired();
    }
}
