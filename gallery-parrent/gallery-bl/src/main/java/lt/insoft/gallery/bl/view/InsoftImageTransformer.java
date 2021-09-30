package lt.insoft.gallery.bl.view;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import lt.insoft.gallery.bl.dao.jpa.SearchResult;
import lt.insoft.gallery.bl.services.image.ImageTransformationService;
import lt.insoft.gallery.model.ByteStorageEntity;
import lt.insoft.gallery.model.InsoftImageEntity;

@Transactional
@Component
public class InsoftImageTransformer {

    private final ImageTransformationService imageTransformationService;

    public InsoftImageTransformer(ImageTransformationService imageTransformationService) {
        this.imageTransformationService = imageTransformationService;
    }

    public InsoftImageEntity transform(InsoftImageView view) {
        if (view == null) {
            return null;
        }

        InsoftImageEntity entity = new InsoftImageEntity();

        entity.setId(view.getId());
        entity.setImageSize(view.getImageSize());
        entity.setImage(new ByteStorageEntity(view.getImage()));
        entity.setThumbnailImage(new ByteStorageEntity(imageTransformationService.getThumbnailBytes(view.getImage())));
        entity.setImageName(view.getImageName());
        entity.setDescription(view.getDescription());
        entity.setCategory(view.getCategory());

        return entity;
    }

    public InsoftImageView transformWithImage(InsoftImageEntity entity) {

        if (entity == null) {
            return null;
        }
        
        InsoftImageView view = transform(entity);
        view.setImage(entity.getImage().getBytes());

        return view;
    }
    
    public InsoftImageView transformWithThumbnailImage(InsoftImageEntity entity) {

        if (entity == null) {
            return null;
        }
        
        InsoftImageView view = transform(entity);
        view.setThumbnailImage(entity.getThumbnailImage().getBytes());
        
        return view;
    }
    
    public List<InsoftImageView> transformWithThumbnailImage(List<InsoftImageEntity> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptyList();
        }

        return entities.stream().map((InsoftImageEntity e) -> {
            return transformWithThumbnailImage(e);
        }).collect(Collectors.toList());
    }

    public SearchResultView transformWithThumbnailImage(SearchResult searchResult) {
        if (searchResult == null) {
            return null;
        }
        SearchResultView result = new SearchResultView();
        result.setResults(transformWithThumbnailImage(searchResult.getResults()));
        result.setTotalSize(searchResult.getTotalSize());
        return result;

    }

    private InsoftImageView transform(InsoftImageEntity entity) {

        if (entity == null) {
            return null;
        }
        InsoftImageView view = new InsoftImageView();

        view.setId(entity.getId());
        view.setImageSize(entity.getImageSize());
        view.setImageName(entity.getImageName());
        view.setDescription(entity.getDescription());
        view.setCategory(entity.getCategory());

        return view;
    }
}