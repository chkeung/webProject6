package ouhk.comps380f.dao;

import ouhk.comps380f.model.Comment;
import java.util.List;
import ouhk.comps380f.model.Bidding;

public interface BiddingRepository {

    public void addEntry(Bidding e);

    //public void updateEntry(Bidding e);

    public List<Bidding> listEntries(String name);
    
    public List<Bidding> listCustomer(String name);

    //public Bidding getEntryById(Integer id);
    
    //public Bidding getEntryByName(String name);

    //public void removeEntryById(Integer id);

}
