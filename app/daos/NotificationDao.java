package daos;

import models.Notification;
import org.sql2o.Connection;

import java.util.List;

public class NotificationDao extends BaseDao {

    public static String tableName = "notification";

    public Long create(Notification notification) {
        String sql = "INSERT INTO " + tableName + " " +
                "(user_id, content) VALUES (:userId, :content)";

        return insertModel(sql, notification);
    }

    public List<Notification> userNotifications(Integer userId) {
        String sql = "SELECT " +
                "n.content, n.created_at " +
                "FROM " + tableName + " n " +
                "WHERE user_id = :userId";

        List<Notification> notifications;
        try(Connection con = this.db.open()) {
            notifications = con.createQuery(sql)
                    .addParameter("userId", userId)
                    .executeAndFetch(Notification.class);
        }

        return notifications;
    }
}
