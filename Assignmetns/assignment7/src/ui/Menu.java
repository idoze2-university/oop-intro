package ui;

import game.animation.Animation;

/**
 * The Menu provides a protocol for a selectible menu which runs tasks according
 * to selection.
 *
 * @param <T> Generic type to be used for the menu.
 */
public interface Menu<T> extends Animation {
  /**
   * add Menu entry.
   *
   * @param key       key that selects the entry.
   * @param message   label for the entry.
   * @param returnVal value to be returned when entry is selected.
   */
  void addSelection(String key, String message, T returnVal);

  /**
   * @return the selected entry.
   */
  T getStatus();

  /**
   * Adds a sub menu to the menu.
   *
   * @param key     key that selects the submenu.
   * @param message label for the submenu.
   * @param subMenu Menu to be returned when submenu is selected.
   */
  void addSubMenu(String key, String message, Menu<T> subMenu);

}