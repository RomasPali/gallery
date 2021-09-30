package lt.insoft.gallery.component;

import java.io.IOException;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.image.AImage;
import org.zkoss.image.Image;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.HtmlMacroComponent;

import lombok.Getter;
import lombok.Setter;
import lt.insoft.gallery.bl.view.InsoftImageView;

@Setter
@Getter
public class InsoftImageComponent extends HtmlMacroComponent {

    private static final long serialVersionUID = 1L;

    private InsoftImageView view;

    @Init
    public void init() {
    }

    @Command("doView")
    public void doView() {
        if (view != null) {
            Executions.getCurrent().sendRedirect("/edit.zul?id=" + view.getId());
        }
    }

    public Image getThumbnailImage() throws IOException {
        return new AImage("image.png", view.getThumbnailImage());
    }
}
