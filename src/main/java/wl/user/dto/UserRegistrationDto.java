package wl.user.dto;

/**
 * Represents a user information.
 *
 * @param email          Email.
 * @param phoneNumber    Phone number.
 * @param password       Password.
 * @param repeatPassword Password confirmation.
 */
public record UserRegistrationDto(
    String email,
    String phoneNumber,
    String password,
    String repeatPassword
) {

}
