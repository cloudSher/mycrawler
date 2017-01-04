var config = {
    ua: '',
    sleepTime : 20
}
var title = $("div.blog-content div.title");
var content = $("textarea.noshow_content");

var url=page.getUrl().toString();
var document = {
    'index':url,
    'type':title,
    'id':null,
    'data':{
        'title':title,
        'content':content,
        'url':url
    }
}

put("document",document);

urls("http://my\\.oschina\\.net/flashsword/blog/\\d+")
