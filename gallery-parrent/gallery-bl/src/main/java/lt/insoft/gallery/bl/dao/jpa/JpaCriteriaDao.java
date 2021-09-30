package lt.insoft.gallery.bl.dao.jpa;

public interface JpaCriteriaDao {

    SearchResult findBy(String criteria, String category, Integer offset, Integer limit);
}
