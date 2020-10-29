package business_logic;

import java.util.LinkedList;
import java.util.List;

import dto.PaisDTO;

public class DatabaseGraph {
	
	private List<PaisDTO> paisesList;

    public DatabaseGraph() {
    	setPaisesList(new LinkedList<>());
    }

	public List<PaisDTO> getPaisesList() {
		return paisesList;
	}

	public void setPaisesList(List<PaisDTO> paisesList) {
		this.paisesList = paisesList;
	} 
    
}
