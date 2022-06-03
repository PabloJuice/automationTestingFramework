package com.pablojuice.framework.data.repositories;

import com.pablojuice.framework.data.entities.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCredentialsRepository extends JpaRepository<UserCredentials, String> {
}
