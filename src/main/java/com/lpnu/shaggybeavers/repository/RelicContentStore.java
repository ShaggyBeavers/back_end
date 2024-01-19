package com.lpnu.shaggybeavers.repository;

import com.lpnu.shaggybeavers.model.Relic;
import org.springframework.content.commons.store.ContentStore;
import org.springframework.stereotype.Component;

@Component
public interface RelicContentStore extends ContentStore<Relic, String> {
}
