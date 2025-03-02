package wl.user.contract;

import wl.contract.Registrar;

/**
 * Interface to register users.
 *
 * @param <T> Type of entities to register.
 */
public interface UserRegistrar<T> extends Registrar<T, Long> {

}
