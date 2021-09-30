package lt.insoft.gallery.bl.dao.hql;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import lt.insoft.gallery.model.InsoftImageEntity;

@Transactional
@Service
class HQLSearchImpl implements HQLSearch {

    private final EntityManager entityManager;

    public HQLSearchImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<InsoftImageEntity> findAllBy(String criteria, String category) {

        if (!category.contentEquals("all")) {
            String hql = "select e from ImageEntity e where " + category + " =" + ":criteria";
            return entityManager.createQuery(hql, InsoftImageEntity.class).setParameter("criteria", criteria).getResultList();

        } else if (category.contentEquals("all") && (criteria == null || criteria.isEmpty())) {
            // return all values
            String hql = "select e from ImageEntity e";
            return entityManager.createQuery(hql, InsoftImageEntity.class).getResultList();
        } else {
            String hql = "select e from ImageEntity e where imageName = :imageCriteria or category = :categoryCriteria";
            return entityManager.createQuery(hql, InsoftImageEntity.class).setParameter("imageCriteria", criteria).setParameter("categoryCriteria", criteria).getResultList();
        }
    }
}
