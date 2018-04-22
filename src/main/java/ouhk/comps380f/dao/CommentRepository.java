package ouhk.comps380f.dao;

import ouhk.comps380f.model.Comment;
import java.util.List;

public interface CommentRepository {

    public void addEntry(Comment e);

    public void updateEntry(Comment e);

    public List<Comment> listEntries(String name);

    public Comment getEntryById(Integer id);
    
    public Comment getEntryByName(String name);

    public void removeEntryById(Integer id);

}
