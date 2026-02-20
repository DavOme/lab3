import java.util.ArrayList;
import java.util.List;

public class Workshop<T extends Vehicle> {

    private final int capacity;
    private final List<T> vehicles = new ArrayList<>();

    public Workshop(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("Capacity must be bigger than 0");
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getSize() {
        return vehicles.size();
    }

    public void store(T vehicle) {
        if (vehicle == null) throw new IllegalArgumentException("Vehicle cannot be null");
        if (vehicles.size() >= capacity) throw new IllegalStateException("Workshop is full");
        vehicles.add(vehicle);
    }

    public T unStore() {
        if (vehicles.isEmpty()) throw new IllegalStateException("Workshop is loadEmpty");
        return vehicles.removeLast();
    }

    public T unStore(int index) {
        if (vehicles.isEmpty()) throw new IllegalStateException("Workshop is loadEmpty");
        if (index < 0 || index >= vehicles.size()) throw new IndexOutOfBoundsException("Bad index");
        return vehicles.remove(index);
    }
}

