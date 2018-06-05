package annotationPractice;

public class MyObject {
	@StringInjector("My name is JDM.")  // Annotation값을 지정하면 String value()로 들어감(Annotaion에서 이미 지정해둔것)
	private String name;
	@StringInjector  // default "This is StringInjector"로 지정 해뒀으므로 값을 지정 안해뒀으면 default값 그대로 남아있음
	private String defaultValue;
	@StringInjector  // 위와 동일 하지만 Integer타입
	private Integer invalidType;
	
	public String getName() {
		return name;
	}
	public String getDefaultValue() {
		return defaultValue;
	}
	public Integer getInvalidType() {
		return invalidType; 
	}
}
