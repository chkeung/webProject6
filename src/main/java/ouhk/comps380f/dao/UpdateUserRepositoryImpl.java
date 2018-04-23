package ouhk.comps380f.dao;

import ouhk.comps380f.model.TicketUser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UpdateUserRepositoryImpl implements UpdateUserRepository {

    @Autowired
    DataSource dataSource;
    
    private static final String SQL_UPDATE_ENTRY
            = "update users set password = ? where username = ?";

    @Override
    public void updateEntry(TicketUser tt, String username) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE_ENTRY);
            stmt.setString(1, tt.getPassword());
            stmt.setString(2, username);
            stmt.executeUpdate();
        } catch (SQLException e) {
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
            }
        }
    }

    

    public static Date toDate(Timestamp timestamp) {
        long milliseconds = timestamp.getTime() + (timestamp.getNanos() / 1000000);
        return new Date(milliseconds);
    }

}
