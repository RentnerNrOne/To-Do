import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import datenbank.SqlCommandsImpl;

@ExtendWith(MockitoExtension.class)
public class SqlCommandsTest {
	@Mock
	private SqlCommandsImpl sql;

	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void CreateTable() {
		sql.createTableTODO();
		//Mockito.verify();
	}

}
