package wl;

import wl.user.contract.UserService;
import wl.user.dto.UserRegistrationDto;
import wl.user.repository.UserRepositoryImpl;
import wl.user.service.UserServiceImpl;
import wl.user.validator.UserValidator;

/**
 * Main.
 */
final class Main {

  private Main() {
  }

  /**
   * Main.
   *
   * @param args List of arguments.
   */
  public static void main(final String[] args) {
    UserRegistrationDto registerDto = new UserRegistrationDto(
        "test@g.com",
        "+13452952367",
        "supper_pwd",
        "supper_pwd"
    );

    UserService userService = new UserServiceImpl(
        new UserValidator(),
        new UserRepositoryImpl()
    );

    Long id = userService.register(registerDto);

    System.out.println(userService.getById(id));
  }
}
