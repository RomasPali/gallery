package lt.insoft.gallery.bl.services;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;

import lt.insoft.gallery.bl.dao.jpa.SearchResult;
import lt.insoft.gallery.model.ByteStorageEntity;
import lt.insoft.gallery.model.InsoftImageEntity;

@ActiveProfiles("test")
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = Replace.ANY)
@SpringBootTest
class ImageServiceImplTest {

    @Autowired private ImageEntityService imageService;

    @Test
    public void testLazyLoadingWithoutTransaction() {

        InsoftImageEntity entity = new InsoftImageEntity("Insoft");
        entity.setImage(new ByteStorageEntity("Insoft-Insoft".getBytes()));

        InsoftImageEntity storedEntity = imageService.save(entity);
        InsoftImageEntity foundedEntity = imageService.findById(storedEntity.getId());

        assertThrows(LazyInitializationException.class, () -> {
            foundedEntity.getImage().getBytes();
        });

    }

    @Transactional
    @Test
    public void testLazyLoadingWithTransaction() {

        InsoftImageEntity entity = new InsoftImageEntity("Insoft");
        entity.setImage(new ByteStorageEntity("Insoft-Insoft".getBytes()));

        InsoftImageEntity storedEntity = imageService.save(entity);
        InsoftImageEntity foundedEntity = imageService.findById(storedEntity.getId());

        assertDoesNotThrow(() -> {
            foundedEntity.getImage().getBytes();
        });

        byte[] bytes = foundedEntity.getImage().getBytes();

        String loadedString = new String(bytes);

        assertTrue(loadedString.equals("Insoft-Insoft"));
    }

    // 2
    @Test
    public void testJpaCriteriaDaoFindAllCrietriaNull() {

        List<InsoftImageEntity> entities = Arrays.asList(new InsoftImageEntity("0"), new InsoftImageEntity("1"), new InsoftImageEntity("2"), new InsoftImageEntity("3"), new InsoftImageEntity("4"));

        for (InsoftImageEntity e : entities) {
            imageService.save(e);
        }

        // activePage 0
        int activePage = 0;
        int pageSize = 2;
        int offset = pageSize * activePage;
        SearchResult results = imageService.findBy(null, "all", offset, pageSize);
        List<InsoftImageEntity> entites = results.getResults();
        Long totalCount = results.getTotalSize();

        assertTrue(entites.size() == 2);
        assertTrue(totalCount == 5);
        assertTrue(entites.get(0).getImageName().equals("0"));
        assertTrue(entites.get(1).getImageName().equals("1"));

        // activePage 1
        activePage = 1;
        offset = pageSize * activePage;
        results = imageService.findBy(null, "all", offset, pageSize);
        entites = results.getResults();
        totalCount = results.getTotalSize();

        assertTrue(entites.size() == 2);
        assertTrue(totalCount == 5);
        assertTrue(entites.get(0).getImageName().equals("2"));
        assertTrue(entites.get(1).getImageName().equals("3"));

        // activePage 2
        activePage = 2;
        offset = pageSize * activePage;
        results = imageService.findBy(null, "all", offset, pageSize);
        entites = results.getResults();
        totalCount = results.getTotalSize();

        assertTrue(entites.size() == 1);
        assertTrue(totalCount == 5);
        assertTrue(entites.get(0).getImageName().equals("4"));
    }

    // 1
    @Test
    void testJpaCriteriaSearchNotAll() {

        List<InsoftImageEntity> entities = Arrays.asList(new InsoftImageEntity("0", "0"), new InsoftImageEntity("1", "1"), new InsoftImageEntity("2", "2"), new InsoftImageEntity("3", "3"),
                new InsoftImageEntity("4", "4"));

        for (InsoftImageEntity e : entities) {
            imageService.save(e);
        }

        String category = "imageName";
        String criteria = "1";

        int pageSize = 2;
        int activePage = 0;
        int offset = pageSize * activePage;

        SearchResult results = imageService.findBy(criteria, category, offset, pageSize);
        List<InsoftImageEntity> founded = results.getResults();
        Long totalSize = results.getTotalSize();

        assertNotNull(results);
        assertNotNull(founded);
        assertTrue(founded.size() == 1);
        assertNotNull(totalSize);

        String n = founded.get(0).getImageName();

        assertTrue(n.equals("1"));
        // criteria random
        category = "imageName";
        criteria = "asas";

        results = imageService.findBy(criteria, category, offset, pageSize);
        founded = results.getResults();

        assertNotNull(results);
        assertNotNull(founded);

        assertTrue(founded.size() == 0);

        // category = "category"
        category = "category";
        criteria = "1";
        results = imageService.findBy(criteria, category, offset, pageSize);
        founded = results.getResults();
        totalSize = results.getTotalSize();

        assertNotNull(results);
        assertNotNull(founded);
        assertTrue(founded.size() == 1);
        assertNotNull(totalSize);
        n = founded.get(0).getCategory();

        assertTrue(n.equals("1"));
    }

    // 3
    @Test
    void testJpaCriteriaSearchAllCriteriaNotNull() {
        List<InsoftImageEntity> entities = Arrays.asList(new InsoftImageEntity("0", "0"), new InsoftImageEntity("1", "1"), new InsoftImageEntity("2", "2"), new InsoftImageEntity("3", "3"),
                new InsoftImageEntity("4", "4"));

        for (InsoftImageEntity e : entities) {
            imageService.save(e);
        }

        String criteria = "0";
        String categoryImageName = "imageName";
        String categoryCategory = "category";

        int activePage = 0;
        int pageSize = 2;
        int offset = pageSize * activePage;

        SearchResult resultName = imageService.findBy(criteria, categoryImageName, offset, pageSize);

        SearchResult resultCategory = imageService.findBy(criteria, categoryCategory, offset, pageSize);

        List<InsoftImageEntity> resultNames = resultName.getResults();
        List<InsoftImageEntity> resulCategorys = resultCategory.getResults();

        assertTrue(resultNames.size() > 0);

        assertTrue(resultNames.size() == resulCategorys.size());

    }
}
