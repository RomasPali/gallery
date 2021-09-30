package lt.insoft.gallery.viewmodels;

import java.util.Collections;
import java.util.List;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.Validator;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import lombok.Getter;
import lombok.Setter;
import lt.insoft.gallery.bl.view.InsoftImageView;
import lt.insoft.gallery.bl.view.SearchResultView;
import lt.insoft.gallery.bl.view.services.ImageViewService;
import lt.insoft.gallery.viewmodels.validators.SearchValidator;

public class IndexViewModel {

    @WireVariable private ImageViewService imageViewService;

    private static final int DEFAULT_ACTIVE_PAGE = 0;
    private static final int DEFAULT_PAGE_SIZE = 2;

    private SearchResultView searchResult;

    @Setter @Getter private String selectedText;
    @Setter @Getter private String selectedCategory = "all";

    private String searchText;
    private String searchCategory;

    @Getter private int pageSize;
    private int activePage;

    @Init
    public void init() {
        activePage = DEFAULT_ACTIVE_PAGE;
        pageSize = DEFAULT_PAGE_SIZE;

        searchText = selectedText;
        searchCategory = selectedCategory;

        searchResult = imageViewService.findThumbnailsBy(searchText, searchCategory, getOffset(), pageSize);
    }

    @NotifyChange({"totalItems", "imageEntities"})
    @Command("doSearch")
    public void doSearch() {
        searchText = selectedText;
        searchCategory = selectedCategory;

        searchResult = imageViewService.findThumbnailsBy(searchText, searchCategory, getOffset(), pageSize);
        setActivePage(DEFAULT_ACTIVE_PAGE);
    }

    @Command("doCreate")
    public void doCreate() {
        Executions.getCurrent().sendRedirect("/edit.zul?id=-1");
    }

    public Validator getFormValidator() {
        return new SearchValidator();
    }

    public List<InsoftImageView> getImageEntities() {

        if (searchResult != null) {
            return searchResult.getResults();
        }

        return Collections.emptyList();
    }

    public void setActivePage(int pg) {
        this.activePage = pg;
        searchResult = imageViewService.findThumbnailsBy(searchText, searchCategory, getOffset(), pageSize);
        BindUtils.postNotifyChange(null, null, this, "imageEntities");
    }


    public Long getTotalItems() {
        return searchResult.getTotalSize();
    }

    private int getOffset() {
        return Math.max(activePage - 1, 0) * pageSize;
    }
    
}