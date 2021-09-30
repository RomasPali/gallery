package lt.insoft.gallery.viewmodels.validators;

import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.validator.AbstractValidator;

public class SearchValidator extends AbstractValidator {

    @Override
    public void validate(ValidationContext ctx) {
        
        /*
        String category = (String) ctx.getProperties("selectCategory")[0].getValue();
        String criteria = (String) ctx.getProperties("selectText")[0].getValue();

        if (category == null || category.isEmpty()) {
            addInvalidMessage(ctx, "fkey1", "EMPTY PLEASE CHOOSE");
            return;
        }

        if (!category.equals("all") && (criteria == null || criteria.isEmpty())) {
            addInvalidMessage(ctx, "fkey1", "EMPTY PLEASE CHOOSE");
        }
        */
    }
}
