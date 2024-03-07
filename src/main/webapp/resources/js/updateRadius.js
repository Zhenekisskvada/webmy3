function updateRadius(event) {
    let r = $("[onchange='updateRadius(event)']").val();
    r = parseFloat(r.replace(",", "."));
    if (r > 5 || r < 2)
        return;
    let path = `M ${150 +r * 15} 150` +
        `L ${150 +r * 15} ${150 +r * 30}` +
        `L 150 ${150 +r * 30}` +
        `L 150 ${150 +r * 15}` +
        `L ${150 - r * 30} 150` +
        `A ${r * 30} ${r * 30} 0 0 1 150 ${150 - r * 30}`+
        'L 150 150'+
        'L ${150 +r * 30} 150'
    $("#graph-path").attr("d", path);
}
