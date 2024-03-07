function checkHit(event) {
    let x = ((event.pageX - $('#graphic').offset().left - 15 - 150) / 30 + 0.5).toFixed(2);
    let y = ((event.pageY - $('#graphic').offset().top - 15 - 150) / -30 - 0.5).toFixed(2);
    let r = $("[onchange='updateRadius(event)']").val();
    if (validateR()) {
        $('#rContainer').removeClass('error-hovered');
        $('.xMenu').eq(1).val(x);
        $('.yInput').eq(1).val(y);
        $('.rMenu').eq(1).val(r);
        $('.mainSubmitButton').click();
    } else {
        $('#rContainer').addClass('error-hovered');
    }
}

function validateR() {
    let r = $("[onchange='updateRadius(event)']").val();
    r = parseFloat(r.replace(",", "."));
    return !isNaN(r);
}

function roundHalf(num) {
    return Math.round(num * 2) / 2;
}