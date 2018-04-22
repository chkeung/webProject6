package ouhk.comps380f.dao;

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
import static ouhk.comps380f.dao.CommentRepositoryImpl.toDate;
import ouhk.comps380f.model.Bidding;
import ouhk.comps380f.model.Comment;

@Repository
public class BiddingRepositoryImpl implements BiddingRepository {

    @Autowired
    DataSource dataSource;

    private static final String SQL_INSERT_ENTRY
            = "insert into bidding (ticket_id, price, customerName, date) values (?, ?, ?, ?)";

    @Override
    public void addEntry(Bidding entry) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT_ENTRY);
            stmt.setString(1, entry.getTicket_id());
            stmt.setString(2, entry.getPrice());
            stmt.setString(3, entry.getCustomerName());
            stmt.setTimestamp(4, new Timestamp(entry.getDate().getTime()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            // do something ... not sure what, though
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                // Even less sure about what to do here
            }
        }
    }

    private static final String SQL_SELECT_ALL_ENTRY
            = "select * from bidding";

    @Override
    public List<Bidding> listEntries(String name) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_ALL_ENTRY);
            rs = stmt.executeQuery();
            List<Bidding> entries = new ArrayList<>();
            while (rs.next()) {
                System.out.println(name);
                Bidding entry = new Bidding();
                if (rs.getString("ticket_id").equals(name)) {
                    entry.setId(rs.getInt("id"));
                    entry.setTicket_id(rs.getString("ticket_id"));
                    entry.setPrice(rs.getString("price"));
                    entry.setCustomerName(rs.getString("customerName"));
                    entry.setDate(toDate(rs.getTimestamp("date")));
                    entries.add(entry);
                }

            }
            return entries;
        } catch (SQLException e) {
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
            }
        }
        return null;
    }

    private static final String SQL_SELECT_ALL_ENTRY2
            = "select distinct CUSTOMERNAME, ticket_id from bidding";

    @Override
    public List<Bidding> listCustomer(String name) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_ALL_ENTRY2);
            rs = stmt.executeQuery();
            List<Bidding> entries = new ArrayList<>();
            while (rs.next()) {
                Bidding entry = new Bidding();
                if (rs.getString("ticket_id").equals(name)) {
                    entry.setCustomerName(rs.getString("customerName"));
                    entries.add(entry);
                }

            }
            return entries;
        } catch (SQLException e) {
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
            }
        }
        return null;
    }

    public static Date toDate(Timestamp timestamp) {
        long milliseconds = timestamp.getTime() + (timestamp.getNanos() / 1000000);
        return new Date(milliseconds);
    }

}
