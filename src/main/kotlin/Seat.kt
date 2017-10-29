import org.atilika.kuromoji.Tokenizer
import twitter4j.*
import java.text.SimpleDateFormat


class Seat{
    val twitter = TwitterFactory.getSingleton()
    val query = Query()

    //ツイートを検索
    fun search():QueryResult{
        query.setQuery("OIC便座 -Oiceat")
        val results:QueryResult = twitter.search(query)
        //何件あったかを出力
        println("ヒット数 : " + results.tweets.count())
        return results
    }

    fun get形容詞and時刻(results:QueryResult):Pair<String,String>{
        val tokenizer = Tokenizer.builder().build()
        var 形容詞 = ""
        var 時刻 = ""


        loop@ for (result in results.tweets){
            val tokens = tokenizer.tokenize(result.toString())

            //形容詞が出るまでループ
            for (token in tokens) {
                val 品詞 = token.partOfSpeech.split(Regex(","))
                if (品詞[0] == "形容詞") {
                    形容詞 = token.baseForm
                    時刻 = SimpleDateFormat("MM月dd日HH時mm分").format(result.getCreatedAt())
                    break@loop
                }
            }
        }
        return Pair(形容詞,時刻)
    }
}