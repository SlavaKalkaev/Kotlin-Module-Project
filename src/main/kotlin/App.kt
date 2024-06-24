import java.util.Scanner

class App {
    private val scanner = Scanner(System.`in`)
    private val menu = Menu(scanner)

    fun start() {
        menu.showMainMenu()
    }
}