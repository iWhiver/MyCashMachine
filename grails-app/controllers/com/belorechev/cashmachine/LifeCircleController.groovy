package com.belorechev.cashmachine

import com.belorechev.cashmachine.computer.ComputerService
import org.springframework.beans.factory.annotation.Value

class LifeCircleController {

    @Value('${dictionary.NEW_LINE}')
    String NEW_LINE

    ComputerService computerService

    def index() {

        String command = params.Command
        String output = params.Output

        if (command) {
            def result = computerService.calculate(command)
            output = result + NEW_LINE + output
        }

        render view: 'index', model: [output: output]
    }

    def clean() {
        render view: 'index', model: [output: '']
    }
}
