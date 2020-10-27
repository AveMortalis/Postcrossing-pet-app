package dao;

import entity.AwaitList;
import entity.User;
import java.util.List;

public interface IWaiters {

    public List<AwaitList> getAll();

    public AwaitList getFirstRecordFromAwaitList(User user);

    public void delete(AwaitList awaitList);

    public void add(AwaitList awaitList);

    public void addUserToAwaitList(User user);
}
