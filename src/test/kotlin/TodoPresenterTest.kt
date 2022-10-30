import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.random.Random
import kotlin.test.fail

/**
 * Specification tests for the Todo Presenter
 */
class TodoPresenterTest {

  private val fakeTestRepository = object : TodoRepository {
    override fun getNotes(): List<TodoNote> {
      return (1..10).map {
        TodoNote(
          body = "Body $it",
          creationTimestamp = Random.nextLong()
        )
      }
    }
  }

  private val testSubject = TodoPresenter(fakeTestRepository)

  @Test
  fun `When I init, then I expect notes in reverse chronological order`() {
    val notes = testSubject.notes

    val isReverseChron = notes
      .windowed(2, 1)
      .all { (a, b) -> a.creationTimestamp > b.creationTimestamp }

    assertTrue(notes.isNotEmpty())
    assertTrue(isReverseChron)
  }

  @Test
  fun `When I addNote, then I expect a new empty note at the head of the list`() {
    fail()
  }

  @Test
  fun `When I updateNote, then I expect the note to be updated with the given text`() {
    fail()
  }

  @Test
  fun `When I deleteNote, then I expect the note to be removed from the list`() {
    fail()
  }

}