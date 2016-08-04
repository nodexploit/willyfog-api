package daos;

import models.Comment;
import org.sql2o.Connection;

public class CommentDao extends BaseDao {

    public static String tableName = "comment";

    public Object create(Comment comment) {
        String sql = "INSERT INTO " + tableName + " " +
                "(user_id, request_id, content) " +
                "VALUES (:userId, :requestId, :content)";

        Object lastInsertedId;
        try(Connection con = this.db.open()) {
            lastInsertedId = con.createQuery(sql)
                    .bind(comment)
                    .executeUpdate()
                    .getKey();
        }

        return lastInsertedId;
    }
}
