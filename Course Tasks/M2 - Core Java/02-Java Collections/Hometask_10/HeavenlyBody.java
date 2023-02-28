package hometask_10;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public final class HeavenlyBody {
	
    private final String name;
    private final double orbitalPeriod;
    private final Set<HeavenlyBody> satellites;

    public HeavenlyBody(String name, double orbitalPeriod) {
        this.name = name;
        this.orbitalPeriod = orbitalPeriod;
        this.satellites = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public double getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public boolean addMoon(HeavenlyBody moon) {
        return this.satellites.add(moon);
    }

    public Set<HeavenlyBody> getSatellites() {
        return new HashSet<>(this.satellites);
    }

	@Override
	public int hashCode() {
		System.out.println("hashcode is called");
		return Objects.hash(name, orbitalPeriod, satellites);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HeavenlyBody other = (HeavenlyBody) obj;
		return Objects.equals(name, other.name)
				&& Double.doubleToLongBits(orbitalPeriod) == Double.doubleToLongBits(other.orbitalPeriod)
				&& Objects.equals(satellites, other.satellites);
	}
    
    
}
