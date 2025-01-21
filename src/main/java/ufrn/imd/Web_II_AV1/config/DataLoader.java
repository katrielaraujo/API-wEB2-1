package ufrn.imd.Web_II_AV1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ufrn.imd.Web_II_AV1.model.User;
import ufrn.imd.Web_II_AV1.model.UserRole;
import ufrn.imd.Web_II_AV1.repository.UserRepository;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        createUserIfNotExists("felipeTeacher@gmail.com", "imd@2024.2", UserRole.ADMIN);
        createUserIfNotExists("katrielStudent@gmail.com", "imd@aluno", UserRole.USER);
    }

    private void createUserIfNotExists(String login, String password, UserRole role){
        if(userRepository.findByLogin(login) == null){
            String encryptedPassword = new BCryptPasswordEncoder().encode(password);
            User user = new User(login, encryptedPassword,role);
            userRepository.save(user);
        }
    }
}
