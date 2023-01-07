     ___________________________________
    |                                   |
    |     Bogdan Dumitrescu 322CAa      |
    |        Project Part 1             |
    |          POO(2022)                |
    |                                   |
    |___________________________________|


Probably you after seeing my code:

![alt text](https://img.bleacherreport.net/cms/media/image/83/55/60/8e/e4a3/4512/ae7f/5b5796a78bce/crop_exact_fc9nl1oacaakvkm.jpeg?h=378&q=90&w=567)

    The first part of the project aims to implement the backend of a Netflix like website. The user has access to multiple pages with different possible actions available. 

    The design patterns I used are: Builder(for Actions classes builder) and Singleton for each action and for the database(which saves a lot of memory and function parametres size).

    For the next project, I am planning to improve the mess I wrote here(because, apparently, I didn't do enough research and planning for this project and things didn't work the way I wanted mid project). I will use 
    a factory with the Builder pattern and probably strategy or observer.

    I didn't implement a special factory class in advance for the users, since it was not that useful for this project. But, if needed, I will implement it in the next project. 

    I will not go through each function, because most of them are self explanatory, but I will explain the most important actions.


    The main class only initialises intput and output, sends them to the Run utility class and then it writes the output to a file.

    The Run function is the brain of the project, it iterates through the actions and calls the corresponding methods. It also writes output for the see details and movies pages.

    Must note that I always keep track of two Movie lists, notUserBannedMovies(depending on the country) and filteredList(depending on the previous filters), because some commands
    require the filteredList(Search, Rate, Like, Watch, purchase etc) and some require the notUserBannedMovies(movies etc).

    When the user logs in / register, after checking if its credentials are valid, the notUserBanned list is initialised and the action is printed.

    Then, the current page becomes homepage authenticated and the user can acces the movies(which prints the notUserBannedMovies list), Upgrades and logout.

    Changing the page to a not valid next one, results in printing "the general error".

    If the user decides to search for a movie / filter the movies, the filteredList is updated for the next commands, or restored to notUserBanned if it goes back to movies page.

    If the user wants to see the details for a specific movie, the filtered list becomes that specific movie. It is restored to notUserBannedMovies if the user goes back to movies page.

    For executing the watch, rate, like actions, previous actions have to be executed first, otherwise it is not possible and the general error is printed.


    Problems I encountered:

    At first, I thought that my idea was amazing, then I realised that Singletons had to be reinitialised for each tests, which kinda ruined my idea and defeats(a little) the purpose of them. That's because I didn't know how tests were run before test 6..

    The Singleton for the database is very useful tho.

    The builder is not THAT useful in my case, because I didn't know that I could use a factory builder pattern which will be an amazing idea for the next project(and I will explain why in a second). My Action classes extend the Builder class which does not make that much sense, and more non sense operations are made there to keep track of the instances of the singletons and the builder's.

    I also use two data structures written in ActionUtils and PageUtils. One to store the next actions that can be executed at some point and one that stores the next pages that can be accessed and actions for that specific page.

    Note: Pages are only strings, and actions are classes!

    What will be solved by the Factory Builder in the next project:

    The main function will not have cases, it will only execute a specific action based on the name of the currentaAction variable.

    Code will make more sense, probably I will remove the singletons from the Actions classes and the ActionUtils data structure will efectively hold the actionClass in it(Not null as it does now).


    What I learned:
    
    That I should do more planning before writing code.
    That I should do more research on design patterns.
    That I should not have skipped the courses.

    What I wish you would do / have done:

    Teach design patterns at least before the deadline(all design pattenrs besides singleton were thought the day before the deadline).

    Recommend a good book for design patterns(I also asked for this in the previous project and I got no answer).