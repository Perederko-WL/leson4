package wl.contract;

/**
 * Interface for validators.
 *
 * @param <T> Type of object to validate.
 */
public interface Validator<T> {

  /**
   * Validates object.
   *
   * @param dto Object to validate.
   * @throws wl.exception.ValidationException In case of validation errors.
   */
  void validate(T dto);
}
