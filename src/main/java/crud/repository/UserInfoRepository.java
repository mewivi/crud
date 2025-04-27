package crud.repository;

import crud.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository <UserInfo, Long> {
}
