package lt.insoft.gallery.bl.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import lt.insoft.gallery.bl.dao.ImageRepository;
import lt.insoft.gallery.bl.dao.jpa.JpaCriteriaDao;
import lt.insoft.gallery.bl.dao.jpa.SearchResult;
import lt.insoft.gallery.model.InsoftImageEntity;

@Transactional
@Service("imageService")
class ImageEntityServiceImpl implements ImageEntityService {

    private JpaCriteriaDao criteriaDao;
    private final ImageRepository imageRepository;

    public ImageEntityServiceImpl(JpaCriteriaDao criteriaDao, ImageRepository imageRepository) {
        this.criteriaDao = criteriaDao;
        this.imageRepository = imageRepository;
    }

    @Override
    public List<InsoftImageEntity> findAll() {
        return imageRepository.findAll();
    }

    @Override
    public InsoftImageEntity findById(Long id) {
        return imageRepository.findById(id).orElse(null);
    }

    @Override
    public void removeById(Long id) {
        imageRepository.deleteById(id);
    }

    @Override
    public InsoftImageEntity save(InsoftImageEntity imageEntity) {
        return imageRepository.save(imageEntity);
    }
    
    @Override
    public SearchResult findBy(String criteria, String category, Integer offset, Integer limit) {
        return criteriaDao.findBy(criteria, category, offset, limit);
    }
}
