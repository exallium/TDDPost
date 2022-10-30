/**
 * Data repository for our TodoPresenter
 */
interface TodoRepository {
  fun getNotes(): List<TodoNote>
}