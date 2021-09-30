package lt.insoft.gallery.bl.services.image;

public interface ImageTransformationService {
    byte[] getThumbnailBytes(byte[] imageBytes);
}
