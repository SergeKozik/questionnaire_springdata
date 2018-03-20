function get_selected_value(select_id) {
    var selectObj = document.getElementById(select_id);
    return selectObj.options[selectObj.selectedIndex].value;
}
