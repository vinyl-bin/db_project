package db_project.db_project.service;

import db_project.db_project.domain.User;
import db_project.db_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static java.util.function.Predicate.isEqual;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * user등록
     */
    @Transactional
    public Long join(User user) {
        validateDuplicateUser(user);  //중복 user 검증
        userRepository.save(user);
        return user.getUser_id();
    }

    @Transactional
    public void createUser(User user) {
        userRepository.save(user);
    }

    private void validateDuplicateUser(User user) {
        List<User> findUsers = userRepository.findByName(user.getName());
        if (!findUsers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 이름입니다.");
        }
    }

    /**
     * user password 일치 여부
     */
    public Long checkPassword(String name, String password) {
        validateCorrectPassword(name, password);
        List<User> users = userRepository.findByName(name);
        User user = users.get(0);
        return user.getUser_id();
    }

    private void validateCorrectPassword(String name, String password) {
        List<User> findNameUsers = userRepository.findByName(name);
        List<User> findPasswordUsers = userRepository.findByPassword(password);

        boolean isEqual = Objects.equals(findNameUsers, findPasswordUsers);
        if (!isEqual) {
            throw new IllegalStateException("user와 password의 정보가 다릅니다.");
        }

    }

    public User login(String userName, String password) {
        User user = userRepository.findOneByUserName(userName);
        if(!user.getPassword().equals(password)){
            System.out.println("Wrong password");
            user = null;
        }
        return user;
    }


    /**
     * 전체 user 조회
     */
    public List<User> findUsers() {
        return userRepository.findAll();
    }


    /**
     * user 이름으로 조회
     */
    public List<User> findByName(String name) {
        return userRepository.findByName(name);
    }


}
