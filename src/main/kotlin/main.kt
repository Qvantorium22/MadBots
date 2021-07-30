fun main() {

    val matrixSettings = MatrixSetting(3, 3)
    val gameRules = GameRules(
        toWin = 3,
        autoresize = false
    )

    val game = Game(matrixSettings, gameRules)

    val player = Player("Hulk")
    val player1 = Player("Tor")

    game.setFirstPlayer(player)
    game.setSecondPlayer(player)

    game.start()
}