package wl.contract;

import java.util.Optional;

/**
 * Interface to retrieve objects.
 *
 * @param <T> Type of object to retrieve.
 * @param <K> Type of object id.
 */
public interface Retrieval<T, K> {

  /**
   * Finds entity by id.
   *
   * @param id Entity id.
   * @return A container object which may or may not contain entity.
   */
  Optional<T> getById(K id);
}
