package presentacion.views.utils;

import java.util.List;

public interface ReportView<T> {

	void setData(List<T> entities);

	void open();
}
