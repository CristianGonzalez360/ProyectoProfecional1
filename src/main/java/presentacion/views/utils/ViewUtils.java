package presentacion.views.utils;

import java.awt.Component;
import java.awt.Container;
import java.awt.Window;

import javax.swing.JMenu;

public class ViewUtils {

	public static Component getChildNamed(Component parent, String name) {
		Component component = null;
		if (name.equals(parent.getName())) {
			component = parent;
		} else if (parent instanceof Container) {
			Component[] children = ((Container) parent).getComponents();
			for (int i = 0; i < children.length; ++i) {
				Component child = getChildNamed(children[i], name);
				if (child != null) {
					component = child;
				}
			}
		} else if (parent instanceof Container) {
			Component[] children = (parent instanceof JMenu) ? ((JMenu) parent).getMenuComponents()
					: ((Container) parent).getComponents();
			for (int i = 0; i < children.length; ++i) {
				Component child = getChildNamed(children[i], name);
				if (child != null) {
					component = child;
				}
			}
		}
		return component;
	}

	static int counter;

	public static Component getChildIndexed(Component parent, String klass, int index) {
		counter = 0;
		if (parent instanceof Window) {
			Component[] children = ((Window) parent).getOwnedWindows();
			for (int i = 0; i < children.length; ++i) {
				// Take only active windows
				if (children[i] instanceof Window && !((Window) children[i]).isActive()) {
					continue;
				}
				Component child = getChildIndexedInternal(children[i], klass, index);
				if (child != null) {
					return child;
				}
			}
		}
		return null;
	}

	private static Component getChildIndexedInternal(Component parent, String klass, int index) {
		if (parent.getClass().toString().endsWith(klass)) {
			if (counter == index) {
				return parent;
			}
			++counter;
		}
		if (parent instanceof Container) {
			Component[] children = (parent instanceof JMenu) ? ((JMenu) parent).getMenuComponents()
					: ((Container) parent).getComponents();
			for (int i = 0; i < children.length; ++i) {
				Component child = getChildIndexedInternal(children[i], klass, index);
				if (child != null) {
					return child;
				}
			}
		}
		return null;
	}
}