package airportsimulation.airport;

/**
 *
 * @author dobos
 */
public interface AirportBuilder {

    public boolean hasNext();

    public Airport getNext();
}