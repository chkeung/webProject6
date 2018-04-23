package ouhk.comps380f.dao;

import ouhk.comps380f.model.Comment;
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
public class CommentRepositoryImpl implements CommentRepository {

    @Autowired
    DataSource dataSource;

    private static final String SQL_INSERT_ENTRY
            = "insert into comment (name, message, date, customer) values (?, ?, ?, ?)";

    @Override
    public void addEntry(Comment entry) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT_ENTRY);
            stmt.setString(1, entry.getName());
            stmt.setString(2, entry.getMessage());
            stmt.setTimestamp(3, new Timestamp(entry.getDate().getTime()));
            stmt.setString(4, entry.getCustomer());
            System.out.println("on99");
            System.out.println(entry.getCustomer());
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

    private static final String SQL_UPDATE_ENTRY
            = "update comment set name = ?, message = ?, date = ? where id = ?";

    @Override
    public void updateEntry(Comment entry) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE_ENTRY);
            stmt.setString(1, entry.getName());
            stmt.setString(2, entry.getMessage());
            stmt.setTimestamp(3, new Timestamp(entry.getDate().getTime()));
            stmt.setInt(4, entry.getId());
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

    private static final String SQL_SELECT_ALL_ENTRY
            = "select id, name, message, date, customer from comment";

    @Override
    public List<Comment> listEntries(String name) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_ALL_ENTRY);
            rs = stmt.executeQuery();
            List<Comment> entries = new ArrayList<>();
            while (rs.next()) {
                System.out.println(name);
                Comment entry = new Comment();
                if(rs.getString("name").equals(name)){
                    entry.setId(rs.getInt("id"));
                    entry.setName(rs.getString("name"));
                    entry.setCustomer(rs.getString("customer"));
                    entry.setMessage(rs.getString("message"));
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

    private static final String SQL_SELECT_ENTRY
            = "select id, name, message, date from comment where id = ?";

    @Override
    public Comment getEntryById(Integer id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_ENTRY);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            Comment entry = null;
            if (rs.next()) {
                entry = new Comment();
                entry.setId(rs.getInt("id"));
                entry.setName(rs.getString("name"));
                entry.setMessage(rs.getString("message"));
                entry.setDate(toDate(rs.getTimestamp("date")));
            }
            return entry;
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
    
    private static final String SQL_SELECT_ENTRY2
            = "select id, name, message, date from comment where name like '%?'";

    @Override
    public Comment getEntryByName(String name) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_ENTRY2);
            stmt.setString(1, name);
            rs = stmt.executeQuery();
            Comment entry = null;
            if (rs.next()) {
                entry = new Comment();
                entry.setId(rs.getInt("id"));
                entry.setName(rs.getString("name"));
                entry.setMessage(rs.getString("message"));
                entry.setDate(toDate(rs.getTimestamp("date")));
            }
            return entry;
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

    private static final String SQL_DELETE_ENTRY
            = "delete from comment where id = ?";

    @Override
    public void removeEntryById(Integer id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE_ENTRY);
            stmt.setInt(1, id);
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
