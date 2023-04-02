package todoer.item.events;

import org.springframework.context.ApplicationEvent;
import todoer.item.Item;

public class NewItemEvent extends ApplicationEvent {

    private Item item;

    public NewItemEvent(final Object source, final Item item) {
        super(source);
        this.item = item;
    }

    public Item getItem() {
        return this.item;
    }

}
