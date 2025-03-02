package wl.user.contract;

import wl.contract.Retrieval;
import wl.user.dto.UserRegistrationDto;
import wl.user.dto.UserResponseDto;

/**
 * Interface to register users.
 */
public interface UserService extends
    UserRegistrar<UserRegistrationDto>,
    Retrieval<UserResponseDto, Long> {

}
