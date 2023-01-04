package browsingoperations;

import readinput.User;

public final class BuyTokens extends ActionBuilder  {

    private static BuyTokens instance = null;

    private static ActionBuilder actionBuilder;

    private BuyTokens() { }
    /**
     * Singleton instance creation using the Builder class ActionBuilder
     * @param currentUser
     * @param count
     * @return
     */
    public static BuyTokens getInstance(final User currentUser, final int count) {

        if (instance == null) {
            instance = new BuyTokens();
            actionBuilder = new Builder("buyTokens").currentUser(currentUser).count(count).build();
        } else {
            actionBuilder.setCurrentUser(currentUser);
            actionBuilder.setCount(count);
        }

        return instance;
    }
    @Override
    public void executeAction() {

        instance.setCurrentUser(actionBuilder.getCurrentUser());

        int balance = Integer.parseInt(instance.getCurrentUser().getCredentials().getBalance());

        if (actionBuilder.getCount() <= balance) {
            instance.getCurrentUser().setTokensCount(instance.getCurrentUser().getTokensCount()
                    + actionBuilder.getCount());

            instance.getCurrentUser().getCredentials().setBalance(String.valueOf(balance
                    - actionBuilder.getCount()));
        } else {
            WriteUtils.generalError();
        }
    }

    /**
     * Set the instance to null (because multiple tests are running in parralel)
     */
    public static void setInstance() {
        instance = null;
    }
}
