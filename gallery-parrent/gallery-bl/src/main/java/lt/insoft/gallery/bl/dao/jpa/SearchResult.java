package lt.insoft.gallery.bl.dao.jpa;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lt.insoft.gallery.model.InsoftImageEntity;

@Data
public class SearchResult {

    private List<InsoftImageEntity> results = new ArrayList<>();

    private Long totalSize;


}
