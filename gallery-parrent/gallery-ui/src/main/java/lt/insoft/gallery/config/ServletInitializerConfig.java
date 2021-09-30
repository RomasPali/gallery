package lt.insoft.gallery.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import lt.insoft.gallery.GalleryUIApplication;

//is JAR padaryti WAR
//https://docs.spring.io/spring-boot/docs/2.1.1.RELEASE/reference/html/howto-traditional-deployment.html
//https://stackoverflow.com/questions/48047909/why-it-is-necessary-to-extendspringbootservletinitializer-while-deploying-it-t
@SpringBootConfiguration
public class ServletInitializerConfig extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(GalleryUIApplication.class);
    }
}