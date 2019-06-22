/**
 * 获取传入from表单jquery对象表单元素json
 * @param $from
 * @returns
 */
function getFromJson($from){
    var params = $from.serializeArray();
    var values = {};
    for(x in params){ 
        values[params[x].name] = params[x].value; 
        }
    var idata = JSON.stringify(values);
    return idata;
}