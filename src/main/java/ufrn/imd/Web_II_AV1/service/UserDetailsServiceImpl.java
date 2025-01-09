package ufrn.imd.Web_II_AV1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.stereotype.Service;
import ufrn.imd.Web_II_AV1.exception.UsuarioNaoEncontradoException;
import ufrn.imd.Web_II_AV1.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username)
            .orElseThrow(() -> new UsuarioNaoEncontradoException("User not found"));
    }

}
