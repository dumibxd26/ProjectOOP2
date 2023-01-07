     ___________________________________
    |                                   |
    |     Bogdan Dumitrescu 322CAa      |
    |        Project Part 2             |
    |          POO(2022)                |
    |                                   |
    |___________________________________|

    I left the readme for the first part of the project
    so you can see the progress.

    The design patterns I used:
    - Factory
    - Builder
    - Singleton
    - Observer

   As promised, I implemented the factory design pattern to help the builder
   create the actions and adding them in a hashmap, so they can easily be accessed.
   
   Basically what I did, is that in the main function, when you try to execute an action,
   you simply call it's execute function(which defines its behavior) from the hashmap.
   It is something like: actionMap.get(feature).execute();
   
    The FACTORY importance is given by eliminating boilerplate code,treating cases and
    not showing the client the execution logic. Also, it creates a more generic code.
    
    The BUILDER is used because some actions need some parametres and others don't.
    For example, Login/Register change the currentUser, notUserBannedList, filteredList, etc.
    But Search only affect one list, the filtered list. Other actions don't affect anything.

    Those variables are changed after every action, if they are used by specific that specific action.
    
    The SINGLETON is used for the output database, and for a wrapper class(global variable).
    The wrapper is for PageUtils, since another builder would have been an overkill there,
        I created a wrapper for the variables that can be change by the action of changing pages
    (for logout the currentPage changes, current user becomes null, history page becomes null, etc,
    (see details changes the currentPage, and filtered list). And all of those page changes 
    change the current page.
        Those changed variables are then retreived in the Run function from the wrapper.
    
    The OBSERVER is used for the notification system and it works like this:
    The subject is the movie list and the observers are the users.
    I know, there could be an implementation where the observer is each genre,
    and the observers are the user, it would probably make more sense and it would
    have been a more natural observer... But imagine how many classes should have been
    created, how many genres I should have looked for in the tests and the overhead...
    So I thought that this implementation is better:
    When a user is added(it registers), the registerObserver method is called, and the
    list of observers(user) is updated. When a movie is added/deleted, the modifyState
    method is called, and it does its job notifying the observers about the changes
    if needed.

    I also used Comparator.compare(I've noticed you gave extra points for that :P)
    
    I didn't use more than 4 design patterns since I thought that even 4 was too much.
    I could have added a Filter pattern but the context was not good enough for it.

    
 Most of the implementation is already explained in the JavaDocs, but I will briefly explain 
 the most important parts:

     The main function only initialises the output database and ObjectMapper functionality.
    The Run class is the core of the program, this is where de data is initialised and 
    distributed to the other classes. 
        If the action type is "change page", we handle the page changing
    and we modify the lists, current user, history of pages and the current page if needed.
        If the action type is "back", and the action can be done, we obtain the last frame(last page's lists, etc)
    and we execute actions from there. If we get to "see details" or "movies" after backing, we print the
    desired message.
        If the action type is "on page", we execute the actions from the hashmap as explained.
        If the action type is "database", we execute the adding/removing movies as explained.

    Honestly, the rest of the code is either self explanatory or already explained in the javadocs.

I hope this design is good enough, I struggled a lot to come out with this solution.
I think it's too much to ask for a better idea, or the best that you found yet, so I would love to hear
what could have been done better in my homework. Thank you!

        
   
