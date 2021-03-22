package ExceptionDAO;

public class ExceptioDao extends Exception {

	
	private static final long serialVersionUID = 1L;
	private String msg;
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public ExceptioDao(String msg) {
		super(msg);
		
	}
	
	
	
	
	
}
