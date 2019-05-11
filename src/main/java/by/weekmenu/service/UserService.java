package by.weekmenu.service;

import by.weekmenu.DTO.AccountDTO;
import by.weekmenu.DTO.UserDTO;
import by.weekmenu.domain.Account;
import by.weekmenu.domain.User;
import by.weekmenu.repos.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    private ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);

    }

    public User createUser(UserDTO userDTO){
       return userRepo.save(convertToEntity(userDTO));
    }




    private UserDetails convertToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, UserDetails.class);
    }

    private UserDTO convertToDto(User user) {
        return modelMapper.map(user, UserDTO.class);
    }



}
