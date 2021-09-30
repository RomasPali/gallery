package lt.insoft.gallery.component;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.zk.ui.HtmlMacroComponent;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Button;
import org.zkoss.zul.event.PagingEvent;
import org.zkoss.zul.event.ZulEvents;
import org.zkoss.zul.ext.Pageable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InsoftPagingComponent extends HtmlMacroComponent implements Pageable {

    private static final long serialVersionUID = 1L;

    private int pageSize; // how many items per page
    private int totalItems; // how many total items
    private int activePage; // curent active page

    @Command("doPressed")
    public void doPressed(@BindingParam("button") Button pressButton, @BindingParam("index") Integer index) {
        activePage = index;
        Events.postEvent(new PagingEvent(ZulEvents.ON_PAGING, this, index));
    }


    @Override
    public int getPageCount() {

        if (totalItems <= pageSize) {
            return 0;
        }

        return (int) Math.ceil((double) totalItems / (double) pageSize);
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
        BindUtils.postNotifyChange(null, null, this, "pageCount");
    }
}
