package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.exception.DuplicateException;
import com.lpnu.shaggybeavers.factory.UserCategoryFactory;
import com.lpnu.shaggybeavers.model.User;
import com.lpnu.shaggybeavers.model.UserCategory;
import com.lpnu.shaggybeavers.service.UserCategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserCategoryFacade {

    private final UserCategoryFactory userCategoryFactory;

    private final UserCategoryService userCategoryService;

    private final CategoryFacade categoryFacade;

    @Transactional
    public void create(User user, Set<Long> categoryIds) {
        categoryIds.forEach(categoryId -> {
                    userCategoryService.findByUserIdAndCategoryId(user.getId(), categoryId)
                            .ifPresent(value -> { throw new DuplicateException("UserCategory object already exists"); });

                    UserCategory newUserCategory = new UserCategory();
                    newUserCategory.setUser(user);
                    newUserCategory.setCategory(categoryFacade.findById(categoryId));
                    userCategoryService.save(newUserCategory);
                });
    }

    @Transactional
    public Set<Long> getCategoryIdsByUserId(Long userId) {
        return userCategoryService.findAllByUserId(userId)
                .stream()
                .map(userCategory -> userCategory.getCategory().getId())
                .collect(Collectors.toSet());
    }

    @Transactional
    public void deleteAllByUserId(Long userId) {
        userCategoryService.deleteAllByUserId(userId);
    }

}
