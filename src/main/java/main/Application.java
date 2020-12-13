
package main;

import presentacion.AdminViewFactory;
import presentacion.ViewsFactory;
import presentacion.ViewsFactoryImpl;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import business_logic.ControllersFactory;
import business_logic.ControllersFactoryImpl;
import repositories.DaosFactory;
import repositories.jdbc.DaosFactoryImpl;
import repositories.jdbc.utils.DataSource;
import repositories.jdbc.utils.DataSourceFactory;
import repositories.jdbc.utils.DataSourceFactoryImpl;
import services.DatabaseSeederServiceImpl;
import repositories.jdbc.utils.DataSourceFactory.DataSourceType;

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
		if (ds.getConnection() != null) {
			DaosFactory.setFactory(new DaosFactoryImpl(ds));
			new DatabaseSeederServiceImpl(DaosFactory.getFactory()).seedDatabase();
			ControllersFactory.setFactory(new ControllersFactoryImpl(DaosFactory.getFactory()));
			ViewsFactory.setFactory(new ViewsFactoryImpl(ControllersFactory.getFactory()));
		} else {
			ViewsFactory.setFactory(new AdminViewFactory());
		}

		ViewsFactory.getFactory().makePresenter().onInit();
	}

	public static void main(String[] args) {
		DataSourceFactory.setFactory(new DataSourceFactoryImpl());
		DataSource ds = DataSourceFactory.getFactory().makeDataSource(DataSourceType.IN_MEMORY);
		new Application().setUpLookAndFeel().onInit(ds);
	}
}