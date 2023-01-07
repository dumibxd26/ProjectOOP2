package initializations;

import browsingoperations.ActionExec;

public class ActionInfo {

    private final ActionExec action;

    public ActionInfo(final ActionExec action) {
        this.action = action;
    }

    /**
     * Getter for the next actions
     * @return
     */
    public ActionExec getAction() {
        return action;
    }

}
