public class Bed {
    public int degree;
    public int maxDegree;
    public boolean hasBed;
    public boolean adjustableBed;
    public boolean bedOpen;


    public Bed() {
        this.degree = 0;
        this.maxDegree = 0;
        this.hasBed = false;
        this.adjustableBed = false;
        this.bedOpen = false;
    }

    public Bed(int degree, int maxDegree, boolean hasBed, boolean adjustableBed, boolean bedOpen) {
        this.degree = degree;
        this.maxDegree = maxDegree;
        this.hasBed = hasBed;
        this.adjustableBed = adjustableBed;
        this.bedOpen = bedOpen;
    }

    public void setDegree(int newDegree) {
        if (adjustableBed) {
            degree = newDegree;
        }
    }

    public int getDegree() {
        return degree;
    }

    public void increment() {
        if (adjustableBed) {
            if (degree < maxDegree) {
                degree++;
                open();
            }
        }
    }

    public void decrement() {
        if (adjustableBed) {
            if (degree > 0) {
                degree--;
                open();
            }
            if (degree == 0) {
                close();
            }
        }
    }

    public void open() {
        if (hasBed) {
            bedOpen = true;
        }
    }

    public void close() {
        if (hasBed) {
            bedOpen = false;
        }
    }
}
