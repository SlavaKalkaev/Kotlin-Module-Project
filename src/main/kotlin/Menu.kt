import java.util.Scanner

class Menu(private val scanner: Scanner) {
    private val archives = mutableListOf<Archive>()

    fun showMainMenu() {
        while (true) {
            println("Главное меню:")
            println("1. Создать архив")
            displayList(archives.map { it.name }, 2)
            println("0. Выход")

            when (val choice = getValidIntInput(archives.size + 1)) {
                0 -> {
                    println("Выход из программы...")
                    return
                }
                1 -> createArchive()
                else -> showArchiveMenu(archives[choice - 2])
            }
        }
    }

    private fun createArchive() {
        println("Введите название архива:")
        val name = getNonEmptyInput()
        archives.add(Archive(name))
        println("Архив \"$name\" создан.")
    }

    private fun showArchiveMenu(archive: Archive) {
        while (true) {
            println("Архив: ${archive.name}")
            println("1. Создать заметку")
            displayList(archive.notes.map { it.name }, 2)
            println("0. Назад")

            when (val choice = getValidIntInput(archive.notes.size + 1)) {
                0 -> return
                1 -> createNote(archive)
                else -> showNoteContent(archive.notes[choice - 2])
            }
        }
    }

    private fun createNote(archive: Archive) {
        println("Введите заголовок заметки:")
        val name = getNonEmptyInput()
        println("Введите содержание заметки:")
        val body = getNonEmptyInput()
        archive.notes.add(Note(name, body))
        println("Заметка \"$name\" создана.")
    }

    private fun showNoteContent(note: Note) {
        while (true) {
            println("Заметка: ${note.name}")
            println(note.body)
            println("0. Назад")

            if (getValidIntInput(0) == 0) return
        }
    }

    private fun displayList(items: List<String>, startIndex: Int) {
        items.forEachIndexed { index, item -> println("${index + startIndex}. $item") }
    }

    private fun getValidIntInput(maxValue: Int): Int {
        while (true) {
            val input = scanner.nextLine()
            try {
                val choice = input.toInt()
                if (choice in 0..maxValue) {
                    return choice
                } else {
                    println("Некорректный выбор. Пожалуйста, введите число от 0 до $maxValue.")
                }
            } catch (e: NumberFormatException) {
                println("Некорректный ввод. Пожалуйста, введите число.")
            }
        }
    }

    private fun getNonEmptyInput(): String {
        while (true) {
            val input = scanner.nextLine()
            if (input.isNotBlank()) {
                return input
            } else {
                println("Ввод не должен быть пустым. Попробуйте снова.")
            }
        }
    }
}
