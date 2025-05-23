var s = "", tail_sum = 150, tail_num = 0;

for (var i = 0; i < tail_sum; i++) {
    s += "<div class='tail_entity none' id='tail_entity_" + i + "'></div>";
}

document.getElementById("tail_show").innerHTML = s;

document.onmousemove = function (event) {
    setTimeout(function () {

        var t = document.getElementById("tail_entity_" + tail_num);

        t.className = "tail_entity show";
        t.style.top = event.clientY + "px";
        t.style.left = event.clientX + "px";
        // console.log(event.clientX, event.clientY, t.style.top, t.style.left, typeof (t.style.top), typeof (t.style.left));
        setTimeout(function () {
            t.className = "tail_entity none";
        }, 2000);

        tail_num = (tail_num + 1) % tail_sum;
    }, 10);
}
