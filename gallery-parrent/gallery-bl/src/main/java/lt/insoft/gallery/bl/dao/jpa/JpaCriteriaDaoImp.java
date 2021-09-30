package lt.insoft.gallery.bl.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import lt.insoft.gallery.model.InsoftImageEntity;

@Repository
class JpaCriteriaDaoImp implements JpaCriteriaDao {

    private final EntityManager em;

    public JpaCriteriaDaoImp(EntityManager em) {
        this.em = em;
    }

    @Override
    public SearchResult findBy(String criteria, String category, Integer offset, Integer limit) {
        SearchResult result = new SearchResult();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<InsoftImageEntity> cq = cb.createQuery(InsoftImageEntity.class);
        Root<InsoftImageEntity> e = cq.from(InsoftImageEntity.class);

        CriteriaQuery<Long> cqCout = cb.createQuery(Long.class);
        Root<InsoftImageEntity> eCount = cqCout.from(InsoftImageEntity.class);

        if (criteria != null && !category.contentEquals("all")) {
            cq.select(e).where(cb.equal(e.get(category), criteria));
            cqCout.select(cb.count(eCount)).where(cb.equal(eCount.get(category), criteria));
        } else if (category.contentEquals("all") && (criteria == null || criteria.isEmpty())) {
            cq.select(e);
            cqCout.select(cb.count(eCount));
        } else {
            cq.select(e).where(cb.or(cb.equal(e.get("imageName"), criteria), cb.equal(e.get("category"), criteria)));
            cqCout.select(cb.count(eCount)).where(cb.or(cb.equal(eCount.get("imageName"), criteria), cb.equal(eCount.get("category"), criteria)));
        }

        result.setTotalSize(em.createQuery(cqCout).getSingleResult());
        result.setResults(em.createQuery(cq).setFirstResult(offset).setMaxResults(limit).getResultList());

        return result;
    }

}
