package crud.service;

import crud.entity.User;
import crud.entity.UserInfo;
import crud.repository.UserInfoRepository;
import crud.repository.UserRepository;
import crud.util.UserNotCreatedException;
import crud.util.UserNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserInfoService {

    private final UserInfoRepository userInfoRepository;
    private final UserRepository userRepository;

    public UserInfoService(UserInfoRepository userInfoRepository, UserRepository userRepository) {
        this.userInfoRepository = userInfoRepository;
        this.userRepository = userRepository;
    }

    public List<UserInfo> findAll() {
        return userInfoRepository.findAll();
    }

    public UserInfo findById(Long id) {
        Optional<UserInfo> foundUserInfo = userInfoRepository.findById(id);
        return foundUserInfo.orElseThrow(UserNotFoundException::new);
    }

    @Transactional
    public User save(Long id, UserInfo userInfo) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);

        if (user.getUserInfo() != null) {
            throw new UserNotCreatedException("Информация о пользователе уже существует");
        } else {
            userInfo.setUser(user);
            user.setUserInfo(userInfo);
            return userRepository.save(user);
        }
    }

    @Transactional
    public User update(Long id, UserInfo userInfo) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);

        if (user.getUserInfo() != null) {
            UserInfo existingInfo = user.getUserInfo();
            existingInfo.setSurname(userInfo.getSurname());
            existingInfo.setName(userInfo.getName());
            existingInfo.setPatronymic(userInfo.getPatronymic());
            existingInfo.setBirthday(userInfo.getBirthday());
            return userRepository.save(user);
        }
        else {
            userInfo.setUser(user);
            user.setUserInfo(userInfo);
            return userRepository.save(user);
        }
    }

    @Transactional
    public void delete(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        if (user.getUserInfo() != null) {
            UserInfo userInfo = user.getUserInfo();
            user.setUserInfo(null);
            userInfoRepository.delete(userInfo);
        }
    }
}