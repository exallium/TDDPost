/**
 * Presenter for our TODO notes.
 */
class TodoPresenter(todoRepository: TodoRepository) {

  private val _notes: MutableList<TodoNote> = mutableListOf()

  val notes: List<TodoNote> = _notes

  init {
    _notes.addAll(todoRepository.getNotes().sortedWith(NoteComparator().reversed()))
  }

  fun addNote() {
    _notes.add(0, TodoNote("", System.currentTimeMillis()))
  }

  fun updateNote(updatedNote: TodoNote) {
    val noteIndex = _notes.indexOfFirst { updatedNote.creationTimestamp == it.creationTimestamp }
    _notes.removeAt(noteIndex)
    _notes.add(noteIndex, updatedNote)
  }

  fun deleteNote(timestamp: Long) {
  }

  private class NoteComparator : Comparator<TodoNote> {
    override fun compare(o1: TodoNote, o2: TodoNote): Int {
      return o1.creationTimestamp.compareTo(o2.creationTimestamp)
    }
  }
}