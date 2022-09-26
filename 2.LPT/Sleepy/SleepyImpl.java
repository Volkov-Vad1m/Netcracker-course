public class SleepyImpl implements Sleepy{
    private boolean awake = false;

    @Override
    public void sleep() {
        awake = false;
    }

    @Override
    public void wakeUp() {
        awake = true;
    }

    @Override
    public void ask() {
        if(awake) {
            System.out.println("BOO");
        } else {
            System.out.println("zzz...");
        }
    }
}
