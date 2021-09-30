package lt.insoft.gallery.bl.view;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class SearchResultView {

    private List<InsoftImageView> results = new ArrayList<>();
    private Long totalSize;

}
