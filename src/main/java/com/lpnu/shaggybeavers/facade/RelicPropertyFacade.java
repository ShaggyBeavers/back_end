package com.lpnu.shaggybeavers.facade;

import com.lpnu.shaggybeavers.factory.RelicPropertyFactory;
import com.lpnu.shaggybeavers.model.Relic;
import com.lpnu.shaggybeavers.model.RelicProperty;
import com.lpnu.shaggybeavers.service.RelicPropertyService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RelicPropertyFacade {

    private final RelicPropertyFactory relicPropertyFactory;

    private final RelicPropertyService relicPropertyService;

    private final PropertyFacade propertyFacade;

    @Transactional
    public void updateRelicProperties(List<Long> relicPropertyIds, Relic relic, List<String> propertyValues) {
        for (RelicProperty relicProperty : relic.getRelicProperties()){
            relicPropertyService.delete(relicProperty);
        }

        for (int i = 0; i < relicPropertyIds.size(); i++){
            RelicProperty relicProperty = relicPropertyFactory.toRelicProperty(
                    propertyFacade.findById(relicPropertyIds.get(i)), relic, propertyValues.get(i));
            relicPropertyService.save(relicProperty);
        }
    }
}
