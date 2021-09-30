package lt.insoft.gallery.bl.view.services;

import lt.insoft.gallery.bl.view.InsoftImageView;
import lt.insoft.gallery.bl.view.SearchResultView;

public interface ImageViewService {

    InsoftImageView findById(Long id);

    void removeById(Long id);

    InsoftImageView save(InsoftImageView imageEntityView);

    SearchResultView findThumbnailsBy(String criteria, String category, Integer offset, Integer limit);
}
