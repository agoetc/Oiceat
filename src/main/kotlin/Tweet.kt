import twitter4j.*

 fun main(args: Array<String>) {
        val seat = Seat()
        val twitter = TwitterFactory.getSingleton()
        val (形容詞, 時刻) = seat.get形容詞and時刻(seat.search())

        if (形容詞 != "" || 時刻 != "")
            twitter.updateStatus("OIC便座${形容詞}\n\n${時刻}")
        println(形容詞)
        println(時刻)
    }

