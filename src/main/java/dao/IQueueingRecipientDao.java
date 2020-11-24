package dao;

import entity.QueueingRecipient;
import entity.User;
import java.util.List;

public interface IQueueingRecipient {

    public List<QueueingRecipient> getAll();

    public QueueingRecipient getFirstRecipientFromFromQueueingRecipientsButNotCurrent(User user);

    public void delete(QueueingRecipient queueingRecipient);

    public void add(QueueingRecipient queueingRecipient);

    public void addUserToAwaitList(User user);
}
