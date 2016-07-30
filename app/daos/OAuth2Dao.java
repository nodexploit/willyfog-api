package daos;

import models.OAuthAccessToken;
import org.sql2o.Connection;

public class OAuth2Dao extends BaseDao {

    public static String tableName = "oauth_access_token";

    public boolean validateAccessToken(String accessToken) {
        String sql = "SELECT * " +
                "FROM " + tableName + " " +
                "WHERE access_token = :accessToken";

        OAuthAccessToken oAuthAccessToken;
        try(Connection con = this.db.open()) {
            oAuthAccessToken = con.createQuery(sql)
                    .addParameter("accessToken", accessToken)
                    .executeAndFetchFirst(OAuthAccessToken.class);
        }

        return oAuthAccessToken != null && !oAuthAccessToken.isExpired();
    }
}
