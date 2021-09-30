package lt.insoft.gallery.bl.view;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class InsoftImageView {
    private Long id;
    private Long imageSize;
    private byte[] image;
    private byte[] thumbnailImage;
    private String imageName;
    private String description;
    private String category;
}
