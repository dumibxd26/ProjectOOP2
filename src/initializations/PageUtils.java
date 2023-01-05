package initializations;

import java.util.HashMap;
import java.util.HashSet;

public final class PageUtils {

    private PageUtils() { }

    /**
     * Utility method to create not authenticated page possible next actions and pages
     * @return
     */
    public static PageInfo createHomepageNotAuth() {

        HashSet<String> homepageNotAuthSetNext = new HashSet<String>();

        homepageNotAuthSetNext.add("login");
        homepageNotAuthSetNext.add("register");

        HashSet<String> homepageNotAuthActionstNext = new HashSet<String>();

        PageInfo loginPageInfo = new PageInfo(homepageNotAuthSetNext, homepageNotAuthActionstNext);

        return loginPageInfo;
    }
    /**
     * Utility method to create authenticated page possible next actions and pages
     * @return
     */
    public static PageInfo createHomepageAuth() {

        HashSet<String> homepageNotAuthSetNext = new HashSet<String>();

        homepageNotAuthSetNext.add("movies");
        homepageNotAuthSetNext.add("upgrades");
        homepageNotAuthSetNext.add("logout");

        HashSet<String> homepageNotAuthActionstNext = new HashSet<String>();

        PageInfo loginPageInfo = new PageInfo(homepageNotAuthSetNext, homepageNotAuthActionstNext);

        return loginPageInfo;
    }
    /**
     * Utility method to create movies page possible next actions and pages
     * @return
     */
    public static PageInfo createMoviesPage() {

        HashSet<String> moviesPageSetNext = new HashSet<String>();

        moviesPageSetNext.add("homepage autentificat");
        moviesPageSetNext.add("see details");
        moviesPageSetNext.add("logout");

        moviesPageSetNext.add("movies");

        HashSet<String> moviesPageActionstNext = new HashSet<String>();

        moviesPageActionstNext.add("search");
        moviesPageActionstNext.add("filter");

        PageInfo loginPageInfo = new PageInfo(moviesPageSetNext, moviesPageActionstNext);

        return loginPageInfo;
    }
    /**
     * Utility method to create upgrades page possible next actions and pages
     * @return
     */
    public static PageInfo createUpgradesPage() {

        HashSet<String> upgradesPageSetNext = new HashSet<String>();

        upgradesPageSetNext.add("homepage autentificat");
        upgradesPageSetNext.add("movies");
        upgradesPageSetNext.add("logout");

        upgradesPageSetNext.add("upgrades");

        HashSet<String> upgradesPageActionstNext = new HashSet<String>();

        upgradesPageActionstNext.add("buy premium account");
        upgradesPageActionstNext.add("buy tokens");

        PageInfo loginPageInfo = new PageInfo(upgradesPageSetNext, upgradesPageActionstNext);

        return loginPageInfo;
    }
    /**
     * Utility method to create see details page possible next actions and pages
     * @return
     */
    public static PageInfo createSeeDetails() {

        HashSet<String> seeDetailsSetNext = new HashSet<String>();

        seeDetailsSetNext.add("homepage autentificat");
        seeDetailsSetNext.add("movies");
        seeDetailsSetNext.add("upgrades");
        seeDetailsSetNext.add("logout");

        seeDetailsSetNext.add("see details");

        HashSet<String> upgradesPageSetNext = new HashSet<String>();

        upgradesPageSetNext.add("purchase");
        upgradesPageSetNext.add("watch");
        upgradesPageSetNext.add("like");
        upgradesPageSetNext.add("rate");

        PageInfo seeDetailsInfo = new PageInfo(seeDetailsSetNext, upgradesPageSetNext);

        return seeDetailsInfo;
    }
    /**
     * Utility method to create login page possible next actions and pages
     * @return
     */
    public static PageInfo createLogin() {

        HashSet<String> loginSetNext = new HashSet<String>();

        loginSetNext.add("register");

        loginSetNext.add("login");

        HashSet<String> loginActionsNext = new HashSet<String>();

        loginActionsNext.add("login");

        PageInfo loginPageInfo = new PageInfo(loginSetNext, loginActionsNext);

        return loginPageInfo;
    }
    /**
     * Utility method to create register page possible next actions and pages
     * @return
     */
    public static PageInfo createRegister() {

        HashSet<String> registerSetNext = new HashSet<String>();

        registerSetNext.add("login");

        registerSetNext.add("register");

        HashSet<String> registerActionsNext = new HashSet<String>();

        registerActionsNext.add("register");

        PageInfo registerPageInfo = new PageInfo(registerSetNext, registerActionsNext);

        return registerPageInfo;
    }

    /**
     * Utility method to create logout page possible next actions and pages
     * @return
     */
    public static PageInfo createLogout() {

        // Irelevant, just logout
        HashSet<String> logoutSetNext = new HashSet<String>();

        HashSet<String> logoutActionsNext = new HashSet<String>();

        PageInfo logoutPageInfo = new PageInfo(logoutSetNext, logoutActionsNext);

        return logoutPageInfo;
    }

    /**
     * Method to create the hashmap which link the name of a page
     * to possible actions and pages to visit from it
     * @return
     */
    public static HashMap<String, PageInfo> createPages() {

        HashMap<String, PageInfo> pages = new HashMap<String, PageInfo>();

        pages.put("homepage neautentificat", createHomepageNotAuth());
        pages.put("homepage autentificat", createHomepageAuth());
        pages.put("movies", createMoviesPage());
        pages.put("upgrades", createUpgradesPage());
        pages.put("see details", createSeeDetails());
        pages.put("login", createLogin());
        pages.put("register", createRegister());
        pages.put("logout", createLogout());

        return pages;
    }
}
