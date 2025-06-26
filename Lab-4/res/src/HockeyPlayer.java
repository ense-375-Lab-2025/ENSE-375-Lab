/**
* HockeyPlayer.java
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
public class HockeyPlayer {

    private String firstName;
    private String lastName;
    private String position;
    private int rating;


    public HockeyPlayer(String position, String firstName,
            String lastName, int rating) {
        this.position = position;
        this.firstName = firstName;
        this.lastName = lastName;
        this.rating = rating;
    }

    public HockeyPlayer() {
        this.position = null;
        this.firstName = null;
        this.lastName = null;
        this.rating = 0;
    }

    public String getFirstName() {
        return firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public String getPosition() {
        return position;
    }


    public int getRating() {
        return rating;
    }
    public void setPosition(String position) {
        this.position = position;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }


    public String toString() {
        return (position+" "+lastName+", "+firstName+" "+rating);
    }


}
