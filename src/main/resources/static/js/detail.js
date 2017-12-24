function pageInit() {
    initDataList();
}
function initDataList() {
    var href = 'upload/'+decodeURI(epm.get('href'));
    $('iframe').attr('src',href);
}