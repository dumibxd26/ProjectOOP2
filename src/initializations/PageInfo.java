package initializations;

import java.util.HashSet;

public class PageInfo {

    private final HashSet<String> nextPages;
    private final HashSet<String> nextActions;

    public PageInfo(final HashSet<String> nextPages,
                    final HashSet<String> nextActions) {
        this.nextPages = nextPages;
        this.nextActions = nextActions;
    }

    /**
     * Getter for next pages
     * @return
     */
    public HashSet<String> getNextPages() {
        return nextPages;
    }

    /**
     * Getter for next actions
     * @return
     */
    public HashSet<String> getNextActions() {
        return nextActions;
    }
}
