function draw_permission_list(div_id,permissions) {
    var perm_list = document.getElementById(div_id);
    var html_content="";
    for (i in permissions) {
        var curId = id_generator();
        html_content+='<input type="checkbox" class="permission-check" name="permissionNames" value="'+permissions[i].buttonCaption+'" id="check'+curId+'" '+permissions[i].buttonLink+'>'+
            '<label for="check'+curId+'">'+permissions[i].buttonCaption+'</label>';
    }
    perm_list.innerHTML = html_content;
}
