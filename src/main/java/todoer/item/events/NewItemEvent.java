package todoer.item.events;

import org.springframework.context.ApplicationEvent;
import todoer.item.Item;


/**
 * The event that launches once a new item is created
 */
public class NewItemEvent extends ApplicationEvent {

    private Item item;

    /**
     * @param source the source of the new event item which is the Item class
     * @param item the new item created
     * Creates the event
     */
    public NewItemEvent(final Object source, final Item item) {
        super(source);
        this.item = item;
    }

    public Item getItem() {
        return this.item;
    }

}
