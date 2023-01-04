package initializations;

import java.util.HashSet;
import browsingoperations.ActionBuilder;
import browsingoperations.ActionExec;

public class ActionInfo {

    private final HashSet<String> nextActions;
    private final ActionExec action;

    public ActionInfo(final HashSet<String> nextActions,
                      final ActionExec action) {
        this.nextActions = nextActions;
        this.action = action;
    }

    /**
     * Getter for the next actions
     * @return
     */
    public ActionExec getAction() {
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
