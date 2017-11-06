window.onload = function() {
    // interval slider
    setInterval(runSlider, 10000);
    // set on click event listener
    window.onclick = setOnClickListener;

    window.onscroll = function (e) {
        var header = document.querySelector('.header');
        if (document.body.scrollTop > 100 || document.documentElement.scrollTop > 100) {
            header.style.backgroundColor = 'rgba(50, 50, 50, 0.9)';
        } else {
            header.style.backgroundColor = 'rgba(50, 50, 50, 0.3)';            
        }
    }
}

function setOnClickListener(event) {
    var target = event.target;
    switch (target.id) {
        case 'join_us': 
            openFormRegister();
            break;
        case 'close_form':
            closeFormRegister();
            break;
        default:
            // just default
    }
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