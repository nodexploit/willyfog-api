package daos;

import org.sql2o.Connection;

public class OAuth2Dao extends BaseDao {

    public boolean validateAccessToken(String accessToken) {
        String sql = "SELECT COUNT(*) " +
                "FROM oauth_access_token " +
                "WHERE access_token = :accessToken";

        Integer count = 0;
        try(Connection con = this.db.open()) {
            count = con.createQuery(sql)
                    .addParameter("accessToken", accessToken)
                    .executeScalar(Integer.class);
        }

        return count > 0;
    }
}
