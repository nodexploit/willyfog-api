package daos;

import models.Comment;
import org.sql2o.Connection;

import java.util.List;
import java.util.Map;

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

    public List<Map<String, Object>> requestComments(Long requestId) {
        String sql = "SELECT " +
                "c.id, c.user_id, c.content, c.created_at, " +
                "u.name AS user_name, u.surname AS user_surname " +
                "FROM " + tableName + " c " +
                "JOIN " + UserDao.tableName + " u ON c.user_id = u.id " +
                "WHERE request_id = :requestId " +
                "ORDER BY c.created_at DESC";

        List<Map<String, Object>> comments;
        try(Connection con = this.db.open()) {
            comments = toMapList(con.createQuery(sql)
                    .addParameter("requestId", requestId)
                    .executeAndFetchTable());
        }

        return comments;
    }
}
