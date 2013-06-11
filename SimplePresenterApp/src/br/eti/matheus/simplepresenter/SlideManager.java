package br.eti.matheus.simplepresenter;

public class SlideManager{
	
	private SimplePresenterClient simplePresenterClient;
	
	static final String PREV_SLIDE = "0";
	static final String NEXT_SLIDE = "1";
	
	public SlideManager(SimplePresenterClient simplePresenterClient){
		this.simplePresenterClient = simplePresenterClient;
	}
	
	public void nextSlide(){
		simplePresenterClient.send(NEXT_SLIDE);
	}
	
	public void prevSlide(){
		simplePresenterClient.send(PREV_SLIDE);
	}

}
