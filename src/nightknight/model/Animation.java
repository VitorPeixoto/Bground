package nightknight.model;

/**
 *
 * @author Vitor
 */
public class Animation {
    private float min, max, pace;
    private int times;
    private float value;
    private AnimationType type;
    private boolean forever;

    public Animation(float min, float max, float pace, int times, AnimationType type) {
        this.value = this.min = min;
        this.max = max;
        this.pace = pace;
        this.times = times;
        if(times == 0) forever = true;
        this.type = type;
    }
    
    public void update() {
        if(forever || --times >= 0) {
            if((this.value+pace) > max || (this.value+pace) < min) {
                if(type == AnimationType.REPEAT) {
                    this.value = (pace < 0 ? max : min);
                } else if(type == AnimationType.INVERT) {
                    this.pace *= -1;
                }
            }
            value += pace;
        }
    }

    public float getValue() {
        return value;
    }
    
    public enum AnimationType {
        REPEAT,
        INVERT
    }
}
