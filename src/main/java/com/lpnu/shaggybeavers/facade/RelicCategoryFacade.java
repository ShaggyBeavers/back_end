package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.factory.RelicCategoryFactory;
import com.lpnu.shaggybeavers.model.Relic;
import com.lpnu.shaggybeavers.model.RelicCategory;
import com.lpnu.shaggybeavers.service.RelicCategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RelicCategoryFacade {

    private final RelicCategoryFactory relicCategoryFactory;

    private final RelicCategoryService relicCategoryService;

    private final CategoryFacade categoryFacade;

    @Transactional
    public void updateRelicCategories(List<Long> relicCategoryIds, Relic relic) {
        for (RelicCategory relicCategory : relic.getRelicCategories()){
            relicCategoryService.delete(relicCategory);
        }

        for (Long categoryId : relicCategoryIds){
            relicCategoryService.create(relic.getId(), categoryId);
        }
    }

    @Transactional
    public Set<Long> getCategoryIdsByRelicId(Long relicId) {
        return relicCategoryService.findAllByRelicId(relicId)
                .stream()
                .map(relicCategory -> relicCategory.getCategory().getId())
                .collect(Collectors.toSet());
    }

}
