package wl.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import wl.user.contract.UserRepository;
import wl.user.contract.UserService;
import wl.user.dto.UserRegistrationDto;
import wl.user.dto.UserResponseDto;
import wl.user.entity.UserEntity;
import wl.user.repository.UserRepositoryImpl;
import wl.user.validator.UserValidator;

/**
 * Test for a user registration service.
 */
public final class UserServiceImplTest {

  @ParameterizedTest
  @CsvSource(
      value = {"fdf@g.com:+1342342345243:pwd:pwd"},
      delimiter = ':'
  )
  void register(
      final String email,
      final String phoneNumber,
      final String password,
      final String repeatPassword
  ) {
    UserRepository repository = mock(UserRepositoryImpl.class);

    /// Mock repository create method.
    when(repository.create(any(UserEntity.class))).thenAnswer(invocation -> {
      UserEntity userEntity = invocation.getArgument(0);

      return userEntity.getId();
    });
    /// Mock repository getById method.
    when(repository.getById(any(Long.class)))
        .thenAnswer(invocation -> Optional.of(
                UserEntity.builder()
                    .id(invocation.getArgument(0))
                    .email(email)
                    .phoneNumber(phoneNumber)
                    .password(password)
                    .build()
            )
        );

    UserService userService = new UserServiceImpl(
        mock(UserValidator.class),
        repository
    );

    UserRegistrationDto userRegistrationDto = new UserRegistrationDto(
        email, phoneNumber, password, repeatPassword
    );

    Long id = userService.register(userRegistrationDto);

    assertNotNull(id);

    Optional<UserResponseDto> userResponse = userService.getById(id);

    assertTrue(userResponse.isPresent());
    assertEquals(id, userResponse.get().id());
    assertEquals(email, userResponse.get().email());
    assertEquals(phoneNumber, userResponse.get().phoneNumber());
  }
}
