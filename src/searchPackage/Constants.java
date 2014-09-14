package searchPackage;

public class Constants {
	private String videoFileExtensions="3g2;3gp;asf;asx;avi;flv;mov;mp4;mpg;rm;swf;vob;wmv;mkv";
	private String audioFileExtensions="aif;iff;m3u;m4a;mid;mp3;mpa;ra;wav;wma";
	private String executableFileExtensions="app;bat;com;exe;gadget;jar;wsf;msi";
	private String documentFileExtensions="doc;docx;rtf;txt;csv;pdf;xls;xlsx;xps";
	private String compressedFileExtensions="7z;deb;gz;pkg;rar;zip;zipx";
	private static String version="1.4";
	
	public String getVideoFileExtensions() {
		return videoFileExtensions;
	}
	public String getAudioFileExtensions() {
		return audioFileExtensions;
	}
	public String getExecutableFileExtensions() {
		return executableFileExtensions;
	}
	public String getDocumentFileExtensions() {
		return documentFileExtensions;
	}
	public String getCompressedFileExtensions() {
		return compressedFileExtensions;
	}
	
	public static String getVersion(){
		return version;
	}
	
}
