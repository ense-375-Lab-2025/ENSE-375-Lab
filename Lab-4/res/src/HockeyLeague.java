/**
* HockeyLeague.java
*
* @author Trevor Douglas
*   <A HREF="mailto:douglatr@uregina.ca"> (douglatr@uregina.ca) </A>
*
* Original code copyright � Mar 15, 2010 Trevor Douglas.  Modifications can be made
* with Author's consent.
* @version Mar 15, 2010
*
**/

import java.util.ArrayList;
import java.util.Random;

/**
 * @author tdouglas
 *
 */
public class HockeyLeague {

	    ArrayList<HockeyTeam> teams = new ArrayList<HockeyTeam>();
	    private static final int ROSTER_SIZE = 20;

	    /**
	     * HockeyLeague
	     * This constructor reads in the 6 files and fills in the teams array
	     * @pre 
	     *
	     */
	    public HockeyLeague() {

	    }


	    /**
	     * playGame
	     * To play a game, the teams must have their rosters set properly.
	     * This will simulate a game...it's not a boring algorithm, so be prepared
	     * It does take into account the goalie and the players ratings so...
	     * Just finds the first goalie...so could replace that with HockeyTeam.getCurrentGoalie()
	     * method that would be better...but for the purposes of this lab, this works.
	     * @param visitingTeam
	     * @param homeTeam
	     * @return String of the winning team or error message
	     */
	    public String playGame(String visitingTeam, String homeTeam) {

	        //Wanted to make this not so boring....it's not that confusing

	        HockeyTeam visiting = null;
	        HockeyTeam home = null;

	        int homeScore = 0;
	        int visitingScore = 0;

		    if (checkTeams( visitingTeam,  homeTeam) == false)
		    	return "Unplayable Game Rosters are wrong";

	        //First get the objects for the teams
	        for (int i = 0; i<teams.size(); i++) {
	            if (teams.get(i).teamName.equals(visitingTeam)) {
	                visiting = teams.get(i);
	                break;
	            }
	        }

	        for (int i = 0; i<teams.size(); i++) {
	            if (teams.get(i).teamName.equals(homeTeam)) {
	                home = teams.get(i);
	                break;
	            }
	        }

	        Random random = new Random();
	        //My implementation for play game is to first randomize the amount of
	        //scoring opportunities that will occur in this game...
	        int numTotalGoals = getRandomInteger(5,15,random);


	        //Now pick a random player from a random team who will be the shooter
	        for (int i = 0; i < numTotalGoals ; i++) {

	            //Now pick a random player from a random team
	            int randomTeam = getRandomInteger(0,1,random);
	            String playerPos = "G";
	            int shooterRating = 0;
	            do {
	                if (randomTeam == 0) {
	                    //get player rating from visiting team
	                    int randomPlayer = getRandomInteger(0,visiting.getRoster().size()-1,random);

	                    playerPos = visiting.getRoster().get(randomPlayer).getPosition();
	                    shooterRating = visiting.getRoster().get(randomPlayer).getRating();

	                } else {
	                    //get player rating from home team
	                    int randomPlayer = getRandomInteger(0,home.getRoster().size()-1,random);

	                    playerPos = home.getRoster().get(randomPlayer).getPosition();
	                    shooterRating = home.getRoster().get(randomPlayer).getRating();
	                }
	            } while (playerPos.equals("G"));

	            //Get the opposing teams goalie
	            int goalieRating = 0;
	            if (randomTeam == 0) {
	                //get home goalie rating
	                for (int j = 0; i<home.getRoster().size(); j++) {
	                    if (home.getRoster().get(j).getPosition().equals("G")) {
	                        goalieRating = home.getRoster().get(j).getRating();
	                        break;
	                    }
	                }
	            } else {
	                //get visiting goalie rating
	                for (int j = 0; i<visiting.getRoster().size(); j++) {
	                    if (visiting.getRoster().get(j).getPosition().equals("G")) {
	                        goalieRating = visiting.getRoster().get(j).getRating();
	                        break;
	                    }
	                }
	            }


	            //Now match them up...
	            float ratio = (float)goalieRating/(float)shooterRating;
	            int intRatio = (int) (ratio*100);

	            //Worst case is technically 10000 so...goalie will likely save
	            //So limit to 200, (if goalie is twice the rating of the player,
	            //the goalie will always save)
	            int goalOrNot = getRandomInteger(1,200, random);
	            if (goalOrNot < intRatio) {
	                //This means they scored...otherwise, no goal
	 //               System.out.println("GOAL!");
	                if(randomTeam == 0)
	                    visitingScore++;
	                else
	                    homeScore++;
	            }
	        }

	        //Score:
	        System.out.println("At end of regulation...");
	        System.out.println(visitingTeam + " = " + visitingScore);
	        System.out.println(homeTeam + " = " + homeScore);

	        int winningTeam;
	        if (homeScore > visitingScore) {
	            winningTeam = 1;
	        } else if (homeScore < visitingScore) {
	            winningTeam = 0;
	        } else {
	            System.out.println("Overtime needed");
	            winningTeam = getRandomInteger(0,1,random);
	        }

	        if (winningTeam == 0) {
	            System.out.println(visiting.teamName+ " wins!");
	            return visiting.teamName;
	        }
	        else {
	            System.out.println(home.teamName+ " wins!");
	            return home.teamName;
	        }

	    }


        /**
         * This function will verify that the team roster is correct.
         * Their must be 4 Centers, 4 RW, 4 LW, 6D and 2G
         */
	    private boolean checkTeams(String visitingTeam, String homeTeam)
	    {

			

	        return true;
	    }


	    //Helper function that really helped me with the game simulation
	    private static int getRandomInteger(int aStart, int aEnd, Random aRandom){
	        if ( aStart > aEnd ) {
	            throw new IllegalArgumentException("Start cannot exceed End.");
	        } else {

	            //get the range, casting to long to avoid overflow problems
	            long range = (long)aEnd - (long)aStart + 1;
	            // compute a fraction of the range, 0 <= frac < range
	            long fraction = (long)(range * aRandom.nextDouble());
	            int randomNumber =  (int)(fraction + aStart);
	            return randomNumber;
	        }
	    }
}
