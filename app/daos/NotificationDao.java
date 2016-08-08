package daos;

import models.Notification;
import org.sql2o.Connection;

import java.time.LocalDateTime;
import java.util.List;

public class NotificationDao extends BaseDao {

    public static String tableName = "notification";

    public Long create(Notification notification) {
        String sql = "INSERT INTO " + tableName + " " +
                "(user_id, content) VALUES (:userId, :content)";

        return insertModel(sql, notification);
    }

    public List<Notification> userNotifications(Long userId) {
        String sql = "SELECT " +
                "n.content, n.created_at " +
                "FROM " + tableName + " n " +
                "WHERE user_id = :userId " +
                "AND n.read_at IS NULL";

        List<Notification> notifications;
        try(Connection con = this.db.open()) {
            notifications = con.createQuery(sql)
                    .addParameter("userId", userId)
                    .executeAndFetch(Notification.class);
        }

        return notifications;
    }

    public void setRead(Long userId) {
        String sql = "UPDATE " +
                tableName + " n " +
                "SET read_at = :readAt " +
                "WHERE user_id = :userId " +
                "AND n.read_at IS NULL";

        try(Connection con = this.db.open()) {
            con.createQuery(sql)
                    .addParameter("readAt", LocalDateTime.now())
                    .addParameter("userId", userId)
                    .executeUpdate();
        }
    }
}
