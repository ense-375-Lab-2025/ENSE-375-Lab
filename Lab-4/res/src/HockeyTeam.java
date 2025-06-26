/**
* HockeyTeam.java
*
* @author Trevor Douglas
*   <A HREF="mailto:douglatr@uregina.ca"> (douglatr@uregina.ca) </A>
*
* Original code copyright � Mar 15, 2010 Trevor Douglas.  Modifications can be made
* with Author's consent.
* @version Mar 15, 2010
*
**/

/**
 * @author tdouglas
 *
 */
import java.util.ArrayList;


public class HockeyTeam {

    private ArrayList<HockeyPlayer> roster = new ArrayList<HockeyPlayer>();
    public String teamName;
    private static final int ROSTER_SIZE = 20;

    public HockeyTeam(String teamName) {
        this.teamName = teamName;
    }

    /**
     *
     * @return
     * current team roster
     */
    public ArrayList<HockeyPlayer> getRoster() {
        return roster;
    }

    /**
     * Precondition : The roster rules are observed prior to adding a new
     * player.  This function will only check for duplicate players last and first name.
     * @param HockeyPlayer
     * @return true or false
     */
    public boolean addPlayer(HockeyPlayer player) {

        return true;
    }


    /**
     * Precondition : The roster rules are observed prior to deleting a new
     * player.  This function will delete a player if it finds a matching
     * First and Last Name.
     * @param HockeyPlayer
     * @return true or false
     */

    public boolean deletePlayer(HockeyPlayer player) {

        return true;
    }


    /**
     * This method will retrieve a player as long as the first and last names match.
     * @param HockeyPlayer to retrieve
     * @return HockeyPlayer or Null
     */
    public HockeyPlayer getPlayer(HockeyPlayer player) {

        return null;
    }

    /**
     * This method will edit a player as long as the first and last names match.
     * @param HockeyPlayer to edit.  Only edit a players position and rating.
     * @return true or false
     */

    public boolean editPlayer(HockeyPlayer player) {
        return true;
    }
}
