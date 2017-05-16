function valid_login() {
    var log = document.forms.log;
    var bool = true;
    if (log.username.value === '' || log.username.value.length < 4) {
        {
            bool = false;
            alert("Login musi mieć minimum 4 znaki");
        }
    } else {
        if (log.password.value === '' || log.password.value.length < 4)
        {
            bool=false;
            alert("Hasło musi mieć minimum 4 znaki");
        } else {
            log.js.value = 'true';
        }
    }
    return bool;
}