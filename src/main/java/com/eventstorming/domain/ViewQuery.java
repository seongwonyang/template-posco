forEach: View
fileName: {{#if queryOption.apiPath}}{{#changeUpper queryOption.apiPath}}{{/changeUpper}}{{else}}{{namePascalCase}}{{/if}}Query.java
path: {{boundedContext.name}}/{{{options.packagePath}}}/domain
except: {{#checkExtend this}}{{/checkExtend}}
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
window.$HandleBars.registerHelper('checkExtend', function (view) {
    if(view.queryParameters =='' || view.dataProjection == 'cqrs' ){
        return true;
    }else{
        return false;
    }
});
window.$HandleBars.registerHelper('changeUpper', function (name) {
    return name.charAt(0).toUpperCase() + name.slice(1);
});
</function>