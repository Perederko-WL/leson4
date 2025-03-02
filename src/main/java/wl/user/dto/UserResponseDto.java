package wl.user.dto;

import wl.user.entity.UserEntity;

/**
 * Represents a user information.
 *
 * @param id          User ID.
 * @param email       Email.
 * @param phoneNumber Phone number.
 */
public record UserResponseDto(
    Long id,
    String email,
    String phoneNumber
) {

  /**
   * Creates user response representation.
   *
   * @param entity User entity.
   * @return User representation response object.
   */
  public static UserResponseDto createFrom(final UserEntity entity) {
    return new UserResponseDto(
        entity.getId(),
        entity.getEmail(),
        entity.getPhoneNumber()
    );
  }
}
