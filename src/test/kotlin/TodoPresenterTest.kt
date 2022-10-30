import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.math.exp
import kotlin.random.Random
import kotlin.random.nextLong
import kotlin.test.assertEquals
import kotlin.test.fail

/**
 * Specification tests for the Todo Presenter
 */
class TodoPresenterTest {

  private val uniqueTimestamps = generateSequence { Random.nextLong(0, 100000) }
    .take(10)
    .toList()

  private val fakeTestRepository = object : TodoRepository {
    override fun getNotes(): List<TodoNote> {
      return (1..10).map {
        TodoNote(
          body = "Body $it",
          creationTimestamp = uniqueTimestamps[it - 1]
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

    assertTrue(notes.isNotEmpty(), "Test with empty notes list makes no sense.")
    assertTrue(isReverseChron, "Test expected resulting notes to be in reverse chronological order.")
  }

  @Test
  fun `When I addNote, then I expect a new empty note at the head of the list`() {
    val originalCount = testSubject.notes.size

    testSubject.addNote()

    val newCount = testSubject.notes.size
    val firstNote = testSubject.notes.first()
    assertEquals(originalCount + 1, newCount, "Expected a note to be added.")
    assertEquals(firstNote.body, "", "Expected the head to be an empty note")
  }

  @Test
  fun `When I updateNote, then I expect the note to be updated with the given text`() {
    val originalNote = testSubject.notes.first()
    val updatedNote = originalNote.copy(body = "Updated body")

    testSubject.updateNote(updatedNote)

    val newNote = testSubject.notes.first()

    assertEquals(updatedNote, newNote)
  }

  @Test
  fun `When I deleteNote, then I expect the note to be removed from the list`() {
    val noteToDelete = testSubject.notes.first()

    testSubject.deleteNote(noteToDelete.creationTimestamp)

    assertTrue(
      testSubject.notes.none { it.creationTimestamp == noteToDelete.creationTimestamp },
      "The deleted note was still in the list."
    )
  }

}