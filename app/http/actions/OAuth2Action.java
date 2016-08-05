package http.actions;

import com.google.gson.Gson;
import com.google.inject.Inject;
import daos.OAuth2Dao;
import http.ErrorResponse;
import models.OAuthAccessToken;
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
        CompletionStage<Result> result;
        boolean authorized = false;

        Http.Request request = ctx.request();
        String authorization = request.getHeader("Authorization");

        if (authorization != null) {
            String[] split = authorization.split(" ");

            String accessToken;
            if (split.length > 1) {
                accessToken = split[1];
                OAuthAccessToken oAuthAccessToken = oauth2dao.accessToken(accessToken);
                authorized = validateAccessToken(oAuthAccessToken);
                ctx.args.put("user_id", oAuthAccessToken.getUserId());
            }
        }

        if (authorized) {
            result = delegate.call(ctx);
        } else {
            result = CompletableFuture.completedFuture(
                    Results.unauthorized(gson.toJson(new ErrorResponse("Not authorized")))
            );
        }

        return result;
    }

    private boolean validateAccessToken(OAuthAccessToken oAuthAccessToken) {
        return oAuthAccessToken != null &&
                !oAuthAccessToken.isExpired();
    }
}
