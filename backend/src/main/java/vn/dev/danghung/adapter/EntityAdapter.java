package vn.dev.danghung.adapter;

/**
 * an adapter for client response model
 * transform entity in system to an different entity which responses to client
 *
 * @author tatsuya
 */
public interface EntityAdapter<T, R> {
    /**
     * perform an operation with entity
     *
     * @param entity entity to perform
     * @return response entity for client
     */
    public abstract R transform(T entity);
}
