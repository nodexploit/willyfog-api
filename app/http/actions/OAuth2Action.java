package http.actions;

import com.google.gson.Gson;
import com.google.inject.Inject;
import daos.OAuth2Dao;
import http.ErrorResponse;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class OAuth2Action extends play.mvc.Action.Simple {

    @Inject
    private OAuth2Dao oauth2dao;
    @Inject
    private Gson gson;

    @Override
    public CompletionStage<Result> call(Http.Context ctx) {
        CompletionStage<Result> result = null;

        Http.Request request = ctx.request();

        String authorization = request.getHeader("Authorization");
        String[] split = authorization.split(" ");
        String accessToken = split[1];

        boolean authorized = oauth2dao.validateAccessToken(accessToken);

        if (authorized) {
            result = delegate.call(ctx);
        } else {
            result = CompletableFuture.completedFuture(
                    Results.unauthorized(gson.toJson(new ErrorResponse("Not authorized", "")))
            );
        }

        return result;
    }
}
