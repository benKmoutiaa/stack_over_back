package com.stack.stack_over.Service.Impl;

import com.stack.stack_over.Dto.ChangePasswordUserDto;
import com.stack.stack_over.Dto.RoleDto;
import com.stack.stack_over.Dto.UserDto;
import com.stack.stack_over.Exception.EntityNotFoundException;
import com.stack.stack_over.Exception.ErrorCodes;
import com.stack.stack_over.Exception.InvalidEntityException;
import com.stack.stack_over.Exception.InvalidOperationException;
import com.stack.stack_over.Model.User;
import com.stack.stack_over.Repository.RoleRepository;
import com.stack.stack_over.Repository.UserRepository;
import com.stack.stack_over.Service.IUserService;
import com.stack.stack_over.Validator.UserValidator;
import com.stack.stack_over.Config.PasswordEncoder;

import com.stack.stack_over.utils.Token.ConfirmationToken;
import com.stack.stack_over.utils.Token.ConfirmationTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
@Service
@Transactional
@Slf4j
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    private final ConfirmationTokenService confirmationTokenService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(ConfirmationTokenService confirmationTokenService, PasswordEncoder passwordEncoder) {
        this.confirmationTokenService = confirmationTokenService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User save(UserDto dto) {
        List<String> errors = UserValidator.validate(dto);
        if(!errors.isEmpty())
        {
            log.error("User is not valid {}",dto);
            throw new InvalidEntityException("User is not valid", ErrorCodes.USER_NOT_VALID,errors);
        }
        if (userAlreadyExists(dto.getEmail())){
            throw new InvalidEntityException("another user with the same email already exists",ErrorCodes.USER_ALREADY_EXISTS,

                    Collections.singletonList("another user with the same email already exists in the DB"));
        }
        dto.setPassword(passwordEncoder.bCryptPasswordEncoder().encode(dto.getPassword()));
        dto.setEnabled(false);
        dto.setLocked(false);
       // return UserDto.fromEntity(userRepository.save(UserDto.toEntity(dto)));
        return userRepository.save(UserDto.toEntity(dto));

    }

    private Boolean userAlreadyExists(String email){
        Optional<User> user = userRepository.findUserByEmail(email);
        return user.isPresent();
    }
    @Override
    public UserDto findById(Long id) {
        if(id==null)
        {
            log.error("User ID is null");
            return null;
        }
        Optional<User> user= userRepository.findById(id);
        return Optional.of( UserDto.fromEntity(user.get())).orElseThrow(()-> new EntityNotFoundException("no user found with id :"+ id ,ErrorCodes.USER_NOT_FOUND));

    }

    @Override
    public RoleDto saveRole(RoleDto role) {
        return RoleDto.fromEntity(roleRepository.save(RoleDto.toEntity(role)));
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
       /* Role role= roleRepository.findByName(roleName);
        User user=userRepository.findUserByEmail(username);
        user.getRoles().add(role);*/

    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(UserDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if(id==null)
        {
            log.error("User ID is null");
            return ;
        }
        userRepository.deleteById(id);
    }

    @Override
    public UserDto findByEmail(String email) {

        return userRepository.findUserByEmail(email)
                .map(UserDto::fromEntity)
                .orElseThrow(()->new EntityNotFoundException(
                        "No user with email ="+email+" was found in the DB",
                        ErrorCodes.USER_NOT_FOUND
                ));
    }

    @Override
    public UserDto changerPassword(ChangePasswordUserDto dto) {
        validate(dto);
        Optional<User> userOptional = userRepository.findById(dto.getId());
        if (userOptional.isEmpty()) {
            log.warn("No user was found with ID " + dto.getId());
            throw new EntityNotFoundException("No user was found with ID " + dto.getId(), ErrorCodes.USER_NOT_FOUND);
        }

        User user = userOptional.get();
        user.setPassword(passwordEncoder.bCryptPasswordEncoder().encode(dto.getPassword()));

        return UserDto.fromEntity(
                userRepository.save(user)
        );

    }

    @Override
    public String signUpUser(UserDto dto) {
        boolean userExists = userRepository.findUserByEmail(dto.getEmail()).isPresent();
        if (userExists) {
            //UserDto userPrevious = UserDto.fromEntity(userRepository.findUserByEmail(dto.getEmail()).get());
            User userPrevious =userRepository.findUserByEmail(dto.getEmail()).get();
            Boolean isEnabled = userPrevious.getEnabled();
            if (!isEnabled) {
                String token = UUID.randomUUID().toString();
                //A method to save user and token in this class
                saveConfirmationToken(userPrevious, token);
                return token;
            }
            throw new IllegalStateException(String.format("User with email %s already exists!", dto.getEmail()));
        }
        String encodedPassword = passwordEncoder.bCryptPasswordEncoder().encode(dto.getPassword());
        dto.setPassword(encodedPassword);

        User user= save(dto);


        String token = UUID.randomUUID().toString();

        saveConfirmationToken(user, token);

        return token;
    }


    private void saveConfirmationToken(User user, String token) {

        ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),user);
        confirmationTokenService.saveConfirmationToken(confirmationToken);

    }

    @Override
    public int enableAppUser(String email) {
        return userRepository.enableAppUser(email);
    }

    private void validate(ChangePasswordUserDto dto) {
        if (dto == null) {
            log.warn("Cannot change password with NULL object");
            throw new InvalidOperationException("No information has been provided to be able to change the password.",
                    ErrorCodes.UTILISATEUR_CHANGE_PASSWORD_OBJECT_NOT_VALID);
        }
        if (dto.getId()== null) {
            log.warn("Unable to change password with NULL ID");
            throw new InvalidOperationException("User ID null:: Unable to change password",
                    ErrorCodes.UTILISATEUR_CHANGE_PASSWORD_OBJECT_NOT_VALID);
        }
        if (!StringUtils.hasLength(dto.getPassword()) || !StringUtils.hasLength(dto.getValidatePassword())) {
            log.warn("Cannot change password with NULL password");
            throw new InvalidOperationException("User password null:: Unable to change password",
                    ErrorCodes.UTILISATEUR_CHANGE_PASSWORD_OBJECT_NOT_VALID);
        }
        if (!dto.getPassword().equals(dto.getValidatePassword())) {
            log.warn("Unable to change password with two different passwords");
            throw new InvalidOperationException("Illegal user passwords:: Unable to change password",
                    ErrorCodes.UTILISATEUR_CHANGE_PASSWORD_OBJECT_NOT_VALID);
        }
    }


}
