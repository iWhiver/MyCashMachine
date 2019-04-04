<div class="container" id="page">
    <h2>Hello! I am cash machine. Let's do it!</h2>


    <g:form name="lifeCircle" controller="lifeCircle" action="index" class="form">
        <div class="form-group">
            <label for="output">Output:</label>
            <g:textArea name="Output" class="form-control" rows="5" readonly="true" id="output">${output}</g:textArea>
        </div>

        <div class="form-group">
            <label for="command">Command:</label>
            <g:textField name="Command" class="form-control" id="command" > </g:textField>
        </div>
        <g:actionSubmit value="Run It" action="index" class="btn btn-primary"/>
        <g:actionSubmit value="Clean" action="clean" class="btn btn-primary"/>
    </g:form>

</div>