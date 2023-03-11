var prevScrollpos = window.pageYOffset;
window.onscroll = function () {
    var currentScrollPos = window.pageYOffset;
    if (prevScrollpos > currentScrollPos) {
        document.getElementById("header-cover").style.top = "0";
    } else {
        document.getElementById("header-cover").style.top = "-80px";
    }
    prevScrollpos = currentScrollPos;
}
