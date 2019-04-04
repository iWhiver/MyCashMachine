package mycashmachine

class LifeCircleController {

    final String NEW_LINE = "\r\n"

    def index() {
        String message = params.Command
        String output = params.Output

        if (message) {
            output = message + NEW_LINE + output
        }

        render view: 'index', model: [output: output]
    }

    def clean() {
        render view: 'index', model: [output: '']
    }
}
