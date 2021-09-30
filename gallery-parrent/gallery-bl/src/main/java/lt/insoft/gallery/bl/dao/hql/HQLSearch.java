package lt.insoft.gallery.bl.dao.hql;

import java.util.List;

import lt.insoft.gallery.model.InsoftImageEntity;

public interface HQLSearch {
    List<InsoftImageEntity> findAllBy(String criteria, String category);
}
