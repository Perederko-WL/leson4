package wl.exception;

import java.util.List;

/**
 * Validation exception.
 */
public class ValidationException extends RuntimeException {

  /**
   * Constructor.
   *
   * @param violations List of errors.
   */
  public ValidationException(final List<String> violations) {
    super("Validation errors: " + String.join("; ", violations));
  }
}
