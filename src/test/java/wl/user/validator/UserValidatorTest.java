package wl.user.validator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import wl.exception.ValidationException;
import wl.user.dto.UserRegistrationDto;

/**
 * Test for a user registration validator.
 */
public final class UserValidatorTest {

  /**
   * User registration validator.
   */
  private final UserValidator userValidator = new UserValidator();

  @ParameterizedTest
  @CsvSource(
      value = {
          "fdf@g.com:+1342342345243:pwd_1:pwd_1",
          "3wer@g.com:+1398342345243:pwd_2:pwd_2"
      },
      delimiter = ':'
  )
  void passValidation(
      final String email,
      final String phoneNumber,
      final String password,
      final String repeatPassword
  ) {
    UserRegistrationDto dto = new UserRegistrationDto(
        email, phoneNumber, password, repeatPassword
    );
    assertDoesNotThrow(() -> this.userValidator.validate(dto));
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {"sdf", "dsd@", "dsfs@dfs", "dsfs@ewe"})
  void failInvalidEmail(final String email) {
    UserRegistrationDto dto = new UserRegistrationDto(
        email, "+1342342345243", "pwd", "pwd"
    );
    assertThrows(
        ValidationException.class,
        () -> this.userValidator.validate(dto)
    );
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {"+134234"})
  void failInvalidPhoneNumber(final String phoneNumber) {
    UserRegistrationDto dto = new UserRegistrationDto(
        "test@g.com", phoneNumber, "pwd", "pwd"
    );
    assertThrows(
        ValidationException.class,
        () -> this.userValidator.validate(dto)
    );
  }

  @ParameterizedTest
  @CsvSource(
      value = {":", "test:test1", "tEst:test", "Java:java"},
      delimiter = ':'
  )
  void failInvalidPhoneNumber(
      final String password,
      final String repeatPassword
  ) {
    UserRegistrationDto dto = new UserRegistrationDto(
        "test@g.com", "+1342342345243", password, repeatPassword
    );
    assertThrows(
        ValidationException.class,
        () -> this.userValidator.validate(dto)
    );
  }
}
