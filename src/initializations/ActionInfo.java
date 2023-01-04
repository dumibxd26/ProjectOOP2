package initializations;

import java.util.HashSet;
import browsingoperations.ActionBuilder;

public class ActionInfo {

    private final HashSet<String> nextActions;
    private final ActionBuilder action;

    public ActionInfo(final HashSet<String> nextActions,
                      final ActionBuilder action) {
        this.nextActions = nextActions;
        this.action = action;
    }

    /**
     * Getter for the next actions
     * @return
     */
    public ActionBuilder getAction() {
        return action;
    }

    /**
     * Getter for the next actions
     * @return
     */
    public HashSet<String> getNextActions() {
        return nextActions;
    }

}
