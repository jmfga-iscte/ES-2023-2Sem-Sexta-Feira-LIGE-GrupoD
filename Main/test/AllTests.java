import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses({AulaTest.class, HorarioCarregadoTest.class, HorarioTest.class, ConversorFicheiroTest.class})
public class AllTests {
}
