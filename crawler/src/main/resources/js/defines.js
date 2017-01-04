function $(str){
    return page.getHtml().$(str).toString();
}
function xpath(str){
    return page.getHtml().xpath(str).toString();
}
function urls(str){
    links = page.getHtml().links().regex(str).all();
    page.addTargetRequests(links);
}

function put(k,v){
    page.putField(k,v);
}

var chars  = ['0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'];
function random(num){
    var ch = "";
    for(var i = 0 ; i < num; i++){
        ch += chars[Math.ceil(Math.random() * 35)];
    }
    return ch;
}
