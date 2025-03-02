package wl.user.repository;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import java.util.Optional;
import wl.user.contract.UserRepository;
import wl.user.entity.UserEntity;

/**
 * Repository to maintain {@link UserEntity} entity.
 */
public final class UserRepositoryImpl implements UserRepository {

  /**
   * Storage.
   */
  private final Cache<Long, UserEntity> storage = Caffeine.newBuilder().build();

  @Override
  public Optional<UserEntity> getById(final Long id) {
    return Optional.ofNullable(storage.getIfPresent(id));
  }

  @Override
  public Long create(final UserEntity entity) {
    Long id = entity.getId();
    if (id == null) {
      throw new RuntimeException("Can not create entity without id.");
    }

    if (storage.getIfPresent(id) != null) {
      throw new RuntimeException("User already exists.");
    }

    storage.put(id, entity);

    return id;
  }

  @Override
  public Boolean update(final UserEntity entity) {
    Long id = entity.getId();
    if (id == null) {
      throw new RuntimeException("Can not update not persisted entity.");
    }

    if (storage.getIfPresent(id) == null) {
      return false;
    }

    storage.put(id, entity);

    return true;
  }
}
