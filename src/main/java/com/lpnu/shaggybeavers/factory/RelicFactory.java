package com.lpnu.shaggybeavers.factory;

import com.lpnu.shaggybeavers.dto.FavoriteRelicDTO;
import com.lpnu.shaggybeavers.dto.RelicCatalogDTO;
import com.lpnu.shaggybeavers.dto.RelicDTO;
import com.lpnu.shaggybeavers.dto.RelicCreateEditDTO;
import com.lpnu.shaggybeavers.mapper.EntityMapper;
import com.lpnu.shaggybeavers.model.Relic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RelicFactory {

    private final EntityMapper entityMapper;

    public RelicDTO toRelicDTO(Relic relic) {
        return entityMapper.toRelicDTO(relic);
    }

    public RelicCatalogDTO toRelicCatalogDTO(Relic relic) { return entityMapper.toRelicCatalogDTO(relic); }

    public List<RelicDTO> toRelicDTOList(List<Relic> relics) {
        return entityMapper.toRelicDTOList(relics);
    }


    public Relic toRelic(RelicCreateEditDTO relicCreateEditDTO) {
        return entityMapper.toRelic(relicCreateEditDTO);
    }

    public Relic update(Relic relic, RelicCreateEditDTO relicCreateEditDTO) {
        return entityMapper.update(relic, relicCreateEditDTO);
    }

    public FavoriteRelicDTO toFavoriteRelicDTO(Relic relic) {
        return entityMapper.toFavoriteRelicDTO(relic);
    }


}
