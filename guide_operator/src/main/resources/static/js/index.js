window.onload = function() {
    // interval slider
    setInterval(runSlider, 10000);
    // set on click event listener for every element in page
    window.onclick = setOnClickListener;

    setInterval(function() {
        var carouselList = document.querySelector('.list');
        curLeft = parseInt(carouselList.style.left) || 0;
        carouselList.style.left = `${(curLeft >= -1150)? curLeft-210: 0}px`;
    }, 5000);
}

function setOnClickListener(event) {
    var target = event.target;
    switch (target.id) {
        case 'txt_sign_in':
        case 'join_us': 
            openFormRegister('default');
            break;
        case 'txt_sign_up':
            openFormRegister('signup')
            break;
        case 'close_form':
            closeFormRegister();
            break;
        default:
            // just default
    }
    switch (target.className) {
        case 'prev':
            handleCarousel('prev');
            break;
        case 'next':
            handleCarousel('next');
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

function openFormRegister(mode) {
    if (mode === 'signup') {
        document.querySelector('.signup-group').classList.remove('hidden');
        document.querySelector('.btn-submit').value = 'Sign up';
        $("#form1").attr("action","/signup");

    } else {
        document.querySelector('.signup-group').classList.add('hidden');
        document.querySelector('.btn-submit').value = 'Sign in';
        $("#form1").attr("action","/login");
    }
    var formContainer = document.querySelector('.form-container');
    formContainer.classList.remove('hidden');
}

function closeFormRegister() {
    var formContainer = document.querySelector('.form-container');
    formContainer.classList.add('hidden');
}

function handleCarousel(move) {
    var carouselList = document.querySelector('.list');
    curLeft = parseInt(carouselList.style.left) || 0;
    if (move === 'prev') {
        carouselList.style.left = `${(curLeft >= -1150)? curLeft-210: curLeft}px`;
    } else {
        carouselList.style.left = `${(curLeft <= -210)? curLeft+210: curLeft}px`;
    }
}