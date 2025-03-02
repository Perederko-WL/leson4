package wl.user.validator;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.RegexValidator;
import wl.contract.Validator;
import wl.exception.ValidationException;
import wl.user.dto.UserRegistrationDto;

/**
 * User registration validator.
 */
public final class UserValidator implements Validator<UserRegistrationDto> {

  @Override
  public void validate(final UserRegistrationDto dto) {
    List<String> violations = new ArrayList<>();

    if (!isValidPassword(dto.password(), dto.repeatPassword())) {
      violations.add("Invalid password.");
    }
    if (!isValidPhoneNumber(dto.phoneNumber())) {
      violations.add("Invalid phone number.");
    }
    if (!isValidEmail(dto.email())) {
      violations.add("Invalid email.");
    }

    if (!violations.isEmpty()) {
      throw new ValidationException(violations);
    }
  }

  /**
   * Checks that password is valid.
   *
   * @param password       Password.
   * @param repeatPassword Password confirmation.
   * @return `true` if password is valid, `false` - otherwise.
   */
  private boolean isValidPassword(
      final String password,
      final String repeatPassword
  ) {
    if (
        password == null || password.isBlank()
            || repeatPassword == null || repeatPassword.isBlank()
    ) {
      return false;
    }

    return password.equals(repeatPassword);
  }

  /**
   * Checks that phone number is valid.
   *
   * @param phoneNumber Phone number.
   * @return `true` if phone number is valid, `false` - otherwise.
   */
  private boolean isValidPhoneNumber(final String phoneNumber) {
    if (phoneNumber == null || phoneNumber.isBlank()) {
      return false;
    }

    return new RegexValidator(
        "^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))"
            + "|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$"
    ).isValid(phoneNumber);
  }

  /**
   * Checks that email is valid.
   *
   * @param email Email.
   * @return `true` if email is valid, `false` - otherwise.
   */
  private boolean isValidEmail(final String email) {
    if (email == null || email.isBlank()) {
      return false;
    }

    return EmailValidator.getInstance().isValid(email);
  }
}
