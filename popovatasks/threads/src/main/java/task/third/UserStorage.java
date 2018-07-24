package task.third;

import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.List;

@ThreadSafe
public class UserStorage {
    List<User> listUsers = new ArrayList<User>();

    public boolean add(User use) {
        boolean result = false;
        synchronized (listUsers) {
            if (!listUsers.contains(use)) {
                listUsers.add(use);
                result = true;
            }
        }
        return result;
    }

    public boolean update(final User use) {
        boolean result = false;
        synchronized (listUsers) {
            if (listUsers.contains(use)) {
                listUsers.stream().filter(user -> use.equals(user)).forEach(x -> x.setAmount(use.getAmount()));
                result = true;
            }
        }
        return result;
    }

    public boolean delete(User use) {
        boolean result = false;
        synchronized (listUsers) {
            if (listUsers.contains(use)) {
                listUsers.remove(use);
                result = true;
            }
        }
        return result;
    }

    public void transfer(int fromId, int toId, int amount) {
        User from = new User(fromId);
        User to = new User(toId);
        synchronized (listUsers) {
            if (listUsers.contains(from) && listUsers.contains(to)) {
                listUsers.stream().filter(user -> from.equals(user))
                        .forEach(x -> {
                            x.changeAmount(-amount);
                            listUsers.stream().filter(u -> u.equals(to))
                                    .forEach(y -> y.changeAmount(amount));
                        });
            }
        }
    }

    public List<User> getListUsers() {
        synchronized (listUsers) {
            return listUsers;
        }
    }
}
