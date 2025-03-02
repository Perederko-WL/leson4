package wl.contract;

/**
 * Interface to register entities.
 *
 * @param <T> Type of entities to register.
 * @param <R> Type of returned value.
 */
public interface Registrar<T, R> {

  /**
   * Registers a user.
   *
   * @param dto Entity representation to register.
   * @return Registered user id.
   */
  R register(T dto);
}
