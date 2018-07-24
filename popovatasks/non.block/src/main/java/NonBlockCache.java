import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ThreadSafe
public class NonBlockCache implements Cache {
    @GuardedBy("this")
    private Map<Integer, Base> cache = new ConcurrentHashMap<>();

    @Override
    public void add(Base model) {
        this.cache.putIfAbsent(model.getId(), model);
    }

    @Override
    public void update(Base model) {
        this.cache.computeIfPresent(model.getId(), (id, obj) -> {
            if (obj.getVersion() == model.getVersion()) {
                model.update();
                return model;
            } else {
                throw new OptimisticException("Erasing data");
            }
        });
    }

    @Override
    public boolean delete(Base model) {
        return this.cache.remove(model.getId(), model);
    }

    @Override
    public Base get(int _id) {
        return this.cache.getOrDefault(_id, null);
    }
}
