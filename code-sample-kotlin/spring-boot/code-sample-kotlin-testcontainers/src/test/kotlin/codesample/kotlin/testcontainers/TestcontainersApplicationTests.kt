package codesample.kotlin.testcontainers


import codesample.kotlin.testcontainers.entity.Entity
import codesample.kotlin.testcontainers.repository.EntityRepository
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.util.*

@RunWith(SpringRunner::class)
@SpringBootTest
class TestcontainersApplicationTests {

	@Autowired
	lateinit var repo: EntityRepository

	@Test
	fun contextLoads() {
	}

	@Test
	fun `insert data into repo`() {
		assertNotNull(repo.save(Entity(UUID.randomUUID())))
	}
}
