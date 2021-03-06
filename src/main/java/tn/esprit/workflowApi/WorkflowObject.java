package tn.esprit.workflowApi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkflowObject implements Cloneable {
	
	private final long uniqueID;
	
	private WorkflowStatus status;
	
	private Map<Long, WorkflowObject> next;
	protected Map<String, Object> parameters;
	
	protected WorkflowObject() {
		super();
		
		this.status = WorkflowStatus.PENDING;
		this.next = new HashMap<Long, WorkflowObject>();
		this.uniqueID = WorkflowManager.getNextUniqueID();
		this.parameters = new HashMap<String, Object>();
		
	}
	
	protected WorkflowObject(Map<String, Object> parameters) {
		this();
		
		this.parameters = new HashMap<String,Object>(parameters);
	}
	
	public long getUniqueID() {
		return this.uniqueID;
	}
	
	public WorkflowObject getNext(long uniqueID) {
		return this.next.get(uniqueID);
	}
	
	public void addNext(WorkflowObject obj) {
		this.next.put(obj.getUniqueID(), obj);
	}
	
	public WorkflowObject removeNext(long uniqueID) {
		return this.next.remove(uniqueID);
	}
	
	public void clearNext() {
		this.next.clear();
	}
	
	public List<WorkflowObject> getNextList(){
		return new ArrayList<WorkflowObject>(this.next.values());
	}
	
	public void setStatus(WorkflowStatus status) {
		this.status = status;
	}
	
	public WorkflowStatus getStatus() {
		return this.status;
	}
	
	public void setParameter(String key, Object value) {
		parameters.put(key, value);
	}

	public void setParameters(Map<String, Object> parameters) {
		this.parameters.putAll(parameters);
	}

	public Object removeParameter(String key) {
		return parameters.remove(key);
	}

	public Object getParameter(String key) {
		return parameters.get(key);
	}
}
