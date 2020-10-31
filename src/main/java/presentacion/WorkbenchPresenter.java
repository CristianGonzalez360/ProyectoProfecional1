package presentacion;

import java.awt.event.ActionEvent;

import presentacion.views.Presenter;
import presentacion.views.WorkbenchView;
import repositories.DaosFactory;
import services.DatabaseSeederServiceImpl;

public class WorkbenchPresenter implements Presenter {

	private WorkbenchView workbenchView = WorkbenchView.getInstance();

	public WorkbenchPresenter() {
		workbenchView.getMntmSeedDb().addActionListener((a) -> onSeedDatabase(a));
	}

	@Override
	public void onInit() {
		workbenchView.open();
	}

	private void onSeedDatabase(ActionEvent a) {
		new DatabaseSeederServiceImpl(DaosFactory.getFactory()).seedDatabase();
	}
}
