
package side;

/**
 * @author ojjranss
 * @version 2 Mar 2022
 *
 */
public class Cheker {

    public static int rand(int min, int max) {
        double n = (max-min)*Math.random() + min;
        return (int)Math.round(n);
    }

}