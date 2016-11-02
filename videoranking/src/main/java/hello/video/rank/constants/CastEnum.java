package hello.video.rank.constants;

/**
 *
 * 演职人员类型
 *
 */
public enum CastEnum {
	/*导演*/
	DIRECTOR(1),
	/* 演员 */
	ACTOR(2),
	/* 主持人 */
	MASTER(3),
	/* 嘉宾 */
	GUEST(4),
	/* 声优 */
	VOICE(5);
	private int value;
	
	CastEnum(int value){
		this.value = value;
	}	
	public int getValue(){
		return this.value;
	}
}
