window.onload = function() {
    setInterval(runSlider, 5000);
}

function runSlider() {
    var currentActive = document.querySelector('.story.slide-active');
    var nextActive = currentActive.nextElementSibling || currentActive.parentNode.firstElementChild;
    currentActive.classList.remove('slide-active');
    nextActive.classList.add('slide-active');
}