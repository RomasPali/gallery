package lt.insoft.gallery.bl.services;

import java.util.List;

import lt.insoft.gallery.bl.dao.jpa.SearchResult;
import lt.insoft.gallery.model.InsoftImageEntity;

public interface ImageEntityService {
    List<InsoftImageEntity> findAll();

    InsoftImageEntity findById(Long id);

    void removeById(Long id);

    InsoftImageEntity save(InsoftImageEntity imageEntity);

    SearchResult findBy(String criteria, String category, Integer offset, Integer limit);
}
