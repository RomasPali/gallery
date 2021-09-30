package lt.insoft.gallery.bl.services.image;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;

@Service
class ImageTransformationServiceImpl implements ImageTransformationService {
    private static final String IMAGE_FORMAT_JPG = "jpg";

    @Override
    public byte[] getThumbnailBytes(byte[] imageBytes) {
        if (imageBytes == null) {
            return null;
        }

        try {
            InputStream is = new ByteArrayInputStream(imageBytes);
            BufferedImage bufferedImage = ImageIO.read(is);
            BufferedImage bufferedThumbnailImage = Scalr.resize(bufferedImage, 200);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedThumbnailImage, IMAGE_FORMAT_JPG, baos);
            return baos.toByteArray();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }
}
