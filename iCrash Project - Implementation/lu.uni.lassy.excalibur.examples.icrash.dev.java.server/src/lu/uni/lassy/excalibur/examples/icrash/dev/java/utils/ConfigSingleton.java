package lu.uni.lassy.excalibur.examples.icrash.dev.java.utils;

public enum ConfigSingleton {	
    INSTANCE;
    public void getPort () {
        // Perform operation here 
    }
    private int PORT;
    public int getPORT() {
		return PORT;
	}
	public void setPORT(int pORT) {
		PORT = pORT;
	}
	public String getRmiServerName() {
		return rmiServerName;
	}
	public void setRmiServerName(String rmiServerName) {
		this.rmiServerName = rmiServerName;
	}
	public String getRmiEnvName() {
		return rmiEnvName;
	}
	public void setRmiEnvName(String rmiEnvName) {
		this.rmiEnvName = rmiEnvName;
	}
	private String rmiServerName;
    private String rmiEnvName;
}
