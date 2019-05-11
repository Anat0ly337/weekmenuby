package by.weekmenu.service;

import by.weekmenu.DTO.AccountDTO;
import by.weekmenu.DTO.UserDTO;
import by.weekmenu.domain.Account;
import by.weekmenu.domain.User;
import by.weekmenu.repos.AccountRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountService {
    @Autowired
    private AccountRepo accountRepo;

    private  ModelMapper modelMapper;

    public Account createAccount(AccountDTO accountDTO){
       return accountRepo.save(convertToEntity(accountDTO));
    }


    private Account convertToEntity(AccountDTO accountDTO) {
        return modelMapper.map(accountDTO, Account.class);
    }

    private AccountDTO convertToDto(Account account) {
        return modelMapper.map(account, AccountDTO.class);
    }
}
