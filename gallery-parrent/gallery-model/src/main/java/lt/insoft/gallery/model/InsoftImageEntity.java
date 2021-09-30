package lt.insoft.gallery.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Entity
@Table(name = "INSOFT_IMAGE_ENTITY")
public class InsoftImageEntity {

    @Id @GeneratedValue private Long id;

    @Column(name = "IMAGE_SIZE") private Long imageSize;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY) @JoinColumn(name = "image_id") private ByteStorageEntity image;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY) @JoinColumn(name = "thumbnail_id") private ByteStorageEntity thumbnailImage;

    @Column(name = "IMAGE_NAME") private String imageName;

    @Column(name = "DESCRIPTION") private String description;

    @Column(name = "CATEGORY") private String category;

    public InsoftImageEntity(String imageName) {
        this.imageName = imageName;
    }

    public InsoftImageEntity(String imageName, String category) {
        this.imageName = imageName;
        this.category = category;
    }

}
