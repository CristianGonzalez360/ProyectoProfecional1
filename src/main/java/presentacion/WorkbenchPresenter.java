package presentacion;

import java.awt.event.ActionEvent;

import business_logic.DatabaseSeederServiceImpl;
import presentacion.views.Presenter;
import presentacion.views.WorkbenchView;
import repositories.DaosFactory;

public class WorkbenchPresenter implements Presenter {

	private WorkbenchView workbenchView = WorkbenchView.getInstance();

	public WorkbenchPresenter() {
		workbenchView.getMntmSeedDb().addActionListener((a)->onSeedDatabase(a));
	}
	
	@Override
	public void onInit() {
		workbenchView.open();
	}
	
	private void onSeedDatabase(ActionEvent a) {
		new DatabaseSeederServiceImpl(DaosFactory.getFactory()).seedDatabase();
	}
}
