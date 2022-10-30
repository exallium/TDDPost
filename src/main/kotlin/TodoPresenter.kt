/**
 * Presenter for our TODO notes.
 */
class TodoPresenter(todoRepository: TodoRepository) {
  val notes: List<TodoNote> = todoRepository.getNotes()
}