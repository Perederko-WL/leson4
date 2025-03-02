package wl.user.service;

import java.util.Optional;
import org.apache.commons.codec.digest.Crypt;
import wl.contract.Validator;
import wl.user.contract.UserRepository;
import wl.user.contract.UserService;
import wl.user.dto.UserRegistrationDto;
import wl.user.dto.UserResponseDto;
import wl.user.entity.UserEntity;

/**
 * Implementation of the {@link UserService} service interface.
 */
public final class UserServiceImpl implements UserService {

  /**
   * User repository.
   */
  private final UserRepository repository;

  /**
   * User validator.
   */
  private final Validator<UserRegistrationDto> validator;

  /**
   * Service constructor.
   *
   * @param validatorImpl  Validator.
   * @param repositoryImpl User repository.
   */
  public UserServiceImpl(
      final Validator<UserRegistrationDto> validatorImpl,
      final UserRepository repositoryImpl
  ) {
    this.validator = validatorImpl;
    this.repository = repositoryImpl;
  }

  @Override
  public Long register(final UserRegistrationDto dto) {
    validator.validate(dto);

    UserEntity user = UserEntity.builder()
        .email(dto.email())
        .phoneNumber(dto.phoneNumber())
        .password(Crypt.crypt(dto.password()))
        .build();

    return repository.create(user);
  }

  @Override
  public Optional<UserResponseDto> getById(final Long id) {
    Optional<UserEntity> user = repository.getById(id);
    return user.map(UserResponseDto::createFrom);
  }
}
