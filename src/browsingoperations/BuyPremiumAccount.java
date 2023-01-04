package browsingoperations;

import readinput.User;

public final class BuyPremiumAccount extends ActionBuilder  {

    private static final int PREMIUMACCOUNTPRICE = 10;
    private static BuyPremiumAccount instance = null;

    private static ActionBuilder actionBuilder;

    private BuyPremiumAccount() { }

    /**
     * Singleton instance creation using the Builder class ActionBuilder
     * @param currentUser
     * @return
     */
    public static BuyPremiumAccount getInstance(final User currentUser) {

        if (instance == null) {
            instance = new BuyPremiumAccount();
            actionBuilder = new Builder("buyPremiumAccount").currentUser(currentUser).build();
        } else {
            actionBuilder.setCurrentUser(currentUser);
        }

        return instance;
    }
    @Override
    public void executeAction() {

        instance.setCurrentUser(actionBuilder.getCurrentUser());

        int tokens = instance.getCurrentUser().getTokensCount();

        if (tokens >= PREMIUMACCOUNTPRICE && instance.getCurrentUser().getCredentials().
                getAccountType().compareTo("standard") == 0) {
            instance.getCurrentUser().setTokensCount(tokens - PREMIUMACCOUNTPRICE);
            instance.getCurrentUser().getCredentials().setAccountType("premium");
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
