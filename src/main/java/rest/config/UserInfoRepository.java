package rest.config;

import org.springframework.data.jpa.repository.JpaRepository;
import rest.entity.UserInfo;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
    Optional<UserInfo> findByName(String username);

}