package application;

import java.net.URL;

public class PatchP {
	
	private String imageM;
	private URL url;


	public String patch(){
	
	URL url=this.getClass().getClassLoader().getResource("money.mp3");
	imageM = url.toString();
	return imageM;
			
	}
	
	public PatchP(URL url, String imageM) {
		super();
		this.url = url;
		this.imageM = imageM;
	}


	public PatchP() {
		// TODO Auto-generated constructor stub
	}

}
