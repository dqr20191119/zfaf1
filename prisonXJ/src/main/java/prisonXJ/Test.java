package prisonXJ;
import com.cesgroup.framework.util.ConfigUtil;

public class Test {
	
	public static void main(String[] args) {
		String s  = ConfigUtil.get("SOCKET_URL");
		System.out.println(s);
		
		
	}

}
