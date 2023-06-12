package Bean;

public class Trojan {

	private String Name;
	private String Type;
	private String Server;
	private String Port;
	private String Password;
	private String Sni;

	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public String getServer() {
		return Server;
	}
	public void setServer(String server) {
		Server = server;
	}
	public String getPort() {
		return Port;
	}
	public void setPort(String port) {
		Port = port;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getSni() {
		return Sni;
	}
	public void setSni(String sni) {
		Sni = sni;
	}
}