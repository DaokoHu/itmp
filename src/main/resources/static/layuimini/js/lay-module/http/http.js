// 本地的开发环境
const $api="http://localhost:8080";


function verifyToken() {
    let token = layui.data('table')['Authorization'];
    if(typeof token == "undefined" || token == null || token === ""){
        window.location = "page/login-3.html";

    }
}

function dateToString(d) {
    if (d[1] < 10) {
        d[1] = '0' + d[1];
    }
    if (d[2] < 10) {
        d[2] = '0' + d[2];
    }
    if (d[3] < 10) {
        d[3] = '0' + d[3];
    }
    if (d[4] < 10) {
        d[4] = '0' + d[4];
    }
    return d[0] + '-' + d[1] + '-' + d[2] + ' ' + d[3] + ':' + d[4];
}

let count = 0;
let html = "";