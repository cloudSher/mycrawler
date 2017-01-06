var config = {
    ua: '',
    sleepTime : 20
}
var title = xpath("//div[@class='blog-content']/div[@class='blog-heading']/div[@class='title']/text()");
var content = xpath("//textarea[@class='noshow_content']/text()");

var url=page.getUrl().toString();
var document = {
    'index':'oschina',
    'type':'blog',
    'id':null,
    'data':{
        'title':title,
        'content':content,
        'url':url
    }
}

put("document",document);

urls("(https://my\\.oschina\\.net/flashsword/blog/\\d+)")
