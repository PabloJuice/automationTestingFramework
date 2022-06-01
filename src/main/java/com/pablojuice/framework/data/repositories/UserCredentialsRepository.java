package com.pablojuice.framework.data.repositories;

import com.pablojuice.framework.data.entities.UserCredentials;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCredentialsRepository extends BaseRepository<UserCredentials, String> {
}
