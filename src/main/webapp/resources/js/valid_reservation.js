function valid_reservation() {
    var form = document.getElementById("reserve");
    if (form.fullname.value === '' || form.fullname.value.length < 5) {
        alert("Imię i nazwisko mausi mieć co najmniej 5 znaków");
        bool = false;
    }
    var emailPattern = /^[0-9a-zA-Z_.-]+@[0-9a-zA-Z.-]+\.[a-zA-Z]{2,3}$/;
    if (!(emailPattern.test(form.email.value))) {
        alert("Wprowadz prawidłowy email");
        bool = false;
    }
    if (form.company.value === '' || form.company.value.length < 5) {
        alert("Firma musi mieć co najmniej 5 znaków");
        bool = false;
    }
    var datePattern = /^(20){1}[0-9]{2}-(0[1-9]|1[0-2])-(0[1-9]|1[0-9]|2[0-9]|3[01])$/;
    if (!(datePattern.test(form.startDate.value))) {
        alert("Data początkowa powinna być w formacie 'yyyy-MM-dd' i zawierać poprawne wartości");
        bool = false;
    }
    if (!(datePattern.test(form.endDate.value))) {
        alert("Data końcowa powinna być w formacie 'yyyy-MM-dd' i zawierać poprawne wartości");
        bool = false;
    }
    if (bool !== 0) {
        form.js.value = 'true';
    }
    return bool;
}

