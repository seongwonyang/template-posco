forEach: Command
representativeFor: Command
fileName: {{namePascalCase}}Command.java
path: {{boundedContext.name}}/s20a01-service/src/main/java/com/posco/{{boundedContext.name}}/s20a01/service
except: {{#isDefaultVerb this}}{{/isDefaultVerb}}
---
package com.posco.{{boundedContext.name}}.s20a01.service;

import java.util.*;
import lombok.Data;
import java.time.LocalDate;
{{#checkBigDecimal fieldDescriptors}}{{/checkBigDecimal}}

@Data
public class {{namePascalCase}}Command {

{{#fieldDescriptors}}
        private {{{className}}} {{nameCamelCase}};
{{/fieldDescriptors}}


}

<function>
window.$HandleBars.registerHelper('except', function (fieldDescriptors) {
    return (fieldDescriptors && fieldDescriptors.length == 0);
});

window.$HandleBars.registerHelper('checkBigDecimal', function (fieldDescriptors) {
    for(var i = 0; i < fieldDescriptors.length; i ++ ){
        if(fieldDescriptors[i] && fieldDescriptors[i].className.includes('BigDecimal')){
            return "import java.math.BigDecimal;";
        }
    }
});

window.$HandleBars.registerHelper('isDefaultVerb', function (command) {
    if(command.isRestRepository){
        return true;
    }
});

</function>
