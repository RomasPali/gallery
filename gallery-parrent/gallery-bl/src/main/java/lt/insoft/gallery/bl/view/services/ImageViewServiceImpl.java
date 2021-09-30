package lt.insoft.gallery.bl.view.services;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import lt.insoft.gallery.bl.dao.jpa.SearchResult;
import lt.insoft.gallery.bl.services.ImageEntityService;
import lt.insoft.gallery.bl.view.InsoftImageTransformer;
import lt.insoft.gallery.bl.view.InsoftImageView;
import lt.insoft.gallery.bl.view.SearchResultView;
import lt.insoft.gallery.model.InsoftImageEntity;

@Transactional
@Service("imageViewService")
public class ImageViewServiceImpl implements ImageViewService {

    private final InsoftImageTransformer insoftImageTransformer;
    private final ImageEntityService imageService;

    public ImageViewServiceImpl(InsoftImageTransformer insoftImageTransformer, ImageEntityService imageService) {

        this.insoftImageTransformer = insoftImageTransformer;
        this.imageService = imageService;
    }

    @Override
    public InsoftImageView findById(Long id) {
        InsoftImageEntity entity = imageService.findById(id);
        return insoftImageTransformer.transformWithImage(entity);
    }

    @Override
    public void removeById(Long id) {
        imageService.removeById(id);
    }

    @Override
    public InsoftImageView save(InsoftImageView view) {

        InsoftImageEntity entity = insoftImageTransformer.transform(view);
        InsoftImageEntity stored = imageService.save(entity);

        return insoftImageTransformer.transformWithImage(stored);

    }

    @Override
    public SearchResultView findThumbnailsBy(String criteria, String category, Integer offset, Integer limit) {
        SearchResult result = imageService.findBy(criteria, category, offset, limit);
        return insoftImageTransformer.transformWithThumbnailImage(result);
    }

}
