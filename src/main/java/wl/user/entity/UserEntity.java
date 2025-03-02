package wl.user.entity;

import java.util.Random;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * User entity record.
 */
@Data
@Builder
@Getter
@Setter(AccessLevel.NONE)
public final class UserEntity {

  /**
   * User ID.
   */
  private Long id;

  /**
   * Email.
   */
  private String email;

  /**
   * Phone number.
   */
  private String phoneNumber;

  /**
   * Password.
   */
  private String password;

  /**
   * Constructor.
   *
   * @param userId          User ID.
   * @param userEmail       Email.
   * @param userPhoneNumber Phone number.
   * @param userPassword    Password.
   */
  private UserEntity(
      final Long userId,
      final String userEmail,
      final String userPhoneNumber,
      final String userPassword
  ) {
    this.id = userId;
    this.email = userEmail;
    this.phoneNumber = userPhoneNumber;
    this.password = userPassword;
  }

  /**
   * Creates user entity builder.
   *
   * @return User entity builder.
   */
  public static UserEntityBuilder builder() {
    return new UserEntityBuilder();
  }

  /**
   * User entity builder.
   */
  public static final class UserEntityBuilder {

    /**
     * Creates new user entity.
     *
     * @return User entity.
     */
    public UserEntity build() {
      return new UserEntity(
          this.id != null ? this.id : new Random().nextLong(),
          this.email,
          this.phoneNumber,
          this.password
      );
    }
  }
}
