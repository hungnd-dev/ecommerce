package vn.dev.danghung.cache;

/**
 * @author tatsuya
 */
public interface ICache<K, V> {
    public abstract void clear();

    public abstract boolean containsKey(Object key);

    public abstract V get(Object key) throws Exception;

    public abstract boolean isEmpty();

    public abstract V put(K key, V value);

    public abstract V remove(Object key);

    public abstract int size();
}