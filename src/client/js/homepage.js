window.onload = function() {
    setInterval(runSlider, 10000);
    var btnActionCall = document.querySelector('button.btn-action-call');
    btnActionCall.onclick = openFormRegister;
    var btnCloseFormRegister = document.querySelector('div.close-btn');
    btnCloseFormRegister.onclick = closeFormRegister;
}

function runSlider() {
    var currentActive = document.querySelector('.story.slide-active');
    var nextActive = currentActive.nextElementSibling || currentActive.parentNode.firstElementChild;
    currentActive.classList.remove('slide-active');
    nextActive.classList.add('slide-active');
}

function openFormRegister() {
    var formContainer = document.querySelector('.form-container');
    formContainer.classList.remove('hidden');
}

function closeFormRegister() {
    var formContainer = document.querySelector('.form-container');
    formContainer.classList.add('hidden');
}