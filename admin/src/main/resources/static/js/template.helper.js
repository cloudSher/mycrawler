template.helper('total', function (data, key) {
    if (!data || !key || !data.length) {
        return 0;
    }
    var total = 0;
    for (var i = 0; i < data.length; i++) {
        total += parseInt(data[i][key]);
    }
    return total;
});

template.helper('json2str', function (data) {
    if (!data) {
        return '';
    }
    return JSON.stringify(data);
});

template.helper('dateFormat', function (date, format) {
    if (!date) {
        return '';
    }
    format = format || 'YYYY-MM-DD hh:mm:ss';
    return moment(parseInt(date)).format(format);
});

template.helper('pagination', function (data, lang, type) {
    // 分页属性配置
    var pageInfo = {
        totalCount: 'totalCount',
        pageIndex: 'pageIndex',
        pageCount: 'pageCount',
        showCount: 3
    };

    var totalCount = data[pageInfo.totalCount];
    if (!totalCount) {
        return '';
    }
    // 显示个数
    var showCount = pageInfo.showCount || 3;
    // 每页显示个数
    var pageCount = data[pageInfo.pageCount] || 8;
    // 当前页索引
    var pageIndex = data[pageInfo.pageIndex] || 0;
    // 总页数
    var totalPage = Math.ceil(totalCount / pageCount);
    // 当前页
    var page = pageIndex + 1;
    if (page < 1) {
        page = 1;
    }
    if (page > totalPage) {
        page = totalPage;
    }
    // 开始
    var pageStart = page - showCount;
    if (pageStart < 1) {
        pageStart = 1;
    }
    // 结束
    var pageEnd = page + showCount;
    if (pageEnd > totalPage) {
        pageEnd = totalPage;
    }
    // HTML
    var interval = '<li><span class="only-show"><i class="zmdi zmdi-more-horiz"></i></span></li>';
    var html = '<div class="bootgrid-footer container-fluid"><div class="row"><div class="col-sm-12 m-b-25"><ul class="pagination">';
    // 上一页
    html += '<li';
    if (page === 1) {
        html += ' class="disabled"';
    }
    html += '><a';
    if (page !== 1) {
        html += ' href="' + pageHref(page - 2, pageCount, type) + '"';
    }
    html += ' aria-label="' + lang.btn.prev + '"><i class="zmdi zmdi-chevron-left"></i></a></li>';
    if (type === 'full') {
        // 间隔段
        if (pageStart > 1) {
            html += interval;
        }
        // 页码区
        for (var i = pageStart; i <= pageEnd; i++) {
            html += '<li';
            if (i === page) {
                html += ' class="active"';
            }
            html += '><a href="' + pageHref(i - 1, pageCount, type) + '">' + i;
            if (i === page) {
                html += ' <span class="sr-only">(current)</span>';
            }
            html += '</a></li>';
        }
        // 间隔段
        if (pageEnd < totalPage) {
            html += interval;
        }
    } else {
        html += '<li class="page-num">' + page + ' / ' + totalPage + '</li>';
    }

    // 下一页
    html += '<li';
    if (page === totalPage) {
        html += ' class="disabled"';
    }
    html += '><a';
    if (page !== totalPage) {
        html += ' href="' + pageHref(page, pageCount, type) + '"';
    }
    html += ' aria-label="' + lang.btn.next + '"><i class="zmdi zmdi-chevron-right"></i></a></li>';
    html += '</ul></div></div></div>';
    return html;

    function pageHref(pageIndex, pageCount, type) {
        var pageIndexName = pageInfo.pageIndex;
        var pageCountName = pageInfo.pageCount;
        if (type === 'part') {
            return '&' + URI('').setSearch(pageIndexName, pageIndex).setSearch(pageCountName, pageCount).toString().substring(1);
        } else {
            return '#' + URI(window.location.hash.substring(1)).setSearch(pageIndexName, pageIndex).setSearch(pageCountName, pageCount);
        }
    }
});

template.helper('steps', function (data, step) {
    step = step || 4;
    if (!data || !data.length) {
        return '';
    }
    var row = Math.ceil(data.length / step);
    var col = data.length % step;
    var doubleStep = 2 * step;

    var i;
    var clazz;
    // 处理节点样式
    for (i = 0; i < data.length; i++) {
        clazz = "step";
        // 特殊点
        if (i === 0) {
            clazz += " right";
        } else if (i === (data.length - 1)) {
            if (col === 1) {
                if (row % 2 === 0) {
                    clazz += " up";
                } else {
                    clazz += " up2";
                }
            } else if (row % 2 === 1) {
                clazz += " left";
            } else {
                clazz += " right";
            }
        } else if ((i + 1 + step) % doubleStep === 0) {
            clazz += " left-down";
        } else if ((i + step) % doubleStep === 0) {
            clazz += " left-up";
        } else if ((i + 1) % doubleStep === 0) {
            clazz += " right-down";
        } else if (i % doubleStep === 0) {
            clazz += " right-up";
        }
        // 是否激活
        if (data[i].active === true) {
            clazz += " active";
        }
        data[i].clazz = clazz;
    }
    // 补齐数组
    for (i = 0; i < (step - col); i++) {
        data.push(false);
    }
    // 分割成2维数组
    var list = [];
    var j;
    var tmp;
    for (i = 0; i < row; i++) {
        tmp = [];
        for (j = 0; j < step; j++) {
            if (i % 2 === 0) {
                tmp.push(data[i * step + j]);
            } else {
                tmp.push(data[(i + 1) * step - j - 1]);
            }
        }
        list.push(tmp);
    }

    var html = '<div class="table-responsive"><table class="steps">';
    for (i = 0; i < row; i++) {
        html += '<tr>';
        for (j = 0; j < step; j++) {
            if (list[i][j] === false) {
                html += '<td></td>';
            } else {
                html += '<td class="' + list[i][j].clazz + '"><span title="' + list[i][j].title + '">' + list[i][j].text + '</span></td>';
            }
        }
        html += '</tr>';
    }
    html += '</table></div>';
    return html;
});

template.helper('electricity', function (data) {
    var maxLength = 7;
    var zero = '0000000';
    if (!data) {
        data = zero;
    } else {
        data = data.toString();
    }
    var length = data.length;
    if (length > maxLength) {
        data = data.substring(length - maxLength);
    } else if (length < maxLength) {
        data = zero.substr(0, maxLength - length) + data;
    }
    var html = '<table class="electricity"><tr>';
    for (var i = 0; i < maxLength; i++) {
        html += '<td>' + data[i] + '</td>';
    }
    html += '</tr></table>';
    return html;
});