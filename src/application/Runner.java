package application;

/**
 * The {@link Runner} interface provides a {@code run(String[] args)} method
 * that allows passing {@code String[] args} arguments, which is not provided
 * by the {@link Runnable} interface of the Java run-time environment.
 * 
 * @version <code style=color:green>{@value application.package_info#Version}</code>
 * @author <code style=color:blue>{@value application.package_info#Author}</code>
 */
public interface Runner {

    /**
     * Method invoked on an instance that implements the {@link Runner} interface
     * with passing command line arguments.
     * @param args commands passed from the command line
     */
    void run(String[] args);
}