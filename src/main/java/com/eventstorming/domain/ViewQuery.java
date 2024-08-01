forEach: View
fileName: {{namePascalCase}}Query.java
path: {{boundedContext.name}}/{{{options.packagePath}}}/domain
except: {{#checkExtend queryOption}}{{/checkExtend}}
---
package {{options.package}}.domain;

import java.util.Date;
import lombok.Data;

@Data
public class {{namePascalCase}}Query {

    {{#queryParameters}}
    private {{className}} {{nameCamelCase}};
    {{/queryParameters}}
}
<function>
window.$HandleBars.registerHelper('checkExtend', function (queryOption) {
    if(queryOption.useDefaultUri != true && queryOption.apiPath !=''){
        return false;
    }
});
</function>