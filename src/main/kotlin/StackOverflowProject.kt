package web.application.project

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class StackOverflowProject {

}

fun main(args: Array<String>){
    runApplication<StackOverflowProject>(*args)
}