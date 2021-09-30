package lt.insoft.gallery.viewmodels;

import java.io.IOException;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.bind.annotation.QueryParam;
import org.zkoss.image.AImage;
import org.zkoss.image.Image;
import org.zkoss.util.media.Media;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import lombok.Getter;
import lombok.Setter;
import lt.insoft.gallery.bl.view.InsoftImageView;
import lt.insoft.gallery.bl.view.services.ImageViewService;

public class EditViewModel {

    @WireVariable private ImageViewService imageViewService;

    @Getter @Setter private Image uploadedImage;
    @Getter private InsoftImageView view;

    @Init
    public void init(@QueryParam("id") Long id) {
  

        view = imageViewService.findById(id);

        if (view == null) {
            view = new InsoftImageView();
        } else if (view.getImage() != null) {
            try {
                uploadedImage = new AImage(view.getImageName(), view.getImage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Command("doSave")
    public void doSave() {
        view = imageViewService.save(view);
    }

    @Command("doDelete")
    public void doDelete() {
        Messagebox.show("Are you sure to delete?", "Confirm Dialog", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION, new EventListener<Event>() {
            public void onEvent(Event evt) {
                if (evt.getName().equals("onOK")) {
                    imageViewService.removeById(view.getId());
                    Executions.getCurrent().sendRedirect("/index.zul");
                }
            }
        });

    }

    @Command("doBack")
    public void doBack() {
        Executions.getCurrent().sendRedirect("/index.zul");
    }

    @NotifyChange({"uploadedImage", "imageName", "showSave"})
    @Command("doUploadImage")
    public void duUploadImage(@BindingParam("media") Media media) {
        if (media != null && media instanceof AImage) {
            uploadedImage = (AImage) media;
            view.setImage(uploadedImage.getByteData());
            view.setImageName(uploadedImage.getName());
            view.setImageSize(Long.valueOf(uploadedImage.getByteData().length));
        }
    }

    public String getImageName() {
        return view.getImageName();
    }

    public boolean getShowDelete() {
        return view.getId() != null;
    }

    public boolean getShowSave() {
        return uploadedImage != null;
    }
}
