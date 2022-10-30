/**
 * Presenter for our TODO notes.
 */
class TodoPresenter(todoRepository: TodoRepository) {
  val notes: List<TodoNote> = todoRepository.getNotes()
    .sortedWith(NoteComparator().reversed())

  private class NoteComparator : Comparator<TodoNote> {
    override fun compare(o1: TodoNote, o2: TodoNote): Int {
      return o1.creationTimestamp.compareTo(o2.creationTimestamp)
    }
  }
}