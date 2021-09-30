package lt.insoft.gallery.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "BYTE_STORAGE_ENTITY")
public class ByteStorageEntity {

    @Id @GeneratedValue private Long id;

    @Lob @Column(name = "BYTES") private byte[] bytes;

    public ByteStorageEntity(byte[] bytes) {
        this.bytes = bytes;
    }
}
