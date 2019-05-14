# Final CS4531

## Structure

* **db_stuff** : contains the basic *mongod.conf* file along with a csv containing the names and other information associated with them
* **server_stuff** : contains a basic npm initialized folder with the **package.json** and some packages you might find useful to utilize for the project. Remember ```npm install``` to download the packages before running
* **android_stuff** : simple android project with base code for you to build off of. Contains a MainActivity and the gradle build packages for retrofit, firebase, and google services

## About

This project is to replace the final for Software Engineering. This is not meant to be a vague project with ambiguous requirements, so we will list them below. Important to note that there is no correct way of implementing things outside of authorization (which we will mention below). As such it is up to you to decide how you would like to implement and structure your code. **<u>Just make it work</u>**. I would suggest you think of this project as a hackathon/proof of concept project , don't sweat the small stuff and just get the grand ideas working. It will make your life much easier.

## Requirements

### Points You Need To Receive 100%: ***250***

### Minimal Features: ***150 Points***

The basic features you need to implement are 
* A mechanism for the user to search for a name of interest
* Results showing the popularity of that name for some period of time (start year to end year)
* An ability to compare two names for popularity for some period of time (start year to end year)

### Additional Feature List  

* Authorization : (**60 Points**)
  * Oauth implementation
  * Self handling of username/passwords
* Tagging names : (**25 Points**)
  * Give the user a way of, once a name is searched, to "save" it so that it will appear in comparisons
  * To fully implement this there should be some way to untag names
  * Rating names (additional **40 Points**) - allow users to tag names with a 1 to 5 star rating, but must be done graphically
    * To receive points for this the rating must be done using a graphical mechanism and the results, when shown, must show them sorted by star rating
* Show the results in a more meaningful/graphical manner (**10 to 50** points depending)
  * Show the users a graphic for the chosen names that indicates popularity graphically
* Results sorted (**30 points**)
  * Show the results about names for the period selected but sort the years by overall popularity for the first name (e.g., if the name Rich is being shown for years 1995-1997 then show the data from the year the name Rich was most popular)
* Prefix searching (**20 points**)
  * Allow the users to search for names by a prefix, return all matching names (if multiple, sort by some appropriate mechanism)
  * This mechanism must then include some way for the user to indicate the name(s) of interest
* Wildcard searching (**50 points**)
  * Allow the users to search as in the previous, but using wildcards
  
### The fancier you do something the more points we may allot. The points you see above are minimal points given for just the functionality.
