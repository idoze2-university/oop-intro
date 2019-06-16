package ui;

/**
 * A task is something that needs to happen, or something that we can run() and
 * return a value.
 *
 * @param <T> the generic type of the task.
 */
public interface Task<T> {

  /**
   * @return the result returned by running the task.
   */
  T run();
}