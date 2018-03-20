function permission_service(url,role_name,div_id) {
    var request = new XMLHttpRequest();
    var param = encodeURIComponent(role_name);
    var new_url = url+'/'+param;
    request.open("GET", new_url,true);
    request.onreadystatechange = function () {
        if (request.readyState === 4 && request.status === 201) {
            var type = request.getResponseHeader("Content-Type");
            if (type === "application/json;charset=UTF-8")
                draw_permission_list(div_id,JSON.parse(request.responseText));
        }
    }
    request.send(null);
}
