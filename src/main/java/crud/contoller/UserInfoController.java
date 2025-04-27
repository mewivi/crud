package crud.contoller;

import crud.dto.UserInfoDTO;
import crud.entity.User;
import crud.entity.UserInfo;
import crud.service.UserInfoService;
import crud.util.UserErrorResponse;
import crud.util.UserNotCreatedException;
import crud.util.UserNotFoundException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

//Контроллер CRUD модель для работы с детальной информацией о пользователе
@RestController
@RequestMapping("/api/info")
public class UserInfoController {

    private final UserInfoService userInfoService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserInfoController(UserInfoService userInfoService, ModelMapper modelMapper) {
        this.userInfoService = userInfoService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public List<UserInfoDTO> getAll() {
        return userInfoService.findAll().stream().map(this::convertToUserInfoDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserInfoDTO getById(@PathVariable("id") Long id) {
        return convertToUserInfoDTO(userInfoService.findById(id));
    }

    @PostMapping("{id}")
    public ResponseEntity<HttpStatus> save(@PathVariable("id") Long id,
                                           @RequestBody @Valid UserInfoDTO userInfoDTO,
                                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            List<FieldError> listErrors = bindingResult.getFieldErrors();
            for (FieldError error : listErrors) {
                errorMessage.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";");
            }
            throw new UserNotCreatedException(errorMessage.toString());
        }
        userInfoService.save(id, convertToUserInfo(userInfoDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody UserInfoDTO userInfoDTO) {
        return userInfoService.update(id, convertToUserInfo(userInfoDTO));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userInfoService.delete(id);
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(UserNotFoundException e) {
        UserErrorResponse response = new UserErrorResponse(
                "User with this id wasn't found!",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(UserNotCreatedException e) {
        UserErrorResponse response = new UserErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private UserInfo convertToUserInfo(@Valid UserInfoDTO userInfoDTO) {
        return modelMapper.map(userInfoDTO, UserInfo.class);
    }

    private UserInfoDTO convertToUserInfoDTO(@Valid UserInfo userInfo) {
        return modelMapper.map(userInfo, UserInfoDTO.class);
    }
}