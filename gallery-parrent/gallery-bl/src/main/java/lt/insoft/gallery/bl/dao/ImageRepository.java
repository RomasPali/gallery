package lt.insoft.gallery.bl.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lt.insoft.gallery.model.InsoftImageEntity;

@Repository
public interface ImageRepository extends JpaRepository<InsoftImageEntity, Long> {}
