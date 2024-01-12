package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.factory.RelicCategoryFactory;
import com.lpnu.shaggybeavers.model.Relic;
import com.lpnu.shaggybeavers.model.RelicCategory;
import com.lpnu.shaggybeavers.service.RelicCategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

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
            RelicCategory relicCategory = relicCategoryFactory.toRelicCategory(categoryFacade.findById(categoryId), relic);
            relicCategoryService.save(relicCategory);
        }
    }
}
