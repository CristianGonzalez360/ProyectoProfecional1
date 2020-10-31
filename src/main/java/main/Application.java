
package main;

import presentacion.views.ViewsFactory;
import presentacion.views.ViewsFactoryImpl;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import business_logic.ControllersFactory;
import business_logic.ControllersFactoryImpl;
import repositories.DaosFactory;
import repositories.jdbc.DaosFactoryImpl;
import repositories.jdbc.DataSource;
import repositories.jdbc.DataSourceFactory;
import repositories.jdbc.DataSourceFactory.DataSourceType;
import repositories.jdbc.DataSourceFactoryImpl;

public class Application {

	public Application setUpLookAndFeel() {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			try {
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return this;
	}

	public void onInit(DataSource ds) {
		DaosFactory.setFactory(new DaosFactoryImpl(ds));
		ControllersFactory.setFactory(new ControllersFactoryImpl(DaosFactory.getFactory()));
		ViewsFactory.setFactory(new ViewsFactoryImpl(ControllersFactory.getFactory()));
		ViewsFactory.getFactory().makePresenter().onInit();
	}

	public static void main(String[] args) {
		DataSourceFactory.setFactory(new DataSourceFactoryImpl());
		DataSource ds = DataSourceFactory.getFactory().makeDataSource(DataSourceType.IN_MEMORY);

		new Application().setUpLookAndFeel().onInit(ds);
	}
}