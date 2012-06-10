package bomberman.game;


import spock.lang.Specification
import messing.around.file.FileReader;
import static org.junit.Assert.*;

class LevelTest extends Specification {
	def "Level toString method is valid"() {
		when:
		FileReader f = new FileReader();
		Level l = f.parse();
		FileReader fz = new FileReader(l.toString());

		then:
		fz.valid == true;
	}
}
