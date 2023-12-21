package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.dto.UserDTO;
import com.lpnu.shaggybeavers.factory.UserFactory;
import com.lpnu.shaggybeavers.model.Relic;
import com.lpnu.shaggybeavers.model.User;
import com.lpnu.shaggybeavers.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class UserFacade {

    private final UserFactory userFactory;

    private final UserService userService;

    private final StorageFacade storageFacade;

    public UserDTO getProfile(Long userId) {
        return userFactory.toUserDTO(userService.findById(userId));
    }

    public String uploadFile(MultipartFile multipartFile, Long userId) {
        String url = storageFacade.uploadFile(multipartFile);
        User user = userService.findById(userId);
        user.setImageUrl(url);
        userService.update(user);
        return url;
    }

    public String downloadFile(Long userId) {
        return userService.findById(userId).getImageUrl();
    }

}
